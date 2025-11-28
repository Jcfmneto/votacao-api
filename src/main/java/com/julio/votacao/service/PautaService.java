package com.julio.votacao.service;


import com.julio.votacao.dto.request.PautaRequestDTO;
import com.julio.votacao.dto.response.PautaResponseDTO;
import com.julio.votacao.exception.type.PautaNotFoundException;
import com.julio.votacao.mapper.PautaMapper;
import com.julio.votacao.model.Pauta;
import com.julio.votacao.repository.PautaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaService {

    private final PautaMapper mapper;
    private final PautaRepository pautaRepository;


    public PautaService(PautaMapper mapper, PautaRepository pautaRepository) {
        this.mapper = mapper;
        this.pautaRepository = pautaRepository;
    }

    public PautaResponseDTO create(PautaRequestDTO pautaRequestDTO) {
        Pauta pauta = mapper.toEntity(pautaRequestDTO);
        pautaRepository.save(pauta);
        return mapper.toDTO(pauta);
    }

    public List<PautaResponseDTO> get() {
        return pautaRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
    public PautaResponseDTO getById(Long id) {
       Pauta pauta = pautaRepository.findById(id)
               .orElseThrow(() -> new PautaNotFoundException(id));
       return mapper.toDTO(pauta);
     }

    }


