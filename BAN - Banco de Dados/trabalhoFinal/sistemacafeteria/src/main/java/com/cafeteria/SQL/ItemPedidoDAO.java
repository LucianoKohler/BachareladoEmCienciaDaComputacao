package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

import com.cafeteria.dados.ItemPedido;
import com.cafeteria.dados.Produto;

public class ItemPedidoDAO {

    public static int criaItemPedido(int quantidade, float preco_unitario, int fkPedido, int fkProduto){
        String sql = "INSERT INTO Item_pedido (quantidade, preco_unitario, FK_pedido, FK_produto) VALUES (?, ?, ?, ?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, quantidade);
            st.setFloat(2, preco_unitario);
            st.setInt(3, fkPedido);
            st.setInt(4, fkProduto);
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    System.out.println("Item inserido com sucesso! ID: " + idGerado);
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

    public static ItemPedido procuraItemPedidoPorID(int id){
        String sql = "SELECT * FROM Item_pedido WHERE ID_item_pedido = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new ItemPedido(
                    res.getInt("ID_item_pedido"),
                    res.getInt("quantidade"),
                    res.getFloat("preco_unitario"),
                    res.getInt("fk_pedido"),
                    res.getInt("fk_produto"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static List<ItemPedido> selectAll(int fkPedido){
        List<ItemPedido> itensDoPedido = new ArrayList<>();
        String sql = "SELECT * FROM Item_pedido WHERE FK_Pedido = ?";

        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, fkPedido);
            ResultSet res = st.executeQuery();
            while(res.next()){
                ItemPedido p = new ItemPedido(
                    res.getInt("ID_item_pedido"),
                    res.getInt("quantidade"),
                    res.getFloat("preco_unitario"),
                    res.getInt("fk_pedido"),
                    res.getInt("fk_produto"));
                itensDoPedido.add(p);
            }
            return itensDoPedido;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Boolean deletaItemPedidoPorID(int id){
        String sql = "DELETE FROM item_pedido WHERE ID_item_pedido = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            int linhasAfetadas = st.executeUpdate();
            st.close();
            if(linhasAfetadas > 0){
                System.out.println("Item do pedido deletado com sucesso!");
                return true;
            }else{
                System.out.println("Item do pedido não encontrado! Nenhum item deletado");
                return false;
            }
        }catch(SQLException e){
            System.out.println("Erro ao deletar item do pedido: " + e.getMessage());
            
            return false;
        }
    }

    public static Boolean alteraItemPedido(String campoAlterado, int pedidoID, String novoAtributo){
        String sql = "UPDATE item_pedido SET " + campoAlterado + " = ? WHERE ID_item_pedido = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            switch (campoAlterado) {
                case "quantidade":
                    st.setInt(1, Integer.parseInt(novoAtributo));
                    break;
            
                case "observacao":
                    st.setString(1, novoAtributo);
                    break;
                    
                default:
                    System.out.println("Escolha de campo inválida");
                    return false;
            }
            st.setInt(2, pedidoID);
            st.executeUpdate();
            st.close();
            System.out.println("Item do pedido alterado com sucesso!");

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao alterar Item do pedido: " + e.getMessage());

            return false;
        }
    }

    public static List<Produto> mostraItensDeUmPedido(int idPedido){
        List<Produto> lista = new ArrayList<>();
        
        String sql = "SELECT p.nome, p.categoria, ip.ID_item_pedido, " +
        "ip.quantidade, ip.preco_unitario FROM Item_pedido ip " +
        "JOIN Produto p ON ip.FK_produto = p.ID_produto WHERE ip.FK_pedido = ?";

        try (Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql)) {
            
            st.setInt(1, idPedido);
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Produto prod = new Produto(
                        rs.getInt("ID_item_pedido"),
                        rs.getString("nome"),
                        rs.getInt("quantidade"),
                        rs.getString("categoria")
                    );

                    lista.add(prod);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar itens do pedido: " + e.getMessage());
        }
        
        return lista;
    }
}
