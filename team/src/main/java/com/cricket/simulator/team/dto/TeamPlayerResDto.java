package com.cricket.simulator.team.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class TeamPlayerResDto {

    private Long playerId;
    private Long teamId;
    private String playerName;
    private Long age;
    private Long totalRun;
    private Long totalSix;
    private Long totalFour;
    private Long totalWicket;
    private Long totalMatch;
    private Boolean isCaptain;
    private String teamName;
}
