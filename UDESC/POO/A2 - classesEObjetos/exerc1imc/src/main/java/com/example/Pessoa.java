package com.example;

public class Pessoa {
  private String nome;
  private int idade;
  private float altura;
  private float peso;

  public Pessoa() {}

  public String getNome() { return this.nome; }
  public void setNome(String nome) { this.nome = nome; }

  public int getIdade() { return this.idade; }
  public void setIdade(int idade) { this.idade = idade; }

  public float getAltura() { return this.altura; }
  public void setAltura(float altura) { this.altura = altura; }

  public float getPeso() { return this.peso; }
  public void setPeso(float peso) { this.peso = peso; }

  public float calculaIMC() {
    return this.peso / (this.altura * this.altura);
  }
}