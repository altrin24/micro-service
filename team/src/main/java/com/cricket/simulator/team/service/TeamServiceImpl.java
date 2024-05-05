package com.cricket.simulator.team.service;


import com.cricket.simulator.team.dto.TeamPlayerResDto;
import com.cricket.simulator.team.dto.TeamResDto;
import com.cricket.simulator.team.Exception.TeamNotFound;
import com.cricket.simulator.team.entity.Team;
import com.cricket.simulator.team.mapper.TeamMapper;
import com.cricket.simulator.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final RestTemplate restTemplate;

    @Value("${player-service.url}")
    private String playerServiceUrl;

    private String FIND_BY_TEAM_ID = "/find-by-team-id?teamId=%s";

    @SneakyThrows
    @KafkaListener(topics = "rollback-team-event-topics2",groupId = "rollback-team-event-consumers")
    public void processProductEvents(String teamId) {
        log.info("rollback-team {} ",teamId);
        teamRepository.deleteById(Long.valueOf(teamId));
    }

    public TeamResDto saveTeam(Team team) {
        return teamMapper.toDto(teamRepository.save(team));
    }

    public TeamResDto findByTeamId(Long teamId) throws  TeamNotFound {
        Team team= getByTeamId(teamId);
        return teamMapper.toDto(team);
    }

    public TeamResDto deleteById(Long teamId) throws  TeamNotFound {
        Team team= getByTeamId(teamId);
        teamRepository.deleteById(team.getTeamId());
        return  TeamResDto.builder().message("deleted successfully").teamName(team.getTeamName()).id(team.getTeamId())  .build() ;
    }

    @Override
    public List<TeamPlayerResDto> getPlayerByTeam(Long teamId) throws TeamNotFound {
        getByTeamId(teamId);
       log.info("calling players API with team Id {} ",teamId);
        List<TeamPlayerResDto> teamPlayerResDto = restTemplate.exchange(playerServiceUrl.concat(String.format(FIND_BY_TEAM_ID,teamId)) ,HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TeamPlayerResDto>>() {}).getBody();
        return teamPlayerResDto;
    }

    private Team getByTeamId(Long teamId) throws TeamNotFound{

        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFound("team not found "+ teamId));
    }

}
