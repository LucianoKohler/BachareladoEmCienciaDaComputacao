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
  private ArrayList<User> users;
  private ArrayList<Post> posts;

  // Construtor
  public Sistema () {
    this.users = new ArrayList<User>();
    this.posts = new ArrayList<Post>();
  }

  // Métodos
  public ArrayList<User> getAllUsers(){
    return users;
  }

  public ArrayList<Post> getAllPosts(){
    return posts;
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
        if(usuario.getSenha().equals(senha)){
          return usuario;
        }
        // Se errou a senha
        return null;
  }

  public boolean deletarUser(User u){
    // Deixando de seguir todos os seus seguindos:
    ArrayList<User> seguindoCopia = new ArrayList<>(u.verSeguindo());
    for(User userSeguindo : seguindoCopia){ 
      u.unfollow(userSeguindo);
    }

    // Removendo ele da lista de seguidores dos outros
    ArrayList<User> seguidoresCopia = new ArrayList<>(u.verSeguidores());
    for(User seguidor : seguidoresCopia){
      seguidor.unfollow(u);
    }

    // Apagando cada post dele
    ArrayList<Post> postsCopia = new ArrayList<>(u.getPosts());
    for(Post p : postsCopia){
      // Remove o post dos favoritos dos usuários
      deletarPost(p);
    }
    return users.remove(u);
  }

  public void criarPost(User u, String legenda, byte[] imagem){
    postDAO.criarPost(u.getId(), legenda, imagem);
  }

  public void deletarPost(Post p){
    p.getDonoPost().deletarPost(p);
    // Cria uma cópia da lista para evitar ConcurrentModificationException
    ArrayList<User> listaFavoritadores = new ArrayList<>(p.getFavoritadores());
    for(User userFavoritador : listaFavoritadores){
      desFavoritarPost(userFavoritador, p);
    }
    posts.remove(p);
  }

  public ArrayList<Post> verPostsDisponiveis(User u){
    ArrayList<Post> postsVisiveis = new ArrayList<Post>();
    for(Post post : posts){
      if(post.getDonoPost().verSeguidores().contains(u) || post.getDonoPost().equals(u)){
        // Se você segue o dono do post OU o post é seu
        postsVisiveis.add(post);
      }
    }
    return postsVisiveis;
  }

  public ArrayList<Post> verPostsDeUmUser(User u){
    ArrayList<Post> postsVisiveis = new ArrayList<Post>();
    for(Post post : posts){
      if(post.getDonoPost().equals(u)){
        postsVisiveis.add(post);
      }
    }
    return postsVisiveis;
  }

  public void followUser(User u, User alvo){
    u.follow(alvo);
    alvo.novoSeguidor(u);
  }

  public void unfollowUser(User u, User alvo){
    u.unfollow(alvo);
    alvo.removerSeguidor(u);
  }

  public boolean mudarCredenciaisPerfil(User u, String s, int escolha){
    if(s.equals("")) return false;

    switch (escolha) {
      case 1: // username
        // Checando para ver se já há um username igual:
        for(User userNaBase : getAllUsers()){
          if (userNaBase.getUsername().equals(s)){
            return false;
          }
        } // Caso não haja
        u.mudarUsername(s);
        break;
      case 2: // senha
        u.mudarSenha(s);
        break;
      case 3: // nome completo
        u.mudarNomeCompleto(s);
        break;
      case 4: // biografia
        u.mudarBiografia(s);
        break;    
      default:
        System.out.println("Escolha inválida");
        break;
    }
    return true;
  }

  public boolean favoritarPost(User u, Post p){
    if(p.getFavoritadores().contains(u)){
      return false;
    }else{
      u.favoritarPost(p);
      p.adicionarFavoritador(u);
      return true;
    }
  }

  public void desFavoritarPost(User u, Post p){
    p.removerFavoritador(u);
    u.desFavoritarPost(p);
  }

  public ArrayList<User> verSeguidoresDeUmUser(User u){
    return u.verSeguidores();
  }
  
  public ArrayList<User> verSeguindoDeUmUser(User u){
    return u.verSeguindo();
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
