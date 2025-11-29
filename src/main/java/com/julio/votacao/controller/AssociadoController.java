package com.julio.votacao.controller;

import com.julio.votacao.dto.request.AssociadoRequestDTO;
import com.julio.votacao.dto.response.AssociadoResponseDTO;
import com.julio.votacao.service.AssociadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/associado")
public class AssociadoController {

  private final AssociadoService associadoService;

  public AssociadoController(AssociadoService associadoService) {
    this.associadoService = associadoService;
  }

  @PostMapping
  public ResponseEntity<AssociadoResponseDTO> create(@Valid @RequestBody AssociadoRequestDTO dto) {
    AssociadoResponseDTO created = associadoService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @GetMapping
  public ResponseEntity<List<AssociadoResponseDTO>> getAll() {
    List<AssociadoResponseDTO> list = associadoService.findAll();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AssociadoResponseDTO> getById(@PathVariable Long id) {
    AssociadoResponseDTO associado = associadoService.findById(id);
    return ResponseEntity.ok(associado);
  }
}
