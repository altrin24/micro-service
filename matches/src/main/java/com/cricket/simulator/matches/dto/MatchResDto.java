package com.cricket.simulator.matches.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResDto {
    private Long id;
    private String msg;
}
