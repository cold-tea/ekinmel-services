package com.sandesh.ekinmelservices.exception;

import com.sandesh.ekinmelservices.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExHandler {

    @Autowired
    private Status status;

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Status> exceptionForHttpClientError(HttpClientErrorException ex) {
        status.setExMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Status> globalExceptionThrown(Exception ex) {
        status.setExMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
    }
}
