package com.cricket.simulator.players.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PlayerDto {

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
