package com.cricket.simulator.matches.service;

import com.cricket.simulator.matches.dto.MatchResDto;
import com.cricket.simulator.matches.dto.PlayerResDto;
import com.cricket.simulator.matches.dto.ProcessMatchDto;
import com.cricket.simulator.matches.entity.Fixture;
import com.cricket.simulator.matches.entity.MatchDetails;
import com.cricket.simulator.matches.exception.FixtureNotFound;
import com.cricket.simulator.matches.repository.FixtureRepo;
import com.cricket.simulator.matches.repository.MatchDetailsRepo;
import com.cricket.simulator.matches.util.CommonUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@Service("matchService")
@Slf4j
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final FixtureRepo fixtureRepo;
    private final CommonUtils commonUtils;
    private final MatchDetailsRepo matchDetailsRepo;
    private final RestTemplate restTemplate;

    @Value("${team-service.url}")
    private String teamSerUrl;

    private String FIND_BY_ID = "find-player-team-id?teamId=%s";


    @Override
    @Transactional
    @CircuitBreaker(name = "service_A", fallbackMethod = "fallbackMethod")
    public MatchResDto processMatch(Long fixtureId) throws FixtureNotFound {


        Fixture fixture = fixtureRepo.findById(fixtureId).orElseThrow(() -> new FixtureNotFound("fixture Not found " + fixtureId));
        AtomicLong tossWinTeamId = new AtomicLong(commonUtils.getRandomNumber(10) > 5 ? fixture.getAwayTeamId() : fixture.getHomeTeamId());
        AtomicLong bowlTeamId = new AtomicLong(fixture.getHomeTeamId().equals(tossWinTeamId.get()) ?   fixture.getAwayTeamId() : tossWinTeamId.get());
        log.info("url {}", teamSerUrl.concat(String.format(FIND_BY_ID, bowlTeamId)));
        List<PlayerResDto> bowTeamPlayerResDto = restTemplate.exchange(teamSerUrl.concat(String.format(FIND_BY_ID, bowlTeamId)), HttpMethod.GET, null, new ParameterizedTypeReference<List<PlayerResDto>>() {
        }).getBody();
        List<PlayerResDto> batTeamPlayerResDto = restTemplate.exchange(teamSerUrl.concat(String.format(FIND_BY_ID, tossWinTeamId)), HttpMethod.GET, null, new ParameterizedTypeReference<List<PlayerResDto>>() {
        }).getBody();
        fixture.setTossWinTeamId(tossWinTeamId.get());
        IntStream.rangeClosed(0, 1).boxed().forEach(i -> {
            AtomicInteger wicketNo = new AtomicInteger(10);
            AtomicInteger totalRun = new AtomicInteger(0);
            AtomicInteger currBatsMan = new AtomicInteger(0);
                    log.info(" value of index {} ",i);
                    IntStream.rangeClosed(0, fixture.getOverMatch().intValue()-1).boxed().forEach((over) -> {
                        if (wicketNo.get() > 0) {
                            log.info("wicket {}", wicketNo.get());
                            processMatch(ProcessMatchDto.builder().over(over).fixture(fixture).totalRun(totalRun).batTeamId(tossWinTeamId.get()).bowlTeamId(bowlTeamId.get()).currBatsManIndex(currBatsMan).wicket(wicketNo).bowlingPlayerId(bowTeamPlayerResDto).batPlayerId(batTeamPlayerResDto).build());
                        }
                    });
                    log.info("win team Id {} ",tossWinTeamId);
                    if (fixture.getAwayTeamId().equals(Long.valueOf(tossWinTeamId.get()))) {
                        fixture.setAwayTeamTotalRun(Long.valueOf(totalRun.get()));
                    } else {
                        fixture.setHomeTeamTotalRun(Long.valueOf(totalRun.get()));
                    }
                    long temp = tossWinTeamId.getAndSet(bowlTeamId.get());
                    bowlTeamId.set(temp);
                    log.info("exchange bowlTeamId batTeamId {} , {}",tossWinTeamId.get(),bowlTeamId.get());
                }
        );
        fixture.setWinTeamId(fixture.getHomeTeamTotalRun() > fixture.getAwayTeamTotalRun() ? fixture.getHomeTeamId() : fixture.getAwayTeamId());
        fixtureRepo.save(fixture);


        return MatchResDto.builder().msg("created ").build();
    }

    private void processMatch(ProcessMatchDto processMatchDto) {
        List<MatchDetails> listMatchDetails = new ArrayList<>();
        int bowlerId = commonUtils.getRandomNumber(processMatchDto.getBowlingPlayerId().size());
        if (processMatchDto.getWicket().get() > 0) {
            log.info("size {}", processMatchDto.getCurrBatsManIndex().get());
            log.info("Bat {}", processMatchDto.getBatPlayerId().size());
            IntStream.rangeClosed(0, 5).boxed().forEach(ball -> {
                if (processMatchDto.getCurrBatsManIndex().get() < processMatchDto.getBatPlayerId().size()) {
                    log.info("inside process");
                    int run = commonUtils.getRandomNumber(9);
                    log.info("run {}", run);
                    boolean isNoBall = run == 7 ? true : false;
                    boolean isWicket = run == 5 ? true : false;
                    boolean isWide = run == 8 ? true : false;
                    if (isWicket) {
                        processMatchDto.getWicket().decrementAndGet();
                    }
                    if (processMatchDto.getWicket().get() > 0) {
                        int runs = isWide || isNoBall ? 1 : isWicket ? 0 : run;
                        processMatchDto.getTotalRun().getAndAdd(runs);
                        listMatchDetails.add(MatchDetails.builder().battingTeamId(processMatchDto.getBatTeamId()).bowlingPlayerId(processMatchDto.getBowlingPlayerId().get(bowlerId).getPlayerId()).battingPlayerId(processMatchDto.getBatPlayerId().get(processMatchDto.getCurrBatsManIndex().get()).getPlayerId()).bowlingTeamId(processMatchDto.getBowlTeamId()).isWideBall(isWide).run(runs).overNo(processMatchDto.getOver().longValue()).fixtureId(processMatchDto.getFixture()).isWicket(isWicket).ballNo(ball.longValue()).build());
                        if (isWicket) processMatchDto.getCurrBatsManIndex().addAndGet(1);

                    }
                }
            });

        }
        log.info("process ended");
        if (!listMatchDetails.isEmpty()) {
            log.info("saved successfully");
            matchDetailsRepo.saveAll(listMatchDetails);
        }
    }

    private MatchResDto fallbackMethod(Throwable throwable){
        log.error("exception fallback");
        return MatchResDto.builder().msg("service unavailable").build();
    }


}
