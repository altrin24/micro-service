package com.cricket.simulator.matches.dto;

import com.cricket.simulator.matches.entity.Fixture;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Data
@Builder
public class ProcessMatchDto {
    private Integer over ;
    private Fixture fixture;
    private Long batTeamId ;
    private Long bowlTeamId;
    private AtomicInteger wicket;
    private List<PlayerResDto> bowlingPlayerId;
    private List<PlayerResDto> batPlayerId;
    private AtomicInteger totalRun;
    private AtomicInteger currBatsManIndex;
}
