package com.julio.votacao.service;

import com.julio.votacao.dto.request.PautaRequestDTO;
import com.julio.votacao.dto.response.PautaResponseDTO;
import com.julio.votacao.exception.type.PautaNotFoundException;
import com.julio.votacao.mapper.PautaMapper;
import com.julio.votacao.model.Pauta;
import com.julio.votacao.repository.PautaRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PautaServiceTest {

  private final PautaRepository repository = mock(PautaRepository.class);
  private final PautaMapper mapper = mock(PautaMapper.class);
  private final PautaService service = new PautaService(mapper, repository);

  @Test
  void deveCriarPautaComSucesso() {
    PautaRequestDTO dto = new PautaRequestDTO("Pauta Teste", "Descricao");

    Pauta entity = new Pauta();
    entity.setId(1L);
    entity.setTitulo("Pauta Teste");
    entity.setDescricao("Descricao");

    when(mapper.toEntity(dto)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entity);
    when(mapper.toDTO(entity)).thenReturn(new PautaResponseDTO(1L, "Pauta Teste", "Descricao"));

    PautaResponseDTO response = service.create(dto);

    assertThat(response.id()).isEqualTo(1L);
    assertThat(response.titulo()).isEqualTo("Pauta Teste");
    assertThat(response.descricao()).isEqualTo("Descricao");
  }

  @Test
  void deveLancarExcecaoAoBuscarPautaInexistente() {
    when(repository.findById(9L)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> service.findById(9L))
        .isInstanceOf(PautaNotFoundException.class);
  }
}
