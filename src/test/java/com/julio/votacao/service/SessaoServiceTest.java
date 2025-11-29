package com.julio.votacao.service;

import com.julio.votacao.dto.request.SessaoRequestDTO;
import com.julio.votacao.dto.response.ResultadoVotacaoDTO;
import com.julio.votacao.dto.response.SessaoResponseDTO;
import com.julio.votacao.exception.type.PautaNotFoundException;
import com.julio.votacao.exception.type.SessaoNotFoundException;
import com.julio.votacao.mapper.SessaoMapper;
import com.julio.votacao.model.Pauta;
import com.julio.votacao.model.Sessao;
import com.julio.votacao.repository.PautaRepository;
import com.julio.votacao.repository.SessaoRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessaoServiceTest {

  private final SessaoRepository sessaoRepository = mock(SessaoRepository.class);
  private final PautaRepository pautaRepository = mock(PautaRepository.class);
  private final SessaoMapper mapper = mock(SessaoMapper.class);

  private final SessaoService service = new SessaoService(sessaoRepository, pautaRepository, mapper);

  @Test
  void deveCriarSessaoComSucesso() {
    SessaoRequestDTO dto = new SessaoRequestDTO(1L, 5);

    Pauta pauta = new Pauta();
    pauta.setId(1L);

    Sessao sessao = new Sessao();
    sessao.setId(1L);
    sessao.setPauta(pauta);
    sessao.setDataAbertura(LocalDateTime.now());
    sessao.setDataFechamento(sessao.getDataAbertura().plusMinutes(5));

    when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
    when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);

    when(mapper.toDTO(sessao)).thenReturn(
        new SessaoResponseDTO(
            1L,
            1L,
            sessao.getDataAbertura(),
            sessao.getDataFechamento(),
            null));

    SessaoResponseDTO resp = service.create(dto);

    assertThat(resp.id()).isEqualTo(1L);
    assertThat(resp.pautaId()).isEqualTo(1L);
  }

  @Test
  void deveFalharAoCriarSessaoComPautaInexistente() {
    when(pautaRepository.findById(999L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> service.create(new SessaoRequestDTO(999L, 1)))
        .isInstanceOf(PautaNotFoundException.class);
  }

  @Test
  void deveListarTodasSessoes() {
    Sessao s1 = new Sessao();
    s1.setId(1L);

    Sessao s2 = new Sessao();
    s2.setId(2L);

    when(sessaoRepository.findAll()).thenReturn(List.of(s1, s2));

    when(mapper.toDTO(any())).thenAnswer(inv -> {
      Sessao s = inv.getArgument(0);
      return new SessaoResponseDTO(s.getId(), null, null, null, null);
    });

    List<SessaoResponseDTO> list = service.findAll();

    assertThat(list).hasSize(2);
    assertThat(list.get(0).id()).isEqualTo(1L);
    assertThat(list.get(1).id()).isEqualTo(2L);
  }

  @Test
  void deveBuscarSessaoPorId() {
    Sessao sessao = new Sessao();
    sessao.setId(1L);

    when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));
    when(mapper.toDTO(sessao))
        .thenReturn(new SessaoResponseDTO(1L, null, null, null, null));

    SessaoResponseDTO resp = service.findById(1L);

    assertThat(resp.id()).isEqualTo(1L);
  }

  @Test
  void deveFalharQuandoSessaoNaoExiste() {
    when(sessaoRepository.findById(10L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> service.findById(10L))
        .isInstanceOf(SessaoNotFoundException.class);
  }

  @Test
  void deveRetornarResultadoDaSessao() {
    Sessao sessao = new Sessao();
    sessao.setId(1L);

    ResultadoVotacaoDTO resultado = new ResultadoVotacaoDTO(2, 1, 3, "Sim");

    when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));
    when(mapper.toResultadoDTO(sessao)).thenReturn(resultado);

    ResultadoVotacaoDTO resp = service.getResult(1L);

    assertThat(resp.votosSim()).isEqualTo(2);
    assertThat(resp.votosNao()).isEqualTo(1);
    assertThat(resp.totalVotos()).isEqualTo(3);
    assertThat(resp.aprovada()).isEqualTo("Sim");
  }

  @Test
  void deveFalharAoBuscarResultadoDeSessaoInexistente() {
    when(sessaoRepository.findById(10L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> service.getResult(10L))
        .isInstanceOf(SessaoNotFoundException.class);
  }
}
