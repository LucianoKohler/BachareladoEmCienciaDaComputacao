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
    return users.remove(u);
    // Se eu remover o user, ele é removido das listas dos outros seguidores e dos favoritados de um post?
    // A função já retorna se o valor foi encontrado no banco
  }

  public void criarPost(User u, Post p){
    p.setID(postIDtracker);
    postIDtracker++;
    u.postar(p);
    posts.add(p);
  }

  public void deletarPost(Post p){
    p.getDonoPost().deletarPost(p);
    posts.remove(p);
  }

  public ArrayList<Post> verPostsDisponiveis(User u){
    ArrayList<Post> postsVisiveis = new ArrayList<Post>();
    for(Post post : posts){
      if(post.getDonoPost().verSeguidores().contains(u)){
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

  public boolean desFavoritarPost(User u, Post p){
    boolean a = p.removerFavoritador(u);
    boolean b = u.desFavoritarPost(p);
    return (a && b);
  }

  public ArrayList<User> verSeguidoresDeUmUser(User u){
    return u.verSeguidores();
  }
  public ArrayList<User> verSeguindoDeUmUser(User u){
    return u.verSeguindo();
  }
}
