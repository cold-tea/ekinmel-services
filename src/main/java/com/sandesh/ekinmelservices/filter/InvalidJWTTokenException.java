package com.sandesh.ekinmelservices.filter;

public class InvalidJWTTokenException extends RuntimeException{

    public InvalidJWTTokenException(String message) {
        super(message);
    }
}
