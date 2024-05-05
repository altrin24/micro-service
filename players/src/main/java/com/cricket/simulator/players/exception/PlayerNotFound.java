package com.cricket.simulator.players.exception;

public class PlayerNotFound extends Exception {

    // Constructor that accepts a message
    public PlayerNotFound(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public PlayerNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
