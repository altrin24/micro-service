package com.cricket.simulator.players.mapper;

import com.cricket.simulator.players.dto.PlayerCreateDto;
import com.cricket.simulator.players.dto.PlayerDto;
import com.cricket.simulator.players.entity.Player;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PlayerMapper {

    public abstract Player dtoToEntity(PlayerCreateDto playerCreateDto);
    public abstract PlayerDto entityToDto(Player player);
}
