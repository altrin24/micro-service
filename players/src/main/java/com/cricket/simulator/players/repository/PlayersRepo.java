package com.cricket.simulator.players.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cricket.simulator.players.entity.Player;

import java.util.List;

public interface PlayersRepo extends JpaRepository<Player, Long> {

    List<Player> findByTeamId(Long teamId);

}
