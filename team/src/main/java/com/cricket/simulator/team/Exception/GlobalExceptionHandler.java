package com.cricket.simulator.team.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public TeamErrorRes handleException(TeamNotFound ex) {
        log .error("team not found exception {}",ex.getMessage());
        return TeamErrorRes.builder().msg(ex.getMessage()) .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public TeamErrorRes handleException(Exception ex) {
        log.error("uncaught exception {}",ex.getMessage());
        ex.printStackTrace();
        return TeamErrorRes.builder().msg(ex.getMessage()) .build();
    }
}

