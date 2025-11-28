package com.julio.votacao.service;

import com.julio.votacao.dto.request.AssociadoRequestDTO;
import com.julio.votacao.dto.response.AssociadoResponseDTO;
import com.julio.votacao.exception.type.AssociadoAlreadyExistsException;
import com.julio.votacao.exception.type.AssociadoNotFoundException;
import com.julio.votacao.mapper.AssociadoMapper;
import com.julio.votacao.model.Associado;
import com.julio.votacao.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociadoService {

   private final AssociadoRepository  associadoRepository;
   private final AssociadoMapper  mapper;

    public AssociadoService(AssociadoRepository associadoRepository, AssociadoMapper mapper) {
        this.associadoRepository = associadoRepository;
        this.mapper = mapper;
    }

    public AssociadoResponseDTO create(AssociadoRequestDTO dto) {
        associadoRepository.findByCpf(dto.cpf()).ifPresent(a -> {
            throw new AssociadoAlreadyExistsException(dto.cpf());
        });
        Associado associado = mapper.toEntity(dto);
        Associado saved = associadoRepository.save(associado);
        return mapper.toDTO(saved);
    }

    public List<AssociadoResponseDTO> findAll() {
        return associadoRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public AssociadoResponseDTO findById(Long id) {
        Associado associado = associadoRepository.findById(id)
                .orElseThrow(() -> new AssociadoNotFoundException(id));
        return mapper.toDTO(associado);
                    }

}
