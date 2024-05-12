package com.cricket.simulator.matches.mapper;

import com.cricket.simulator.matches.constants.WeatherStatus;
import com.cricket.simulator.matches.dto.FixtureCreateDto;
import com.cricket.simulator.matches.entity.Fixture;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MatchMapper {
    @Mapping(target = "tossWinTeamId", source = "tossWinTeamId.id")
    @Mapping(target = "weatherStatus", source = "weatherStatus")
    @Mapping(target = "groundName", source = "groundName.groundName")
    @Mapping(target = "awayTeamTotalRun", source = "awayTeamTotalRun.run")
    @Mapping(target = "homeTeamTotalRun", source = "homeTeamTotalRun.run")
    @Mapping(target = "homeTeamId", source = "homeTeamId.id")
    @Mapping(target = "overMatch", source = "overMatch.over")
    @Mapping(target = "awayTeamId", source = "awayTeamId.id")
    @Mapping(target = "teamId", source = "teamId.id")
    @Mapping(target = "winTeamId", source = "winTeamId.id")
    public abstract Fixture DtoToEntity(FixtureCreateDto fixtureCreateDto);
}
