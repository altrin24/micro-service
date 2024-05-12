package com.cricket.simulator.matches.exception;

import lombok.NoArgsConstructor;


public class MatchDetailsNotFound extends Exception{

    public MatchDetailsNotFound () {
        super("MatchDetails Not Found");
    }
    // Constructor that accepts a message
    public MatchDetailsNotFound (String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public MatchDetailsNotFound (String message, Throwable cause) {
        super(message, cause);
    }
}