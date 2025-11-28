package com.julio.votacao.exception.type;

public class SessaoNotFoundException extends RuntimeException {
    public SessaoNotFoundException(Long id) {
        super("Sessão não encontrada: " + id);
    }

}