package com.julio.votacao.dto.response;

import java.util.List;

public record AssociadoResponseDTO(
    Long id,
    String nome,
    String cpf,
    List<VotoResumoResponseDTO> votos) {
}
