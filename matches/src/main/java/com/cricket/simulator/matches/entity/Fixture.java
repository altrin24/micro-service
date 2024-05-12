package com.cricket.simulator.matches.entity;

import com.cricket.simulator.matches.constants.WeatherStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "fixture")
public class Fixture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fix_id")
    private Long fixId;
    @Column(name = "team_id")
    private Long teamId;
    @Column(name = "away_team_id")
    private Long awayTeamId;
    @Column(name = "overMatch")
    private Long overMatch;
    @Column(name = "home_team_id")
    private Long homeTeamId;
    @Column(name = "home_team_total_run")
    private Long homeTeamTotalRun;
    @Column(name = "away_team_total_run")
    private Long awayTeamTotalRun;
    @Column(name = "win_team_id")
    private Long winTeamId;
    @Column(name = "ground_name")
    private String groundName;
    @Column(name = "weather_status")
    @Enumerated(EnumType.STRING)
    private WeatherStatus weatherStatus;
    @Column(name = "toss_win_team_id")
    private Long tossWinTeamId;

}
