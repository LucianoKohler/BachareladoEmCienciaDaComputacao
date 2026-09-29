package com.cafeteria.dados;

public class InfoProduto {
    private int id;
    private Produto produto;
    private Ingrediente ingrediente;
    private float quantidade;
    private String observacao;

    /* Construtor */
    public InfoProduto(int id, Produto produto, Ingrediente ingrediente, float quantidade, String observacao) {
        this.id = id;
        this.produto = produto;
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
        this.observacao = observacao;
    }

    /* Gets e Sets */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }
    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public float getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID: " + id + "\nProduto: " + produto.getNome() +
        "\nIngrediente: " + ingrediente.getNome() + "\nQuantidade: "
        + quantidade + "\nObservação: " + observacao);
        
        return s.toString();
    }
}
