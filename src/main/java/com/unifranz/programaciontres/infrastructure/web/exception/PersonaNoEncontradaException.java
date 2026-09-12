package com.unifranz.programaciontres.infrastructure.web.exception;

public class PersonaNoEncontradaException extends RuntimeException {
    public PersonaNoEncontradaException(String message) {
        super(message);
    }
}
