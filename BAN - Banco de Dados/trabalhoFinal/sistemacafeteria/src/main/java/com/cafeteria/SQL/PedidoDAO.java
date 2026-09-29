package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import com.cafeteria.dados.Pedido;

public class PedidoDAO {

    public static int criaPedido(int fkComanda, String obs){
        String sql = "INSERT INTO Pedido (FK_comanda, observacao) VALUES (?, ?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, fkComanda);
            if(obs.length() == 0){
                st.setNull(2, Types.VARCHAR);
            }else{
                st.setString(2, obs);
            }   
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    System.out.println("Pedido criado com sucesso! ID: " + idGerado);
                    st.close();
                    return idGerado;
                }
            }

            st.close();
            return -1;
        }catch(SQLException e){
            System.out.println("Erro ao criar pedido: " + e.getMessage());
            return -1;
        }
    }

    public static Pedido procuraPedidoPorID(int pID){
        String sql = "SELECT * FROM Pedido WHERE ID_pedido = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, pID);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Pedido(
                    res.getInt("ID_pedido"),
                    res.getTimestamp("data_hora_abertura").toLocalDateTime(), 
                    "pendente", 
                    res.getString("observacao"),
                    res.getInt("FK_funcionario"), 
                    res.getInt("FK_comanda"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static List<Pedido> selectAll(String status){
        List<Pedido> pedidos = new ArrayList<>();
        String sql;
        if(status.length() == 0){
            sql =  "SELECT * FROM Pedido";
        }else{
            sql =  "SELECT * FROM Pedido WHERE status_pedido = ?";
        }

        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            if(status.length() > 0) st.setString(1, status);
            ResultSet res = st.executeQuery();
            while(res.next()){
                Pedido p = new Pedido(
                    res.getInt("ID_pedido"),
                    res.getTimestamp("data_hora_abertura").toLocalDateTime(), 
                    res.getString("status_pedido"), 
                    res.getString("observacao"),
                    res.getInt("FK_funcionario"), 
                    res.getInt("FK_comanda"));
                pedidos.add(p);
            }
            return pedidos;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Boolean deletaPedidoPorID(int pID){
        String sql = "DELETE FROM Pedido WHERE ID_pedido = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, pID);
            int linhasAfetadas = st.executeUpdate();
            st.close();
            if(linhasAfetadas > 0){
                System.out.println("Pedido deletado com sucesso!");
                return true;
            }else{
                System.out.println("Pedido não encontrado! Nenhum pedido deletado");
                return false;
            }
        }catch(SQLException e){
            System.out.println("Erro ao deletar Pedido: " + e.getMessage());
            
            return false;
        }
    }

    public static Boolean alteraPedido(String campoAlterado, int pedidoID, String novoAtributo){
        String sql = "UPDATE Pedido SET " + campoAlterado + " = ? WHERE ID_pedido = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            switch (campoAlterado) {
                case "status_pedido":
                case "observacao":
                    st.setString(1, novoAtributo);
                    break;
            
                case "FK_comanda":
                case "FK_funcionario":
                    st.setInt(1, Integer.parseInt(novoAtributo));
                    break;
                    
                default:
                    System.out.println("Escolha de campo inválida");
                    return false;
            }
            st.setInt(2, pedidoID);
            st.executeUpdate();
            st.close();

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao alterar Pedido: " + e.getMessage());

            return false;
        }
    }
}
