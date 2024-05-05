package com.cricket.simulator.team.Exception;

public class TeamNotFound extends Exception{

    // Constructor that accepts a message
    public TeamNotFound(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public TeamNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
