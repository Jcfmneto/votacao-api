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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class VotoServiceTest {

  private VotoRepository votoRepository;
  private AssociadoRepository associadoRepository;
  private SessaoRepository sessaoRepository;
  private VotoMapper votoMapper;
  private VotoService votoService;

  private VotoRequestDTO request;
  private Sessao sessao;
  private Associado associado;
  private Voto voto;
  private VotoResponseDTO response;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    votoRepository = mock(VotoRepository.class);
    associadoRepository = mock(AssociadoRepository.class);
    sessaoRepository = mock(SessaoRepository.class);
    votoMapper = mock(VotoMapper.class);

    votoService = new VotoService(
        votoRepository,
        associadoRepository,
        sessaoRepository,
        votoMapper);

    request = new VotoRequestDTO(1L, 1L, true);

    sessao = new Sessao();
    sessao.setId(1L);
    sessao.setDataAbertura(LocalDateTime.now().minusMinutes(1));
    sessao.setDataFechamento(LocalDateTime.now().plusMinutes(10));

    associado = new Associado();
    associado.setId(1L);

    voto = new Voto();
    voto.setId(1L);

    response = new VotoResponseDTO(1L, true, 1L, 1L);
  }

  @Test
  void deveRegistrarVotoComSucesso() {
    when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));
    when(associadoRepository.findById(1L)).thenReturn(Optional.of(associado));
    when(votoRepository.findBySessaoIdAndAssociadoId(1L, 1L)).thenReturn(Optional.empty());
    when(votoMapper.toEntity(request, sessao, associado)).thenReturn(voto);
    when(votoRepository.save(voto)).thenReturn(voto);
    when(votoMapper.toDTO(voto)).thenReturn(response);

    VotoResponseDTO result = votoService.castVote(request);

    assertThat(result).isEqualTo(response);
  }

  @Test
  void deveFalharQuandoSessaoNaoExiste() {
    when(sessaoRepository.findById(1L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> votoService.castVote(request))
        .isInstanceOf(SessaoNotFoundException.class);
  }

  @Test
  void deveFalharQuandoSessaoEstiverExpirada() {
    sessao.setDataFechamento(LocalDateTime.now().minusMinutes(1));

    when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));

    assertThatThrownBy(() -> votoService.castVote(request))
        .isInstanceOf(SessaoAlreadyExpired.class);
  }

  @Test
  void deveFalharQuandoAssociadoNaoExiste() {
    when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));
    when(associadoRepository.findById(1L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> votoService.castVote(request))
        .isInstanceOf(AssociadoNotFoundException.class);
  }

  @Test
  void deveFalharQuandoAssociadoJaVotou() {
    when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));
    when(associadoRepository.findById(1L)).thenReturn(Optional.of(associado));
    when(votoRepository.findBySessaoIdAndAssociadoId(1L, 1L))
        .thenReturn(Optional.of(voto));

    assertThatThrownBy(() -> votoService.castVote(request))
        .isInstanceOf(AssociadoAlreadyVoted.class);
  }

  @Test
  void deveListarVotosPorSessao() {
    when(votoRepository.findBySessaoId(1L)).thenReturn(List.of(voto));
    when(votoMapper.toDTO(voto)).thenReturn(response);

    List<VotoResponseDTO> result = votoService.listVotesBySession(1L);

    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isEqualTo(response);
  }

  @Test
  void deveListarVotosPorAssociado() {
    when(votoRepository.findByAssociadoId(1L)).thenReturn(List.of(voto));
    when(votoMapper.toDTO(voto)).thenReturn(response);

    List<VotoResponseDTO> result = votoService.listVotesByMember(1L);

    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isEqualTo(response);
  }
}
