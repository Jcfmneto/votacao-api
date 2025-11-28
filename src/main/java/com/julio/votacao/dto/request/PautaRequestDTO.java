package com.julio.votacao.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PautaRequestDTO(
        @NotBlank(message = "Titulo é obrigatório")
        String titulo,
        String descricao) {
}
