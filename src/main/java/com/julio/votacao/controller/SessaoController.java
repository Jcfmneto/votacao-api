package com.julio.votacao.controller;

import com.julio.votacao.dto.request.SessaoRequestDTO;
import com.julio.votacao.dto.response.SessaoResponseDTO;
import com.julio.votacao.service.SessaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessao")
public class SessaoController {

    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping
    public ResponseEntity<SessaoResponseDTO> create(@RequestBody SessaoRequestDTO dto) {
        SessaoResponseDTO created = sessaoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<SessaoResponseDTO>> get() {
        List<SessaoResponseDTO> list = sessaoService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sessaoService.findById(id));
    }
}
