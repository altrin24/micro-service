package com.cricket.simulator.matches.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerResDto {
    private Long playerId;
    private Long teamId;
    private String playerName;
    private Long age;
    private Long totalRun;
    private Long totalSix;
    private Long totalFour;
    private Long totalWicket;
    private Long totalMatch;
    private boolean isCaptain;
    private String teamName;

}
