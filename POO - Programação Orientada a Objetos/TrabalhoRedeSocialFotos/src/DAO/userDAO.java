package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

  public static User buscarPorId(int id){
    String sql = "SELECT * FROM usuario WHERE id = ?";

    try{
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, id);
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

  public static ArrayList<User> getAllUsers(){
    ArrayList<User> users = new ArrayList<>();
    String sql = "SELECT * FROM usuario";

    try{
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      ResultSet resultado = statement.executeQuery();

      if(resultado.next()){
        do {
          users.add(new User(
            resultado.getInt("id"),
            resultado.getString("username"),
            resultado.getString("senha"),
            resultado.getString("nomeCompleto"),
            resultado.getString("biografia"),
            resultado.getBytes("imagemPerfil")
          ));
        } while (resultado.next());
      }
    statement.close();
    } catch (SQLException e){
      System.out.println(e.getMessage());
    }
    return users;
  }

  public static void follow(int idFollowed, int idFollower){
    String sql = "INSERT INTO seguidores (idFollowed, idFollower) VALUES (?, ?)";

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idFollowed);
      statement.setInt(2, idFollower);
      statement.executeUpdate();
      statement.close();
      System.out.println("Seguido!");
    } catch(SQLException e){
      System.out.println(e.getMessage());
    }
  }

  public static void unfollow(int idUnfollower, int idUnfollowed){
    String sql = "DELETE FROM seguidores WHERE idFollowed = ? AND idFollower = ?";

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idUnfollowed);
      statement.setInt(2, idUnfollower);
      statement.executeUpdate();
    } catch(SQLException e){
      System.out.println(e.getMessage());
    }
  }

  public static void mudarCredenciaisPerfil(int idUser, String info, String infoMudada){
    String sql = "UPDATE usuario SET " + infoMudada + " = ? WHERE id = ?";

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setString(1, info);
      statement.setInt(2, idUser);
      statement.executeUpdate();
      statement.close();

    } catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }

  public static void mudarFotoPerfil(int idUser, byte[] imagem){
    String sql = "UPDATE usuario SET imagemperfil  = ? WHERE id = ?";

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setBytes(1, imagem);
      statement.setInt(2, idUser);
      statement.executeUpdate();
      statement.close();

    } catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }

  public static ArrayList<User> verSeguidoresDeUmUser(int idUser) {
    ArrayList<User> seguidores = new ArrayList<>();
    String sql = "SELECT * FROM seguidores WHERE idfollowed = ?";

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idUser);
      ResultSet resultado = statement.executeQuery();

      if(resultado.next()){
        do {
          User user = buscarPorId(idUser); // A função principal só pega os IDs do user, então chamamos essa
          seguidores.add(user);
        } while (resultado.next());
      }
      statement.close();
    } catch (SQLException e){
      System.out.println(e.getMessage());
    }
    return seguidores;
  }

  public static ArrayList<User> verSeguindoDeUmUser(int idUser) {
    ArrayList<User> seguindo = new ArrayList<>();
    String sql = "SELECT * FROM seguidores WHERE idfollower = ?";

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idUser);
      ResultSet resultado = statement.executeQuery();

      if(resultado.next()){
        do {
          User user = buscarPorId(idUser);
          seguindo.add(user);
        } while (resultado.next());
      }
      statement.close();
    } catch (SQLException e){
      System.out.println(e.getMessage());
    }
    return seguindo;
  }

  public static void deletarUser(int idUser){
    String sql = "DELETE FROM usuario WHERE id = ?";

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idUser);
      statement.executeUpdate();
    }catch(SQLException e){
      System.out.println(e.getMessage());
    }
  }
}
