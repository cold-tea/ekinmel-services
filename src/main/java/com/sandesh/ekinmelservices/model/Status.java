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

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExMessage() {
        return exMessage;
    }

    public void setExMessage(String exMessage) {
        this.exMessage = exMessage;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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
