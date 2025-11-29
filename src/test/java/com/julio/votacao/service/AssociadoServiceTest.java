package com.julio.votacao.service;

import com.julio.votacao.dto.request.AssociadoRequestDTO;
import com.julio.votacao.dto.response.AssociadoResponseDTO;
import com.julio.votacao.exception.type.AssociadoAlreadyExistsException;
import com.julio.votacao.mapper.AssociadoMapper;
import com.julio.votacao.model.Associado;
import com.julio.votacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssociadoServiceTest {

  private final AssociadoRepository repository = mock(AssociadoRepository.class);
  private final AssociadoMapper mapper = mock(AssociadoMapper.class);
  private final AssociadoService service = new AssociadoService(repository, mapper);

  @Test
  void deveCriarAssociadoComSucesso() {
    AssociadoRequestDTO dto = new AssociadoRequestDTO("Julio", "60302158090");

    Associado entity = new Associado();
    entity.setId(1L);
    entity.setCpf("60302158090");
    entity.setNome("Julio");

    when(repository.findByCpf("60302158090")).thenReturn(Optional.empty());
    when(mapper.toEntity(dto)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entity);
    when(mapper.toDTO(entity)).thenReturn(
        new AssociadoResponseDTO(1L, "Julio", "60302158090", null));

    AssociadoResponseDTO response = service.create(dto);

    assertThat(response.id()).isEqualTo(1L);
    assertThat(response.nome()).isEqualTo("Julio");
    assertThat(response.cpf()).isEqualTo("60302158090");
    assertThat(response.votos()).isNull();
  }

  @Test
  void naoDevePermitirCpfDuplicado() {
    when(repository.findByCpf("60302158090"))
        .thenReturn(Optional.of(new Associado()));

    assertThatThrownBy(() -> service.create(new AssociadoRequestDTO("Julio", "60302158090")))
        .isInstanceOf(AssociadoAlreadyExistsException.class);
  }
}
