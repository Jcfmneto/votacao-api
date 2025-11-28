package com.julio.votacao.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AssociadoRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "CPF é obrigatório")
        String cpf
) {}
