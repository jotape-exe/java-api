package com.jorge.api.service.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmptyListException extends EntityNotFoundException {
    public EmptyListException(String message) {
        super(message);
    }
}
