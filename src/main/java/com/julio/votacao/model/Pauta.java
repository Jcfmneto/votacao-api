package com.julio.votacao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pauta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;
  private String descricao;

  public Pauta() {
  }

  public Pauta(String nome, String descricao) {
    this.titulo = nome;
    this.descricao = descricao;
  }

  public Long getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

}
