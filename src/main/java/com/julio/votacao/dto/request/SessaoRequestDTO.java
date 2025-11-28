package com.julio.votacao.dto.request;


public record SessaoRequestDTO(
        Long pautaId,
        Integer duracaoMinutos
) {}