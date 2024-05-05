package com.cricket.simulator.players.service;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import com.cricket.simulator.players.dto.*;


import com.cricket.simulator.players.exception.PlayerNotFound;
import com.cricket.simulator.players.mapper.PlayerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cricket.simulator.players.entity.Player;
import com.cricket.simulator.players.repository.PlayersRepo;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

	private final RestTemplate restTemplate;
	private final PlayersRepo playerRepository;
	private final KafkaTemplate<String, Object> kafkaTemplate;
	private final PlayerMapper playerMapper;

	@Value("${team-service.url}")
	private String teamSerUrl;

	private String FIND_BY_ID = "find-by-id?teamId=%s";

	@Override
	@Transactional
	public PlayerResDto createPlayer(PlayerCreateDto playerCreateDto) throws Exception, PlayerNotFound {
		// TODO Auto-generated method stub
		Long teamId = playerCreateDto.getTeamId();
		String url = String.format(teamSerUrl.concat(FIND_BY_ID), teamId);
		log.info("create player method started {} ", url);
		Optional<TeamResponseDto> result = Optional.empty();
		try {
			try {
				result = Optional.of(restTemplate.getForObject(url, TeamResponseDto.class));
			} catch (Exception e) {
				log.error(" error calling teams API {} ", teamId);
				result = result.isPresent() ? result : Optional.of(restTemplate.postForEntity(new URI(teamSerUrl), TeamDto.builder().teamName(UUID.randomUUID().toString()).build(), TeamResponseDto.class).getBody());

			}
			Player player = playerMapper.dtoToEntity(playerCreateDto);
			player.setTeamId(result.get().getId());
			log.info("create player method ended ");
			return PlayerResDto.builder().msg("created").id(playerRepository.save(player).getPlayerId()).build();
		} catch (Exception ex) {
			if (!result.isEmpty()) {
				log.error("player not saved so reverting team {}", result.get().getId());
				kafkaTemplate.send("rollback-team-event-topics2", result.get().getId());
				log.error("create player method failed ");
			}
			log.error("player not saved");
			return PlayerResDto.builder().msg("Failed").build();
		}


	}

	@Override
	public List<PlayerDto> getPlayerByTeamId(Long teamId) throws PlayerNotFound {
		return Optional.ofNullable(playerRepository.findByTeamId(teamId)).filter(players -> !players.isEmpty()).orElseThrow(
				() -> new PlayerNotFound("player Not found for teamId "+teamId)).stream().map(playerMapper::entityToDto).collect(Collectors.toList());

	}

}
