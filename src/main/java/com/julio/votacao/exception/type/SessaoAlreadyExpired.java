package com.julio.votacao.exception.type;

public class SessaoAlreadyExpired extends RuntimeException {
    public SessaoAlreadyExpired(String message) {

        super("Sessão já foi expirada");
    }
}
