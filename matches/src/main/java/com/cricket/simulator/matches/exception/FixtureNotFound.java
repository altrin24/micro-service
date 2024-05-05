package com.cricket.simulator.matches.exception;


public class FixtureNotFound extends Exception{

    // Constructor that accepts a message
    public FixtureNotFound(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public FixtureNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
