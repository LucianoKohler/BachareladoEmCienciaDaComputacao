package dados;

import java.util.ArrayList;

public class User {
  private int ID;
  private String username;
  private String senha;
  private String nomeCompleto;
  private String biografia;
  private ArrayList<Post> posts;
  private ArrayList<Post> favoritos;
  private ArrayList<User> seguidores;
  private ArrayList<User> seguindo;

  // Construtor
  public User(String username, String senha, String nomeCompleto, String biografia){
    this.username = username;
    this.senha = senha;
    this.nomeCompleto = nomeCompleto;
    this.biografia = biografia;
    this.posts = new ArrayList<Post>();
    this.favoritos = new ArrayList<Post>();
    this.seguidores = new ArrayList<User>();
    this.seguindo = new ArrayList<User>();
  }

  // Gets
  public int getID() {
    return ID;
  }
  public String getUsername() {
    return username;
  }
  public String getSenha() {
    return senha;
  }
  public String getNomeCompleto() {
    return nomeCompleto;
  }
  public String getBiografia() {
    return biografia;
  }
  public ArrayList<Post> getPosts() {
    return posts;
  }
  public ArrayList<Post> getFavoritos() {
    return favoritos;
  }
  public ArrayList<User> verSeguidores() {
    return seguidores;
  }
  public ArrayList<User> verSeguindo() {
    return seguindo;
  }

  // Sets
  public void mudarUsername(String username) {
    this.username = username;
  }
  public void mudarSenha(String senha) {
    this.senha = senha;
  }
  public void mudarNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }
  public void mudarBiografia(String biografia) {
    this.biografia = biografia;
  }
  public void setID(int id) {
    ID = id;
  }

  // Outros métodos
  public void follow(User u){ this.seguindo.add(u); } 

  public void novoSeguidor(User u){ this.seguidores.add(u); } 

  public void unfollow(User u){ this.seguindo.remove(u); } 

  public void removerSeguidor(User u){ this.seguidores.remove(u); } 
  
  public void postar(Post p){ this.posts.add(p); } 

  public void deletarPost(Post p){ this.posts.remove(p); } 

  public void favoritarPost(Post p){ this.favoritos.add(p); }

  public boolean desFavoritarPost(Post p){ return this.favoritos.remove(p); } 

  public String toString(){
    return "Usuário N° " + ID + "\n Username: " + username + "\n Nome Completo: " + nomeCompleto + "\n";
  }

  public String toStringCompleto(){
    String str = "";
    str +=  "Usuário N° " + ID;
    str += "\n Username: " + username;
    str += "\n Senha: " + senha;
    str += "\n Nome Completo: " + nomeCompleto;
    str += "\n Biografia: " + biografia;
    str += "\n Seguidores: " + seguidores.size();
    str += "\n Seguindo: " + seguindo.size();

    return str;
  }
}
