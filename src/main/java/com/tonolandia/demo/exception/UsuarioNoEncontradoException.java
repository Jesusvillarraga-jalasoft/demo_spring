package com.tonolandia.demo.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super(String.valueOf(id));
    }
}
