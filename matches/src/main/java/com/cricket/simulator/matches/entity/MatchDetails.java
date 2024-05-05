package com.cricket.simulator.matches.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "match_details")
@Builder
public class MatchDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "match_id")
    private Long matchId;
    @ManyToOne
    @JoinColumn(name = "fixture_id")
    private Fixture fixtureId;
    @Column(name = "batting_team_id")
    private Long battingTeamId;
    @Column(name = "batting_player_id")
    private Long battingPlayerId;
    @Column(name = "bowling_player_id")
    private Long bowlingPlayerId;
    @Column(name = "bowling_team_id")
    private Long bowlingTeamId;
    @Column(name = "ball_no")
    private Long ballNo;
    @Column(name = "over_no")
    private Long overNo;
    @Column(name = "is_no_ball")
    private boolean isNoBall;
    @Column(name = "is_wide_ball")
    private boolean isWideBall;
    @Column(name = "is_wicket")
    private boolean isWicket;
    @Column(name = "run")
    private Integer run;

}
