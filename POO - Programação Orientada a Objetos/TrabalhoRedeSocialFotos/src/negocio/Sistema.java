package negocio;
import dados.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import DAO.postDAO;
import DAO.userDAO;

public class Sistema {

  // Construtor
  public Sistema () {}

  // Métodos
  public ArrayList<User> getAllUsers(){
    return userDAO.getAllUsers();
  }

  public ArrayList<Post> getAllPosts(){
    return postDAO.getAllPosts();
  }

  public User buscarPorUsername(String username){
    return userDAO.buscarPorUsername(username);
  }

  public boolean cadastrarUser(String username, String senha, String nomeCompleto, String biografia, byte[] imagemPerfil){
    if(userDAO.buscarPorUsername(username) != null){ // Se já houver alguém com esse username
      return false;
    }
    userDAO.cadastrarUser(username, senha, nomeCompleto, biografia, imagemPerfil);
    return true;  
  }

  public User fazerLogin(String username, String senha){
    User usuario = userDAO.buscarPorUsername(username);
    if(usuario != null){
      if(usuario.getSenha().equals(senha)){
        return usuario;
      }
    }
        // Se errou a senha
        return null;
  }

  public void deletarUser(User u){
    // Deixando de seguir todos os seus seguindos:
    ArrayList<User> seguindos = userDAO.verSeguindoDeUmUser(u.getId());
    if(seguindos != null){
      for(User seguido : seguindos){ 
        unfollowUser(u, seguido);
      }
    }

    // Removendo ele da lista de seguidores dos outros
    ArrayList<User> seguidores = userDAO.verSeguidoresDeUmUser(u.getId());
    if(seguidores != null){
      for(User seguidor : seguidores){
        unfollowUser(seguidor, u);
      }
    }

    // Apagando cada post dele
    ArrayList<Post> posts = postDAO.verPostsDeUmUser(u.getId());
    if(posts != null){
      for(Post p : posts){
        deletarPost(p);
      }
    }
    userDAO.deletarUser(u.getId());
  }

  public void criarPost(User u, String legenda, byte[] imagem){
    postDAO.criarPost(u.getId(), legenda, imagem);
  }

  public void deletarPost(Post p){
    // Remover os favoritos
    ArrayList<User> usersQueFavoritaram = postDAO.verUsersQueFavoritaramPost(p.getId());
    if(usersQueFavoritaram != null){
      for(User u : usersQueFavoritaram){
        if(u != null){
          desFavoritarPost(u, p);
        }
      }
    }
    // Remover o post
    postDAO.deletarPost(p.getId());
  }

  public ArrayList<Post> verPostsDisponiveis(User u){
    ArrayList<Post> postsVisiveis = new ArrayList<Post>();
    ArrayList<User> usuariosSeguidos = userDAO.verSeguindoDeUmUser(u.getId());
    ArrayList<Post> posts = postDAO.getAllPosts();
    if(posts != null){
      for(Post post : posts){
        if(usuariosSeguidos != null){
          if(usuariosSeguidos.contains(userDAO.buscarPorId(post.getDonoPost())) || post.getDonoPost() == u.getId()){
            // Se você segue o dono do post OU o post é seu
            postsVisiveis.add(post);
          }
        }
      }
    }
    return postsVisiveis;
  }

  public ArrayList<Post> verPostsDeUmUser(User u){
    return postDAO.verPostsDeUmUser(u.getId());
  }

  public void followUser(User u, User alvo){ 
    userDAO.follow(u.getId(), alvo.getId());
  }

  public void unfollowUser(User u, User alvo){
    userDAO.unfollow(u.getId(), alvo.getId());
  }

  public boolean mudarCredenciaisPerfil(User u, String s, int escolha){
    if(s.equals("")) return false;

    switch (escolha) {
      case 1: // username
        // Checando para ver se já há um username igual:
        if (userDAO.buscarPorUsername(s) != null){ return false; }

        userDAO.mudarCredenciaisPerfil(u.getId(), s, "username");
        break;
      case 2: // senha
        userDAO.mudarCredenciaisPerfil(u.getId(), s, "senha");
        break;
      case 3: // nome completo
        userDAO.mudarCredenciaisPerfil(u.getId(), s, "nomecompleto");
        break;
      case 4: // biografia
        userDAO.mudarCredenciaisPerfil(u.getId(), s, "biografia");
        break;    
      default:
        System.out.println("Escolha inválida");
        break;
    }
    return true;
  }

  public void mudarFotoPerfil(User u, byte[] imagem){
    if(imagem == null){
      imagem = User.carregarImagemPadrao();
    }

    userDAO.mudarFotoPerfil(u.getId(), imagem);
  }

  public boolean favoritarPost(User u, Post p){
    ArrayList<Post> favoritos = postDAO.verFavoritosDeUmUser(u.getId());
    if(favoritos != null){
      if(favoritos.contains(p)){
        return false;
      }else{
        postDAO.favoritarPost(p.getId(), u.getId());
        return true;
      }
    }
    postDAO.favoritarPost(p.getId(), u.getId());
    return true;
  }

  public void desFavoritarPost(User u, Post p){
    postDAO.desFavoritarPost(u.getId(), p.getId());
  }

  public ArrayList<Post> verFavoritosDeUmUser(User u){
    return postDAO.verFavoritosDeUmUser(u.getId());
  }

  public ArrayList<User> verSeguidoresDeUmUser(User u){
    return userDAO.verSeguidoresDeUmUser(u.getId());
  }
  
  public ArrayList<User> verSeguindoDeUmUser(User u){
    return userDAO.verSeguindoDeUmUser(u.getId());
  }

  public static byte[] ImageParaBytes(BufferedImage imagem) {
    if(imagem == null){ return null; }
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        ImageIO.write(imagem, "png", baos);
        return baos.toByteArray();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
  }

  public static BufferedImage BytesParaImage(byte[] bytes) {
    if(bytes == null){ return null; }
    try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
        return ImageIO.read(bais);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
  }
}
