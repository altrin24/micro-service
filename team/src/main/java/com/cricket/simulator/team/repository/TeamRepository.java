package com.cricket.simulator.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cricket.simulator.team.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}