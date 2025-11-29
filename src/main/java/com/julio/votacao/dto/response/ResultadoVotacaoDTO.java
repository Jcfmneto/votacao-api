package com.julio.votacao.dto.response;

public record ResultadoVotacaoDTO(
    int votosSim,
    int votosNao,
    int totalVotos,
    String aprovada) {
}
