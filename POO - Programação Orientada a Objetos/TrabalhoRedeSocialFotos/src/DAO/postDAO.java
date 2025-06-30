package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import banco.ConexaoDB;
import dados.Post;
import dados.User;

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

  public static Post buscarPorId(int idPost){
    String sql = "SELECT * FROM post WHERE id = ?";

    try{
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idPost);
      ResultSet resultado = statement.executeQuery();

      if(resultado.next()){ // Se há registro
        return new Post(
          resultado.getInt("id"),
          resultado.getInt("donopost"), 
          resultado.getString("legenda"), 
          resultado.getBytes("imagem")
          );
      }else{ // Se não encontrou ninguém
        return null;
      }
    } catch (SQLException e){
      System.out.println(e.getMessage());
      return null;
    }

  }

  public static ArrayList<Post> getAllPosts(){
    ArrayList<Post> posts = new ArrayList<>();
    String sql = "SELECT * FROM post";

    try{
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      ResultSet resultado = statement.executeQuery();

      if(resultado.next()){
        do {
          posts.add(new Post(
            resultado.getInt("id"),
            resultado.getInt("donopost"),
            resultado.getString("legenda"),
            resultado.getBytes("imagem")
          ));
        } while (resultado.next());
      }else{
        return null;
      }
    } catch (SQLException e){
      System.out.println(e.getMessage());
    }
    return posts;
  }

  public static ArrayList<Post> verPostsDeUmUser(int idUser){
    String sql = "SELECT * FROM post WHERE donopost = ?";
    ArrayList<Post> posts = new ArrayList<>();

    try{
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idUser);
      ResultSet resultado = statement.executeQuery();

      if(resultado.next()){
        do {
          posts.add(new Post(
            resultado.getInt("id"),
            resultado.getInt("donopost"),
            resultado.getString("legenda"),
            resultado.getBytes("imagem")
          ));
        }while(resultado.next());
        statement.close();
        return posts;
      }else{
        statement.close();
        return null;
      }
    }catch(SQLException e){
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static ArrayList<Post> verFavoritosDeUmUser(int idUser){
    String sql = "SELECT * FROM usuariofavoritos WHERE idUsuario = ?";
    ArrayList<Post> favoritos = new ArrayList<>();

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idUser);
      ResultSet resultado = statement.executeQuery();

      if(resultado.next()){
        do{
          Post p = postDAO.buscarPorId(resultado.getInt("id"));
          favoritos.add(p);
        }while(resultado.next());
        statement.close();
        return favoritos;
      }else{
        statement.close();
        return null;
      }
    } catch (SQLException e){
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static ArrayList<User> verUsersQueFavoritaramPost(int idPost){
    String sql = "SELECT * FROM usuariofavoritos WHERE idpost = ?";
    ArrayList<User> users = new ArrayList<>();

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idPost);
      ResultSet resultado = statement.executeQuery();

      if(resultado.next()){
        do {
          User u = userDAO.buscarPorId(resultado.getInt("id"));
          users.add(u);
        } while(resultado.next());
        return users;
      }else{
        return null;
      }
    } catch(SQLException e){
      System.out.println(e.getMessage());
      return null;
    }
  }

  public static void favoritarPost(int idPost, int idUser){
    String sql = "INSERT INTO usuariofavoritos (idUsuario, idPost) VALUES (?, ?)";

    try{
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idUser);
      statement.setInt(2, idPost);
      statement.executeUpdate();
      statement.close();
    } catch (SQLException e){
      System.out.println("Erro ao favoritar post: " + e.getMessage());
    }
  }

  public static void desFavoritarPost(int idPost, int idUser){
    String sql = "DELETE FROM usuariofavoritos WHERE idusuario = ? AND idpost = ?";

    try {
      Connection con = ConexaoDB.getInstancia();
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(1, idUser);
      statement.setInt(2, idPost);
      statement.executeUpdate();
    } catch(SQLException e){
      System.out.println(e.getMessage());
    }

  }

  public static void deletarPost(int idPost){
    String sqlDeleteFavoritos = "DELETE FROM usuariofavoritos WHERE idpost = ?";
    String sqlDeletePost = "DELETE FROM post WHERE id = ?";

    try{
      Connection con = ConexaoDB.getInstancia();
      // Primeiro, remove referências em usuariofavoritos
      PreparedStatement statementFavoritos = con.prepareStatement(sqlDeleteFavoritos);
      statementFavoritos.setInt(1, idPost);
      statementFavoritos.executeUpdate();
      statementFavoritos.close();

      // Agora, remove o post
      PreparedStatement statementPost = con.prepareStatement(sqlDeletePost);
      statementPost.setInt(1, idPost);
      statementPost.executeUpdate();
      statementPost.close();
    }catch(SQLException e){
      System.out.println(e.getMessage());
    }
  }
}
