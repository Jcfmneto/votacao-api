package com.julio.votacao.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;

    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL)
    private List<Voto> votos;


    public Sessao(){}

    public boolean isAberta() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(dataAbertura) && now.isBefore(dataFechamento);
    }

    public Long getId() {
        return id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

}
