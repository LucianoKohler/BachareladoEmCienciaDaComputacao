package com.cafeteria.dados;

public class Ingrediente {
    private int id;
    private String nome;
    private String descricao;
    private float quantidade;
    private String unidade_medida;

    /* Construtor */
    public Ingrediente(int id, String nome, String descricao, float quantidade, String unidade_medida){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.unidade_medida = unidade_medida;
    }

    public Ingrediente(){

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

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }
    public void setUnidade_medida(String unidade_medida) {
        this.unidade_medida = unidade_medida;
    }    
    public String getUnidade_medida() {
        return unidade_medida;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + "; Nome: " + nome + "; Descricao: " + descricao + "; Quantidade em estoque: " + quantidade + " " + unidade_medida);
        return s.toString();
    }


}
