package com.julio.votacao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "votos")
public class Voto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "sessao_id", nullable = false)
  private Sessao sessao;

  @ManyToOne(optional = false)
  @JoinColumn(name = "associado_id", nullable = false)
  private Associado associado;

  @Column(nullable = false)
  private Boolean voto;

  public Voto() {
  }

  public Voto(Sessao sessao, Associado associado, Boolean voto) {
    this.sessao = sessao;
    this.associado = associado;
    this.voto = voto;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Sessao getSessao() {
    return sessao;
  }

  public void setSessao(Sessao sessao) {
    this.sessao = sessao;
  }

  public Associado getAssociado() {
    return associado;
  }

  public void setAssociado(Associado associado) {
    this.associado = associado;
  }

  public Boolean getVoto() {
    return voto;
  }

  public void setVoto(Boolean voto) {
    this.voto = voto;
  }
}
