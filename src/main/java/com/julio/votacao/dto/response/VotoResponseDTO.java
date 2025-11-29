package com.julio.votacao.dto.response;

public record VotoResponseDTO(
    Long votoid,
    Boolean escolha,
    Long sessaoId,
    Long associadoId) {
}
