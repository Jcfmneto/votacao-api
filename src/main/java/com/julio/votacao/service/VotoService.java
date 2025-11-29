package com.julio.votacao.service;

import com.julio.votacao.dto.request.VotoRequestDTO;
import com.julio.votacao.dto.response.VotoResponseDTO;
import com.julio.votacao.exception.type.AssociadoAlreadyVoted;
import com.julio.votacao.exception.type.AssociadoNotFoundException;
import com.julio.votacao.exception.type.SessaoAlreadyExpired;
import com.julio.votacao.exception.type.SessaoNotFoundException;
import com.julio.votacao.mapper.VotoMapper;
import com.julio.votacao.model.Associado;
import com.julio.votacao.model.Sessao;
import com.julio.votacao.model.Voto;
import com.julio.votacao.repository.AssociadoRepository;
import com.julio.votacao.repository.SessaoRepository;
import com.julio.votacao.repository.VotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VotoService {

  private final VotoRepository votoRepository;
  private final AssociadoRepository associadoRepository;
  private final SessaoRepository sessaoRepository;
  private final VotoMapper votoMapper;

  public VotoService(VotoRepository votoRepository,
      AssociadoRepository associadoRepository,
      SessaoRepository sessaoRepository,
      VotoMapper votoMapper) {
    this.votoRepository = votoRepository;
    this.associadoRepository = associadoRepository;
    this.sessaoRepository = sessaoRepository;
    this.votoMapper = votoMapper;
  }

  @Transactional
  public VotoResponseDTO castVote(VotoRequestDTO dto) {
    Sessao sessao = sessaoRepository.findById(dto.sessaoId())
        .orElseThrow(() -> new SessaoNotFoundException(dto.sessaoId()));

    if (!sessao.isOpen()) {
      throw new SessaoAlreadyExpired();
    }

    Associado associado = associadoRepository.findById(dto.associadoId())
        .orElseThrow(() -> new AssociadoNotFoundException(dto.associadoId()));

    votoRepository.findBySessaoIdAndAssociadoId(dto.sessaoId(), dto.associadoId())
        .ifPresent(v -> {
          throw new AssociadoAlreadyVoted(dto.associadoId());
        });

    Voto voto = votoMapper.toEntity(dto, sessao, associado);
    Voto saved = votoRepository.save(voto);
    return votoMapper.toDTO(saved);
  }

  public List<VotoResponseDTO> listVotesBySession(Long sessaoId) {
    return votoRepository.findBySessaoId(sessaoId)
        .stream()
        .map(votoMapper::toDTO)
        .toList();
  }

  public List<VotoResponseDTO> listVotesByMember(Long associadoId) {
    return votoRepository.findByAssociadoId(associadoId)
        .stream()
        .map(votoMapper::toDTO)
        .toList();
  }
}
