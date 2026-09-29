package com.cafeteria.dados;

import java.time.LocalDate;

public class Funcionario {
    private int id;
    private String nome;
    private float salario;
    private LocalDate data_contratacao;
    private String cargo;

    /* Construtor */
    public Funcionario(int id, String nome, float salario, LocalDate data_contratacao, String cargo){
        this.id = id;
        this.nome = nome;
        this.salario = salario;
        this.data_contratacao = data_contratacao;
        this.cargo = cargo;
    }

    public Funcionario(){

    }
    
    /* Gets e Sets */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }
    public void setSalario(float salario) {
        this.salario = salario;
    }

    public LocalDate getData_contratacao() {
        return data_contratacao;
    }
    public void setData_contratacao(LocalDate data_contratacao) {
        this.data_contratacao = data_contratacao;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + " Nome: " + nome + 
        " Salário: R$" + salario + " Data de Contratação: " + data_contratacao.toString() +
        " Cargo: " + cargo);
        
        return s.toString();
    }
}
