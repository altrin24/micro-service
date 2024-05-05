package com.cricket.simulator.players.entity;

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
@Table(name = "player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long playerId;

	@Column(name = "team_id")
	private Long teamId;

	@Column(name = "player_name")
	private String playerName;

	@Column(name = "age")
	private Long age;

	@Column(name = "total_run")
	private Long totalRun;

	@Column(name = "total_six")
	private Long totalSix;

	@Column(name = "total_four")
	private Long totalFour;

	@Column(name = "total_wicket")
	private Long totalWicket;

	@Column(name = "total_match")
	private Long totalMatch;

	@Column(name = "is_captain")
	private boolean isCaptain;

}
