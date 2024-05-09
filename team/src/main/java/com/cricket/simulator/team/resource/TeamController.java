package com.cricket.simulator.team.resource;

import com.cricket.simulator.team.dto.TeamPlayerResDto;
import com.cricket.simulator.team.dto.TeamResDto;
import com.cricket.simulator.team.Exception.TeamNotFound;
import com.cricket.simulator.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;

import com.cricket.simulator.team.entity.Team;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("team")
@Slf4j
@RequiredArgsConstructor
public class TeamController {


	private final  TeamService teamService;

	// Save operation
	@PostMapping("/")
	public ResponseEntity<TeamResDto> saveTeam(@Valid @RequestBody Team team) {
		log.info("team saving in progress {} ",team.getTeamName());
		return ResponseEntity.ok(teamService.saveTeam(team));
	}

	@GetMapping("/find-by-id")
	public ResponseEntity<TeamResDto> getOneTeam(@RequestParam ("teamId") Long teamId) throws TeamNotFound {
		return ResponseEntity.ok(teamService.findByTeamId(teamId));
	}

	@DeleteMapping("/del-by-id")
	public ResponseEntity<TeamResDto> DeleteTeam(@RequestParam Long teamId) throws TeamNotFound {
		 log.info("Team Id {}", teamId);
		 return ResponseEntity.ok(teamService.deleteById(teamId)) ;
	}


    @GetMapping("/find-player-team-id")
    public ResponseEntity<List<TeamPlayerResDto>> getAllPlayerTeam(@RequestParam ("teamId") Long teamId) throws TeamNotFound, InterruptedException {
		Thread.sleep(10000L);
		return ResponseEntity.ok(teamService.getPlayerByTeam(teamId) );
	}

}
