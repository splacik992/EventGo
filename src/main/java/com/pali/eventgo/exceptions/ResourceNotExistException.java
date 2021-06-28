package com.pali.eventgo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotExistException extends RuntimeException {


    public ResourceNotExistException(String message) {
        super(message);
    }
    public ResourceNotExistException(String message, Throwable cause) {
        super(message,cause);
    }


}
