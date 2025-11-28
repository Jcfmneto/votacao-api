package com.julio.votacao.exception.type;

public class AssociadoAlreadyExistsException extends RuntimeException {

    public AssociadoAlreadyExistsException(String cpf) {
        super("Associado com CPF: " + cpf + " já existe");
    }
}
