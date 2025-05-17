package negocio;
import dados.*;
import java.util.ArrayList;

public class Sistema {
  private ArrayList<User> users;
  private int userIDtracker = 1;
  private ArrayList<Post> posts;
  private int postIDtracker = 1;

  // Construtor
  public Sistema () {
    this.users = new ArrayList<User>();
    this.posts = new ArrayList<Post>();
  }

  // Métodos
  public ArrayList<User> getAllUsers(){
    return users;
  }

  public boolean cadastrarUser(User u){
    for(User userNaBase : getAllUsers()){
      if(userNaBase.getUsername().equals(u.getUsername())){ // Se já há um username igual cadastrado
        return false;
      }
    }
    u.setID(userIDtracker);
    users.add(u);
    userIDtracker++;
    return true;  
  }

  public User fazerLogin(String username, String senha){
    for(User userNaBase : getAllUsers()){
      if(userNaBase.getUsername().equals(username)){
        if(userNaBase.getSenha().equals(senha)){
          return userNaBase;
        }
        // Se errou a senha
        return null;
      }
    }
    // Se não encontrou user
    return null;
  }

  public boolean deletarUser(User u){
    // Deixando de seguir todos os seus seguindos:
    for(User userSeguindo : u.verSeguindo()){ 
      u.unfollow(userSeguindo);
    }

    // Removendo ele da lista de seguidores dos outros
    for(User seguidor : u.verSeguidores()){
      seguidor.unfollow(u);
    }

    // Apagando cada post dele
    for(Post p : u.getPosts()){
      // Remove o post dos favoritos dos usuários
      deletarPost(p);
    }
    return users.remove(u);
  }

  public void criarPost(User u, Post p){
    p.setID(postIDtracker);
    postIDtracker++;
    u.postar(p);
    posts.add(p);
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

  public ArrayList<Post> getAllPosts(){
    return posts;
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
    return false;
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
}
