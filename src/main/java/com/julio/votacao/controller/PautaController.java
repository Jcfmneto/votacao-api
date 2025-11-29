package com.julio.votacao.controller;

import com.julio.votacao.dto.request.PautaRequestDTO;
import com.julio.votacao.dto.response.PautaResponseDTO;
import com.julio.votacao.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pauta")
public class PautaController {

  private final PautaService pautaService;

  public PautaController(PautaService pautaService) {
    this.pautaService = pautaService;
  }

  @PostMapping
  public ResponseEntity<PautaResponseDTO> create(@Valid @RequestBody PautaRequestDTO dto) {
    PautaResponseDTO created = pautaService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @GetMapping
  public ResponseEntity<List<PautaResponseDTO>> getAll() {
    List<PautaResponseDTO> pautas = pautaService.findAll();
    return ResponseEntity.ok(pautas);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PautaResponseDTO> getById(@PathVariable("id") Long id) {
    PautaResponseDTO pauta = pautaService.findById(id);
    return ResponseEntity.ok(pauta);
  }

}
