package com.cafeteria.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

import com.cafeteria.dados.Produto;

public class ProdutoDAO {

    public static int insereProduto(Produto p){
        String sql = "INSERT INTO Produto (nome, preco, categoria) VALUES (?, ?, ?)";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, p.getNome());
            st.setFloat(2, p.getPreco());
            st.setString(3, p.getCategoria());
            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1); // O ID estará sempre na primeira coluna desse ResultSet
                    System.out.println("Produto criado com sucesso! ID: " + idGerado);
                    st.close();
                    return idGerado;
                }
            }

            st.close();
            return -1;
        }catch(SQLException e){
            System.out.println("Erro ao criar produto: " + e.getMessage());
            return -1;
        }
    }

    public static Produto procuraProdutoPorID(int pID){
        String sql = "SELECT * FROM Produto WHERE ID_produto = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, pID);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Produto(
                    res.getInt("ID_produto"),
                    res.getString("nome"), 
                    res.getFloat("preco"), 
                    res.getString("categoria"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    // Só retorna o primeiro funcionário
    public static Produto procuraProdutoPorNome(String nome){
        String sql = "SELECT * FROM Produto WHERE nome = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nome);
            ResultSet res = st.executeQuery();

            if(res.next()){
                return new Produto(
                    res.getInt("ID_produto"),
                    res.getString("nome"), 
                    res.getFloat("preco"), 
                    res.getString("categoria"));
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Produto> selectAll(){
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto";

        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet res = st.executeQuery();
            while(res.next()){
                Produto p = new Produto(
                    res.getInt("ID_produto"),
                    res.getString("nome"), 
                    res.getFloat("preco"), 
                    res.getString("categoria"));
                produtos.add(p);
            }
            return produtos;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Boolean deletaProdutoPorID(int pID){
        String sql = "DELETE FROM Produto WHERE ID_produto = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, pID);
            int linhasAfetadas = st.executeUpdate();
            st.close();
            if(linhasAfetadas > 0){
                System.out.println("Produto deletado com sucesso!");
                return true;
            }else{
                System.out.println("Produto não encontrado! Nenhum produto deletado");
                return false;
            }
        }catch(SQLException e){
            System.out.println("Erro ao deletar Produto: " + e.getMessage());
            
            return false;
        }
    }

    public static Boolean alteraProduto(String campoAlterado, int produtoID, String novoAtributo){
        String sql = "UPDATE Produto SET " + campoAlterado + " = ? WHERE ID_produto = ?";
        try{
            Connection con = ConexaoDB.getInstancia();
            PreparedStatement st = con.prepareStatement(sql);
            switch (campoAlterado) {
                case "nome":
                case "categoria":
                    st.setString(1, novoAtributo);
                    break;
            
                case "preco":
                    st.setFloat(1, Float.parseFloat(novoAtributo));
                    break;
                    
                default:
                    System.out.println("Escolha de campo inválida");
                    return false;
            }
            st.setInt(2, produtoID);
            st.executeUpdate();
            st.close();
            System.out.println("Produto alterado com sucesso!");

            return true;
        }catch(SQLException e){
            System.out.println("Erro ao alterar Produto: " + e.getMessage());

            return false;
        }
    }
}
