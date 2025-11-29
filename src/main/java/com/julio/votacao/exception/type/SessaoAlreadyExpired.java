package com.julio.votacao.exception.type;

public class SessaoAlreadyExpired extends RuntimeException {
  public SessaoAlreadyExpired() {

    super("Sessão já foi expirada");
  }
}
