package com.cricket.simulator.team.service;

import com.cricket.simulator.team.dto.TeamPlayerResDto;
import com.cricket.simulator.team.dto.TeamResDto;
import com.cricket.simulator.team.Exception.TeamNotFound;
import com.cricket.simulator.team.entity.Team;

import java.util.List;

public interface TeamService {

    public TeamResDto saveTeam(Team team);

    public TeamResDto findByTeamId(Long teamId)  throws TeamNotFound;

    public TeamResDto deleteById(Long teamId) throws  TeamNotFound;

    public List<TeamPlayerResDto> getPlayerByTeam(Long teamId) throws TeamNotFound;
}
