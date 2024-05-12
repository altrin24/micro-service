package com.cricket.simulator.matches.service;

import com.cricket.simulator.matches.dto.PlayerResDto;
import com.cricket.simulator.matches.exception.MatchDetailsNotFound;

import java.util.List;
import java.util.Map;

public interface PlayerService {

    public Map<Long, Integer> getBestPlayer() throws MatchDetailsNotFound;

    public List<PlayerResDto> getBestBowler();

}
