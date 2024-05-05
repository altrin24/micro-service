package com.cricket.simulator.players.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerResDto {
    private Long id;
    private String msg;
}
