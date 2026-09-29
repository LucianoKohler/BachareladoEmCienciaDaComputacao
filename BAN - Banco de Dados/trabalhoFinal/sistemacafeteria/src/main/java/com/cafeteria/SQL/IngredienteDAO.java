package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafeteria.dados.Ingrediente;

public class IngredienteDAO {
    public static Boolean insereIngrediente(Ingrediente ing){
        String sql = "INSERT INTO Ingrediente (nome, descricao, quantidade, unidade_medida) VALUES (?, ?, ?, ?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, ing.getNome());
            st.setString(2, ing.getDescricao());
            st.setFloat(3, ing.getQuantidade());
            st.setString(4, ing.getUnidade_medida());
            st.executeUpdate();
            st.close();
            System.out.println("Ingrediente criado com sucesso!");
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao criar ingrediente: " + e.getMessage());
            return false;
        }
    }

    public static Ingrediente procuraIngredientePorID(int ingID){
        String sql = "SELECT * FROM ingrediente WHERE ID_ingrediente = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, ingID);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Ingrediente(
                    res.getInt("ID_ingrediente"),
                    res.getString("nome"), 
                    res.getString("descricao"), 
                    res.getFloat("quantidade"),
                    res.getString("unidade_medida"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    // Só retorna o primeiro ingrediente
    public static Ingrediente procuraIngredientePorNome(String nome){
        String sql = "SELECT * FROM Ingrediente WHERE nome = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Ingrediente(
                    res.getInt("ID_ingrediente"),
                    res.getString("nome"), 
                    res.getString("descricao"), 
                    res.getFloat("quantidade"),
                    res.getString("unidade_medida"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Ingrediente> selectAll(){
        List<Ingrediente> ingredientes = new ArrayList<>();
        String sql = "SELECT * FROM Ingrediente";

        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Ingrediente i = new Ingrediente(
                    rs.getInt("id_ingrediente"),
                    rs.getString("nome"),
                    rs.getString("descricao"), 
                    rs.getFloat("quantidade"),
                    rs.getString("unidade_medida"));
                ingredientes.add(i);
            }
            return ingredientes;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Boolean deletaIngredientePorID(int ingId){
        String sql = "DELETE FROM Ingrediente WHERE ID_ingrediente = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, ingId);
            int linhasAfetadas = st.executeUpdate();            
            st.close();
            if(linhasAfetadas > 0){
                System.out.println("Ingrediente deletado com sucesso!");
                return true;
            }else{
                System.out.println("Ingrediente não encontrado! Nenhum ingrediente deletado");
                return false;
            }

        }catch(SQLException e){
            System.out.println("Erro ao deletar ingrediente: " + e.getMessage());
            
            return false;
        }
    }

    public static Boolean alteraIngrediente(String campoAlterado, int ingredienteID, String novoAtributo){
        String sql = "UPDATE Ingrediente SET " + campoAlterado + " = ? WHERE ID_ingrediente = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            switch (campoAlterado) {
                case "nome":
                case "descricao":
                case "unidade_medida":
                    st.setString(1, novoAtributo);
                    break;
            
                case "quantidade":
                    st.setFloat(1, Float.parseFloat(novoAtributo));
                    break;
                    
                default:
                    System.out.println("Escolha de campo inválida");
                    return false;
            }
            st.setInt(2, ingredienteID);
            st.executeUpdate();
            st.close();
            System.out.println("Ingrediente alterado com sucesso!");

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao alterar ingrediente: " + e.getMessage());
            
            return false;
        }
    }
}
