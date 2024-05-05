package com.cricket.simulator.matches.mapper;

import com.cricket.simulator.matches.dto.FixtureCreateDto;
import com.cricket.simulator.matches.entity.Fixture;
import com.cricket.simulator.matches.entity.MatchDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MatchMapper {

    public abstract Fixture DtoToEntity(FixtureCreateDto fixtureCreateDto);
}
