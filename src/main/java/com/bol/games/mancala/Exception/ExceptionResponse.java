package com.bol.games.mancala.Exception;

public class ExceptionResponse {

    private final String message;
    private final Integer code;

    public ExceptionResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }


}