package com.cricket.simulator.players.resource;

import com.cricket.simulator.players.dto.PlayerCreateDto;
import com.cricket.simulator.players.dto.PlayerDto;
import com.cricket.simulator.players.dto.PlayerResDto;
import com.cricket.simulator.players.exception.PlayerNotFound;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cricket.simulator.players.service.PlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("player")
@RequiredArgsConstructor
@Slf4j
public class PlayerController {

	private final PlayerService playerServ;

	@PostMapping("/")
	public ResponseEntity <PlayerResDto> Player(@Valid @RequestBody PlayerCreateDto playerCreateDto) throws Exception, PlayerNotFound {
		return ResponseEntity.ok(playerServ.createPlayer(playerCreateDto));
	}

	@GetMapping("/find-by-team-id")
	public ResponseEntity<List<PlayerDto>> getOneTeam(@RequestParam ("teamId") Long teamId) throws PlayerNotFound {
		log.info("searching player by team id {}",teamId);
		return ResponseEntity.ok(playerServ.getPlayerByTeamId(teamId));
	}

}
