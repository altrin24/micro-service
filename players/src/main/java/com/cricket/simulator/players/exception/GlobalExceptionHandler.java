package com.cricket.simulator.players.exception;

import com.cricket.simulator.players.dto.PlayerErrorResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public PlayerErrorResDto handleException(PlayerNotFound ex) {
     //   log.info("player team not found exception {}",ex.getMessage());
        return PlayerErrorResDto.builder().msg(ex.getMessage()) .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public PlayerErrorResDto handleException(Exception ex) {
      //  log.info("uncaught exception {}",ex.getMessage());
        return PlayerErrorResDto.builder().msg(ex.getMessage()) .build();
    }
}

