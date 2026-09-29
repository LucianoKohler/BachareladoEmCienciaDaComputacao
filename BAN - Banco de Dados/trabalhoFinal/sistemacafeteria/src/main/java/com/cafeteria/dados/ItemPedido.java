package com.cafeteria.dados;

public class ItemPedido {
    private int id;
    private int quantidade;
    private float preco_unitario;
    private int pedido;
    private int produto;

    /* Construtor */
    public ItemPedido(int id, int quantidade, float preco_unitario, int pedido, int produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.pedido = pedido;
        this.produto = produto;
    }

    /* Gets e Sets */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPedido() {
        return pedido;
    }
    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public int getProduto() {
        return produto;
    }
    public void setProduto(int produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco_unitario() {
        return preco_unitario;
    }
    public void setPreco_unitario(float preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + "\nProduto: " + produto +
        "\nQuantidade: " + quantidade + "\n Do pedido: " + pedido + 
        "\nPreço unitário: " + preco_unitario);
        
        return s.toString();
    }
}
