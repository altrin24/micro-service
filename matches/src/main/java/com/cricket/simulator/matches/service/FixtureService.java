package com.cricket.simulator.matches.service;

import com.cricket.simulator.matches.dto.FixtureCreateDto;
import com.cricket.simulator.matches.dto.MatchResDto;

public interface FixtureService {
    MatchResDto saveFixtures(FixtureCreateDto fixtureCreateDto);
}
