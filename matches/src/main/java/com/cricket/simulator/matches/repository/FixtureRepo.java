package com.cricket.simulator.matches.repository;

import com.cricket.simulator.matches.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixtureRepo extends JpaRepository<Fixture, Long> {

}
