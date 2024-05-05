package com.cricket.simulator.players.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

    private Long teamId;
    private String teamName;
    private Integer teamWinPercentage;
    private String teamHomeground;
    private String teamManager;
    private String status;
}
