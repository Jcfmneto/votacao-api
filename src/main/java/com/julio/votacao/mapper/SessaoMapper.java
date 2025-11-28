package com.julio.votacao.mapper;

import com.julio.votacao.dto.request.SessaoRequestDTO;
import com.julio.votacao.dto.response.SessaoResponseDTO;
import com.julio.votacao.model.Pauta;
import com.julio.votacao.model.Sessao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SessaoMapper {

    public Sessao toEntity(SessaoRequestDTO dto, Pauta pauta) {
        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);

        LocalDateTime abertura = LocalDateTime.now();
        int duracao = dto.duracaoMinutos() != null ? dto.duracaoMinutos() : 1;
        sessao.setDataAbertura(abertura);
        sessao.setDataFechamento(abertura.plusMinutes(duracao));

        return sessao;
    }

    public SessaoResponseDTO toDTO(Sessao sessao) {
        return new SessaoResponseDTO(
                sessao.getId(),
                sessao.getPauta().getId(),
                sessao.getDataAbertura(),
                sessao.getDataFechamento()
        );
    }
}
