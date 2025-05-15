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
    for(User userNaBase : this.getAllUsers()){
      if(userNaBase.getUsername().equals(u.getUsername())){ // Se já há um username igual cadastrado
        return false;
      }
    }
    users.add(u);
    return true;  
  }
}
