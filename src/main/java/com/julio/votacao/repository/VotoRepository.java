package com.julio.votacao.repository;

import com.julio.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findBySessaoIdAndAssociadoId(Long sessaoId, Long associadoId);

    List<Voto> findBySessaoId(Long sessaoId);

    List<Voto> findByAssociadoId(Long associadoId);
}
