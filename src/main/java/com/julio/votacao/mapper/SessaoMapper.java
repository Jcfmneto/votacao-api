package com.julio.votacao.mapper;

import com.julio.votacao.dto.request.SessaoRequestDTO;
import com.julio.votacao.dto.response.SessaoResponseDTO;
import com.julio.votacao.dto.response.ResultadoVotacaoDTO;
import com.julio.votacao.model.Pauta;
import com.julio.votacao.model.Sessao;
import com.julio.votacao.model.Voto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class SessaoMapper {

  private final VotoMapper votoMapper;

  public SessaoMapper(VotoMapper votoMapper) {
    this.votoMapper = votoMapper;
  }

  public Sessao toEntity(SessaoRequestDTO dto, Pauta pauta) {
    Sessao sessao = new Sessao();
    sessao.setPauta(pauta);

    LocalDateTime abertura = LocalDateTime.now();
    int duracao = dto.duracaoMinutos() != null ? dto.duracaoMinutos() : 1;
    sessao.setDataAbertura(abertura);
    sessao.setDataFechamento(abertura.plusMinutes(duracao));

    return sessao;
  }

  public SessaoResponseDTO toDTO(Sessao sessao) {
    List<Voto> votos = sessao.getVotos() != null ? sessao.getVotos() : Collections.emptyList();
    return new SessaoResponseDTO(
        sessao.getId(),
        sessao.getPauta().getId(),
        sessao.getDataAbertura(),
        sessao.getDataFechamento(),
        votoMapper.toDTOList(votos));
  }

  public ResultadoVotacaoDTO toResultadoDTO(Sessao sessao) {
    List<Voto> votos = sessao.getVotos() != null ? sessao.getVotos() : Collections.emptyList();
    long votosSim = votos.stream().filter(v -> Boolean.TRUE.equals(v.getVoto())).count();
    long votosNao = votos.size() - votosSim;
    String resultado;
    if (votosSim > votosNao) {
      resultado = "Sim";
    } else if (votosSim < votosNao) {
      resultado = "Não";
    } else {
      resultado = "Empate";
    }
    return new ResultadoVotacaoDTO((int) votosSim, (int) votosNao, votos.size(), resultado);
  }
}
