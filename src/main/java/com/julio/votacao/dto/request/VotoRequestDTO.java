package com.julio.votacao.dto.request;

import jakarta.validation.constraints.NotNull;

public record VotoRequestDTO(
        @NotNull(message = "ID da sessão é obrigatório") Long sessaoId,
        @NotNull(message = "ID do associado é obrigatório") Long associadoId,
        @NotNull(message = "Escolha do voto é obrigatória") Boolean escolha
) {}