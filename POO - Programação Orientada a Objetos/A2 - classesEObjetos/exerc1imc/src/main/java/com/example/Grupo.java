package com.example;

public class Grupo {
  private Pessoa[] pessoas = new Pessoa[5]; // Cria um array vazio de 5 espaços que encaixam uma pessoa
  private int qtdPessoas = 0;

  public Grupo() {}

  public Pessoa[] getPessoas() {
    return this.pessoas;
  }

  public void setPessoa(Pessoa pessoa) {
    if (this.qtdPessoas < 5) {
      this.pessoas[this.qtdPessoas] = pessoa;
      this.qtdPessoas++;
    } else {
      System.out.println("O grupo está cheio.");
    }
  }

  public void ordena() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (this.pessoas[j].calculaIMC() > this.pessoas[i].calculaIMC()) {
          Pessoa aux = this.pessoas[j];
          this.pessoas[j] = this.pessoas[i];
          this.pessoas[i] = aux;
        }
      }
    }
  }
}