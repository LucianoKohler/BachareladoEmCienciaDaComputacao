package com.cafeteria.dados;

public class ItemRelatorio {
    private String nome;
    private int quantidade;
    private float valor;

    public ItemRelatorio(String nome, int quantidade, float valor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public float getValor() { return valor; }
}