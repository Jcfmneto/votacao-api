package com.julio.votacao.dto.request;

import java.time.LocalDateTime;

public record SessaoRequestDTO(
        Long pautaId,
        Integer duracaoMinutos
) {}