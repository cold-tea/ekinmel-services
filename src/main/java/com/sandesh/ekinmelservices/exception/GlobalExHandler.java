package com.sandesh.ekinmelservices.exception;

import com.sandesh.ekinmelservices.models.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Status> globalExceptionThrown(Exception ex) {
        Status status = new Status("Exception occured in Ekinmel Service",
                ex.getMessage(), "Unknown Operation");
        System.out.println("Exception thrown:  " + status.getExMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
    }
}
