package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.cafeteria.dados.Ingrediente;

public class InfoProdutoDAO {

    public static Boolean criaInfoProduto(float quantidade, String obs, int ingID, int prodID){
        String sql = "INSERT INTO Info_produto (quantidade, observacao, FK_ingrediente, FK_produto) VALUES (?, ?, ?, ?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setFloat(1, quantidade);
            st.setString(2, obs);
            st.setInt(3, ingID);
            st.setInt(4, prodID);
            st.executeUpdate();
            st.close();
            System.out.println("Relação criada com sucesso!");
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao criar relação: " + e.getMessage());
            return false;
        }
    }

    public static List<Ingrediente> buscaIngredientesDeUmProduto(int idProduto) {
        List<Ingrediente> lista = new ArrayList<>();
        
        String sql = "SELECT r.ID_info_produto, i.nome, r.observacao, r.quantidade, i.unidade_medida " +
                 "FROM Info_produto r " +
                 "JOIN Ingrediente i ON r.FK_ingrediente = i.ID_ingrediente " +
                 "WHERE r.FK_produto = ?";

        try (Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql)) {
            
            st.setInt(1, idProduto);
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Ingrediente ing = new Ingrediente(
                        rs.getInt("ID_info_produto"),
                        rs.getString("nome"),
                        rs.getString("observacao"), 
                        rs.getFloat("quantidade"), 
                        rs.getString("unidade_medida")
                    );
                    
                    lista.add(ing);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar receita: " + e.getMessage());
        }
        
        return lista;
    }

    public static Boolean alteraInfoProduto(String campoAlterado, int infoProdutoID, String novoAtributo){
        String sql = "UPDATE info_produto SET " + campoAlterado + " = ? WHERE ID_info_produto = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            switch (campoAlterado) {
                case "quantidade":
                    st.setFloat(1, Float.parseFloat(novoAtributo));
                    break;
                case "observacao":
                    if(novoAtributo.length() == 0){
                        st.setNull(1, Types.VARCHAR);
                    }else{
                        st.setString(1, novoAtributo);
                    }
                    break;
            
                case "FK_ingrediente":
                    st.setInt(1, Integer.parseInt(novoAtributo));
                    break;
                    
                default:
                    System.out.println("Escolha de campo inválida");
                    return false;
            }
            st.setInt(2, infoProdutoID);
            st.executeUpdate();
            st.close();
            System.out.println("Item da receita alterado com sucesso!");

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao alterar item da receita: " + e.getMessage());

            return false;
        }
    }

    public static Boolean deletaItemDaReceita(int idItemProduto, int idProduto){
        String sql = "DELETE FROM info_produto WHERE ID_info_produto = ? AND FK_produto = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idItemProduto);
            st.setInt(2, idProduto);
            int linhasAfetadas = st.executeUpdate();
            st.close();

            if(linhasAfetadas > 0){
                System.out.println("Item deletado com sucesso!");
                return true;
            }else{
                System.out.println("Item não encontrado! Nenhum item deletado");
                return false;
            }
        }catch(SQLException e){
            System.out.println("Erro ao deletar item: " + e.getMessage());
            
            return false;
        }
    }
}
