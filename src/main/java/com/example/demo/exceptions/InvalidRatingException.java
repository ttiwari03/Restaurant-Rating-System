package com.example.demo.exceptions;

public class InvalidRatingException extends Throwable {
    public InvalidRatingException(String message) {
        super(message);
    }

}
