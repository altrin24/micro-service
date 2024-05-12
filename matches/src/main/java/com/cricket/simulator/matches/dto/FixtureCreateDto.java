package com.cricket.simulator.matches.dto;

import com.cricket.simulator.matches.constants.WeatherStatus;
import com.cricket.simulator.matches.valueobjects.GroundName;
import com.cricket.simulator.matches.valueobjects.OverPerMatch;
import com.cricket.simulator.matches.valueobjects.Run;
import com.cricket.simulator.matches.valueobjects.TeamId;
import lombok.Data;

@Data
public class FixtureCreateDto {
    private TeamId teamId;
    private TeamId awayTeamId;
    private OverPerMatch overMatch;
    private TeamId homeTeamId;
    private Run homeTeamTotalRun;
    private Run awayTeamTotalRun;
    private TeamId winTeamId;
    private GroundName groundName;
    private WeatherStatus weatherStatus;
    private TeamId tossWinTeamId;
}
