package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import banco.ConexaoDB;

public class postDAO {
  
  public static void criarPost(int donoPostId, String legenda, byte[] imagem){
    String sql = "INSERT INTO post (donopost, legenda, imagem) VALUES (?, ?, ?)";

    try{
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, donoPostId);
      statement.setString(2, legenda);
      statement.setBytes(3, imagem);
      statement.executeUpdate();
      statement.close();
      System.out.println("Post criado!");
    } catch (SQLException e){
      System.out.println("Erro ao criar post: " + e.getMessage());
    }

  }
}
