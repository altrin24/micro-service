package com.cricket.simulator.matches.resource;


import com.cricket.simulator.matches.dto.MatchResDto;
import com.cricket.simulator.matches.dto.ReqDto;
import com.cricket.simulator.matches.service.FixtureService;
import com.cricket.simulator.matches.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("player")
@RequiredArgsConstructor
@Slf4j
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/best-bats-men")
    public ResponseEntity<Map<Long,Integer>> processMatch() throws Exception {
        return ResponseEntity.ok(playerService.getBestPlayer());
    }
}
