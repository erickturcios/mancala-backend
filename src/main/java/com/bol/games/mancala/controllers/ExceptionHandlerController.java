package com.bol.games.mancala.controllers;

import com.bol.games.mancala.Exception.ExceptionResponse;
import com.bol.games.mancala.Exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(NotFoundException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(new ExceptionResponse(e.getMessage(), e.getStatus().value()));
    }

}
