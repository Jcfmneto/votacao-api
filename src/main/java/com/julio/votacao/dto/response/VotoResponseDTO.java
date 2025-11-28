package com.julio.votacao.dto.response;


public record VotoResponseDTO(

        Long id,
        Boolean escolha,
        Long sessaoId,
        Long associadoId
) {}