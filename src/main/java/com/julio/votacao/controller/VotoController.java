package com.julio.votacao.controller;

import com.julio.votacao.dto.request.VotoRequestDTO;
import com.julio.votacao.dto.response.VotoResponseDTO;
import com.julio.votacao.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voto")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<VotoResponseDTO> votar(@RequestBody @Valid VotoRequestDTO dto) {
        VotoResponseDTO votoDto = votoService.castVote(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(votoDto);
    }

    @GetMapping("/sessao/{sessaoId}")
    public ResponseEntity<List<VotoResponseDTO>> listarPorSessao(@PathVariable Long sessaoId) {
        List<VotoResponseDTO> votos = votoService.listVotesBySession(sessaoId);
        return ResponseEntity.ok(votos);
    }

    @GetMapping("/associado/{associadoId}")
    public ResponseEntity<List<VotoResponseDTO>> listarPorAssociado(@PathVariable Long associadoId) {
        List<VotoResponseDTO> votos = votoService.listVotesByMember(associadoId);
        return ResponseEntity.ok(votos);
    }
}
