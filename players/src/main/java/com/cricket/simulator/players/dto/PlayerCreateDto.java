package com.cricket.simulator.players.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PlayerCreateDto {
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
}
