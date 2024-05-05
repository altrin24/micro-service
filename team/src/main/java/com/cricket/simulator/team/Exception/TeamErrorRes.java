package com.cricket.simulator.team.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class TeamErrorRes {

    private String msg;
}
