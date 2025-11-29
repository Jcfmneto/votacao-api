package com.julio.votacao.service;

import com.julio.votacao.dto.request.SessaoRequestDTO;
import com.julio.votacao.dto.response.SessaoResponseDTO;
import com.julio.votacao.exception.type.PautaNotFoundException;
import com.julio.votacao.exception.type.SessaoNotFoundException;
import com.julio.votacao.mapper.SessaoMapper;
import com.julio.votacao.model.Pauta;
import com.julio.votacao.model.Sessao;
import com.julio.votacao.repository.PautaRepository;
import com.julio.votacao.repository.SessaoRepository;
import org.springframework.stereotype.Service;
import com.julio.votacao.dto.response.ResultadoVotacaoDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessaoService {

  private final SessaoRepository sessaoRepository;
  private final PautaRepository pautaRepository;
  private final SessaoMapper sessaoMapper;

  public SessaoService(
      SessaoRepository sessaoRepository,
      PautaRepository pautaRepository,
      SessaoMapper sessaoMapper) {
    this.sessaoRepository = sessaoRepository;
    this.pautaRepository = pautaRepository;
    this.sessaoMapper = sessaoMapper;
  }

  public SessaoResponseDTO create(SessaoRequestDTO dto) {
    Pauta pauta = pautaRepository.findById(dto.pautaId())
        .orElseThrow(() -> new PautaNotFoundException(dto.pautaId()));
    Sessao sessao = new Sessao();
    sessao.setPauta(pauta);
    LocalDateTime abertura = LocalDateTime.now();
    sessao.setDataAbertura(abertura);
    int minutos = dto.duracaoMinutos() != null ? dto.duracaoMinutos() : 1;
    sessao.setDataFechamento(abertura.plusMinutes(minutos));
    Sessao saved = sessaoRepository.save(sessao);
    return sessaoMapper.toDTO(saved);
  }

  public List<SessaoResponseDTO> findAll() {
    return sessaoRepository.findAll()
        .stream()
        .map(sessaoMapper::toDTO)
        .toList();
  }

  public SessaoResponseDTO findById(Long id) {
    Sessao sessao = sessaoRepository.findById(id)
        .orElseThrow(() -> new SessaoNotFoundException(id));
    return sessaoMapper.toDTO(sessao);
  }

  public ResultadoVotacaoDTO getResult(Long sessaoId) {
    Sessao sessao = sessaoRepository.findById(sessaoId)
        .orElseThrow(() -> new SessaoNotFoundException(sessaoId));
    return sessaoMapper.toResultadoDTO(sessao);
  }
}
