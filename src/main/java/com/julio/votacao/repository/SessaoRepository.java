package com.julio.votacao.repository;

import com.julio.votacao.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}
