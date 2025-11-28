package com.julio.votacao.dto.response;



import java.time.LocalDateTime;
import java.util.List;

public record SessaoResponseDTO(
        Long id,
        Long pautaId,
        LocalDateTime dataAbertura,
        LocalDateTime dataFechamento,
        List<VotoResponseDTO> votos
) {}
