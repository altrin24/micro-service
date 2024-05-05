package com.cricket.simulator.matches.exception;

import com.cricket.simulator.matches.dto.MatchResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public MatchResDto handleException(Exception ex) {
          log.info("uncaught exception {}",ex.getMessage());
        return MatchResDto.builder().msg(ex.getMessage()) .build();
    }

}
