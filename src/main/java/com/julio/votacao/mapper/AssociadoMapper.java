package com.julio.votacao.mapper;

import com.julio.votacao.dto.request.AssociadoRequestDTO;
import com.julio.votacao.dto.response.AssociadoResponseDTO;
import com.julio.votacao.model.Associado;
import org.springframework.stereotype.Component;

@Component
public class AssociadoMapper {

    public Associado toEntity(AssociadoRequestDTO dto) {
        Associado associado = new Associado();
        associado.setCpf(dto.cpf());
        associado.setNome(dto.nome());
        return associado;
    }
    public AssociadoResponseDTO toDTO(Associado associado) {
        return new AssociadoResponseDTO(associado.getId(), associado.getCpf(), associado.getNome());
    }
}
