package com.cafeteria.dados;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
    private int id;
    private LocalDateTime data_hora_pedido;
    private String status_pedido;
    private String observacao;
    private int atendente;
    private int comanda;

    /* Construtor */
    public Pedido(int id, LocalDateTime data_hora_pedido, String status_pedido, String observacao, int atendente, int comanda) {
        this.id = id;
        this.data_hora_pedido = data_hora_pedido;
        this.status_pedido = status_pedido;
        this.observacao = observacao;
        this.atendente = atendente;
        this.comanda = comanda;
    }

    /* Gets e Sets */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData_hora_pedido() {
        return data_hora_pedido;
    }
    public void setData_hora_pedido(LocalDateTime data_hora_pedido) {
        this.data_hora_pedido = data_hora_pedido;
    }

    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus_pedido() {
        return status_pedido;
    }
    public void setStatus_pedido(String status_pedido) {
        this.status_pedido = status_pedido;
    }

    public int getAtendente() {
        return atendente;
    }
    public void setAtendente(int atendente) {
        this.atendente = atendente;
    }

    public int getComanda() {
        return comanda;
    }
    public void setComanda(int comanda) {
        this.comanda = comanda;
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH'h'mm");

        s.append("\nID: " + id + "\n\tFeito em: " + df.format(data_hora_pedido) +
        "\n\t" + (atendente == 0 ? "Ainda não atendido" : "Atendido pelo funcionario " + atendente) + 
        "\n\tNúmero da comanda: " + comanda);
        if(observacao != null && observacao.length() > 0){
            s.append("\n\tObservação: " + observacao);
        }
        
        return s.toString();
    }
}
