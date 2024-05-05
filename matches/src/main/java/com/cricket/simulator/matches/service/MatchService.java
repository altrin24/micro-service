package com.cricket.simulator.matches.service;

import com.cricket.simulator.matches.dto.MatchResDto;
import com.cricket.simulator.matches.exception.FixtureNotFound;

public interface MatchService {

    public MatchResDto processMatch(Long fixtureId) throws FixtureNotFound;
}
