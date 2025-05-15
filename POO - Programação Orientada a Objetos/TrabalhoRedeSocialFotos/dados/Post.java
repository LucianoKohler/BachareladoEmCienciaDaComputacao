package dados;

import java.util.ArrayList;

public class Post {
  private int ID;
  private User donoPost;
  private String legenda;
  private String img;
  private ArrayList<User> favoritadores;

  // Construtor
  public Post(int ID, User donoPost, String img, String legenda){
    this.ID = ID;
    this.donoPost = donoPost;
    this.legenda = legenda;
    this.img = img;
    this.favoritadores = new ArrayList<User>();
  }

  // Gets
  public int getID() {
    return ID;
  }
  public User getDonoPost() { // ID ??
    return donoPost;
  }
  public String getImg() {
    return img;
  }
  public String getLegenda() {
    return legenda;
  }
  public ArrayList<User> getFavoritadores() {
    return favoritadores;
  }

  // Métodos
  public void adicionarFavoritador(User u){ // ID ??
    this.favoritadores.add(u);
  }
}
