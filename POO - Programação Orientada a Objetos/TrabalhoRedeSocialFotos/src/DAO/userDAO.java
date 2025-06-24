package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import banco.ConexaoDB;
import dados.User;

public class userDAO {

  public static void cadastrarUser(String username, String senha, String nomeCompleto, String biografia, byte[] imagemPerfil){
    String sql = "INSERT INTO usuario (username, senha, nomeCompleto, biografia, imagemPerfil) VALUES (?, ?, ?, ?, ?)";

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, username);
      statement.setString(2, senha);
      statement.setString(3, nomeCompleto);
      statement.setString(4, biografia);
      statement.setBytes(5, imagemPerfil);
      statement.executeUpdate();
      statement.close();
      System.out.println("Cadastrado!");
    } catch (SQLException e){
      System.out.println("Erro no cadastro: " + e.getMessage());
    }
  }

  public static User buscarPorUsername(String username){
    String sql = "SELECT * FROM usuario WHERE username = ?";

    try{
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, username);
      ResultSet resultado = statement.executeQuery();

      if(resultado.next()){ // Se há registro
        return new User(
          resultado.getInt("id"),
          resultado.getString("username"), 
          resultado.getString("senha"), 
          resultado.getString("nomeCompleto"),
          resultado.getString("biografia"),
          resultado.getBytes("imagemPerfil")
          );
      }else{ // Se não encontrou ninguém
        return null;
      }
    } catch (SQLException e){
      System.out.println(e.getMessage());
      return null;
    }
  }
}
