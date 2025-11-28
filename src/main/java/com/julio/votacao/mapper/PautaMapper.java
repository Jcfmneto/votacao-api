package com.julio.votacao.mapper;

import com.julio.votacao.dto.request.PautaRequestDTO;
import com.julio.votacao.dto.response.PautaResponseDTO;
import com.julio.votacao.model.Pauta;
import org.springframework.stereotype.Component;


@Component
public class PautaMapper {

public Pauta toEntity(PautaRequestDTO dto) {
        return new Pauta(dto.nome());
    }

    public PautaResponseDTO toDTO(Pauta pauta) {
        return new PautaResponseDTO(pauta.getId(), pauta.getNome());
    }
}
