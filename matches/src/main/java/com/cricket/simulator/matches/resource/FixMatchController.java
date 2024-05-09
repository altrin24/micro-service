package com.cricket.simulator.matches.resource;

import com.cricket.simulator.matches.dto.FixtureCreateDto;
import com.cricket.simulator.matches.dto.MatchResDto;
import com.cricket.simulator.matches.dto.ReqDto;
import com.cricket.simulator.matches.service.FixtureService;
import com.cricket.simulator.matches.service.MatchService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fixture")
@RequiredArgsConstructor
@Slf4j
public class FixMatchController {

    private final FixtureService fixtureService;
    private final MatchService matchService;

    @PostMapping("/")
    public ResponseEntity<MatchResDto> saveFixture(@RequestBody FixtureCreateDto fixtureCreateDto) throws Exception {
        return ResponseEntity.ok(fixtureService.saveFixtures(fixtureCreateDto));
    }

    @PostMapping("/generate")
    public ResponseEntity<MatchResDto> processMatch(@Valid @RequestBody ReqDto reqDto) throws Exception {
        return ResponseEntity.ok(matchService.processMatch(reqDto.getFixtureId()));
    }


}
