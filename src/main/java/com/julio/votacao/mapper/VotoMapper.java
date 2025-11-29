package com.julio.votacao.mapper;

import com.julio.votacao.dto.request.VotoRequestDTO;
import com.julio.votacao.dto.response.VotoResponseDTO;
import com.julio.votacao.dto.response.VotoResumoResponseDTO;
import com.julio.votacao.model.Associado;
import com.julio.votacao.model.Sessao;
import com.julio.votacao.model.Voto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VotoMapper {

  public VotoResponseDTO toDTO(Voto voto) {
    return new VotoResponseDTO(
        voto.getId(),
        voto.getVoto(),
        voto.getSessao().getId(),
        voto.getAssociado().getId());
  }

  public List<VotoResponseDTO> toDTOList(List<Voto> votos) {
    return votos.stream().map(this::toDTO).toList();
  }

  public Voto toEntity(VotoRequestDTO dto, Sessao sessao, Associado associado) {
    return new Voto(sessao, associado, dto.escolha());
  }

  public VotoResumoResponseDTO toResumoDTO(Voto voto) {
    return new VotoResumoResponseDTO(
        voto.getSessao().getId(),
        voto.getVoto());
  }

  public List<VotoResumoResponseDTO> toResumoDTOList(List<Voto> votos) {
    return votos.stream().map(this::toResumoDTO).toList();
  }

}
