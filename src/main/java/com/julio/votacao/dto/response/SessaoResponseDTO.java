package com.julio.votacao.dto.response;

import java.time.LocalDateTime;

public record SessaoResponseDTO(
        Long id,
        Long pautaId,
        LocalDateTime dataAbertura,
        LocalDateTime dataFechamento
) {}
