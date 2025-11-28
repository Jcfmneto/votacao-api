package com.julio.votacao.exception.type;

public class PautaNotFoundException extends RuntimeException {
    public PautaNotFoundException(Long id) {
        super("Pauta não encontrada: " + id);
    }
}
