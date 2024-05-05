package com.cricket.simulator.matches.repository;

import com.cricket.simulator.matches.entity.MatchDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchDetailsRepo extends JpaRepository<MatchDetails, Long> {

}
