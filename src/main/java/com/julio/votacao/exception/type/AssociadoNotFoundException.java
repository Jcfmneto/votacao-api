package com.julio.votacao.exception.type;

public class AssociadoNotFoundException extends RuntimeException {
    public AssociadoNotFoundException(Long id) {
        super("Associado não encontrado: " + id);
    }
}
