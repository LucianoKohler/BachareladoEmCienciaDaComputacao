package negocio;
import dados.*;
import java.util.ArrayList;

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

  public boolean adicionarUser(User u){
    for(User userNaBase : getAllUsers()){
      if(userNaBase.getUsername().equals(u.getUsername())){ // Se já há um username igual cadastrado
        return false;
      }
    }
    users.add(u);
    return true;  
  }

  public boolean deletarUser(User u){
    return users.remove(u);
    // A função já retorna se o valor foi encontrado no banco
  }

  public void criarPost(User u, Post p){
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

  public ArrayList<Post> verTodosOsPosts(){
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

  public void mudarCredenciaisPerfil(String s, int escolha){
    switch (escolha) {
      case 1: // username
        break;
      case 2: // senha
        break;
      case 3: // nome completo
        break;
      case 4: // biografia
        break;    
      default:
        break;
    }
  }
}
