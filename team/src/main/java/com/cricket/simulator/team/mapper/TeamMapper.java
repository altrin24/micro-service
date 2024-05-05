package com.cricket.simulator.team.mapper;


import com.cricket.simulator.team.dto.TeamResDto;
import com.cricket.simulator.team.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class TeamMapper {

    @Mapping(target = "teamName", source = "team.teamName")
    @Mapping(target = "id", source = "team.teamId")
    public abstract TeamResDto toDto(Team team);
}
