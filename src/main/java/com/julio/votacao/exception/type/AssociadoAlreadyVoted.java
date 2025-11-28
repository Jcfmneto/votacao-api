package com.julio.votacao.exception.type;

public class AssociadoAlreadyVoted extends RuntimeException {

    public AssociadoAlreadyVoted(Long id) {
        super("O Associado: " + id + " já votou");
    }
}
