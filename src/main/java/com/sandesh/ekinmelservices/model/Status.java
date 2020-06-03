package com.sandesh.ekinmelservices.model;

import java.io.Serializable;

public class Status implements Serializable {
    private String message;
    private String exMessage;
    private String operation;

    public Status() {}
    public Status(String message) {
        this.message = message;
    }
    public Status(String message, String exMessage, String operation) {
        this(message);
        this.exMessage = exMessage;
        this.operation = operation;
    }

    public String getMessage() {
        return message;
    }

    public Status setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getExMessage() {
        return exMessage;
    }

    public Status setExMessage(String exMessage) {
        this.exMessage = exMessage;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public Status setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    @Override
    public String toString() {
        return "Status{" +
                "message='" + message + '\'' +
                ", exMessage='" + exMessage + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
