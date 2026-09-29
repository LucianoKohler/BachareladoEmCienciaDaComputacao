package com.cafeteria.dados;

public class Produto {
    private int id;
    private String nome;
    private float preco;
    private String categoria;

    /* Construtor */
    public Produto(int id, String nome, float preco, String categoria){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto(){

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

    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + " Nome: " + nome + " Preço: R$" + String.format("%.2f", preco) + " Categoria: " + categoria);
        
        return s.toString();
    }
}
