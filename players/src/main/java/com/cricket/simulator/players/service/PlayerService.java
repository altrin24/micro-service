package com.cricket.simulator.players.service;

import com.cricket.simulator.players.dto.PlayerCreateDto;
import com.cricket.simulator.players.dto.PlayerDto;
import com.cricket.simulator.players.dto.PlayerResDto;
import com.cricket.simulator.players.entity.Player;
import com.cricket.simulator.players.exception.PlayerNotFound;

import java.util.List;

public interface PlayerService {

	public PlayerResDto createPlayer(PlayerCreateDto playerCreateDto)  throws Exception, PlayerNotFound
			;
	public List<PlayerDto> getPlayerByTeamId(Long teamId) throws PlayerNotFound;

}
