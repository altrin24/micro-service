package com.cricket.simulator.matches.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FixtureCreateDto {
    private Long teamId;
    private Long awayTeamId;
    private Long overMatch;
    private Long homeTeamId;
    private Long homeTeamTotalRun;
    private Long awayTeamTotalRun;
    private Long winTeamId;
    private String groundName;
    private String weatherStatus;
    private Long tossWinTeamId;
}
