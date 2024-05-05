package com.cricket.simulator.team.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResDto {

    private Long id;

    private String teamName;

    private String message;
}
