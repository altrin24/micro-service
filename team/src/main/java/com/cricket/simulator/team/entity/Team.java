package com.cricket.simulator.team.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teamId;

	@Column(name = "team_name")
	private String teamName;

	@Column(name = "team_win_percentage")
	private Integer teamWinPercentage;

	@Column(name = "team_home_ground")
	private String teamHomeground;

	@Column(name = "team_manager")
	private String teamManager;

	@Column(name = "status")
	private String status;





}
