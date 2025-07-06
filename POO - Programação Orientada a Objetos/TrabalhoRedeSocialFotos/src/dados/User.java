package dados;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class User {
  private int id;
  private String username;
  private String senha;
  private String nomeCompleto;
  private String biografia;
  private byte[] imagemPerfil;
  private ArrayList<Post> posts;
  private ArrayList<Post> favoritos;
  private ArrayList<User> seguidores;
  private ArrayList<User> seguindo;

  // Construtor
  public User(int id, String username, String senha, String nomeCompleto, String biografia, byte[] imagemPerfil){
    this.id = id;
    this.username = username;
    this.senha = senha;
    this.nomeCompleto = nomeCompleto;
    this.biografia = biografia;
    this.imagemPerfil = (imagemPerfil == null) ? carregarImagemPadrao() : imagemPerfil;// Pode ou não ter imagem
    this.posts = new ArrayList<Post>();
    this.favoritos = new ArrayList<Post>();
    this.seguidores = new ArrayList<User>();
    this.seguindo = new ArrayList<User>();
  }

  // Gets
  public int getId() {
    return id;
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
  public byte[] getFotoPerfil() {
    return imagemPerfil;
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
  public void setId(int id) {
    this.id = id;
  }
  public void setFotoPerfil(byte[] imagemPerfil) {
    this.imagemPerfil = imagemPerfil;
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

  public static byte[] carregarImagemPadrao() {
    try {
        String caminhoImagem = System.getProperty("user.dir") + "/src/imagens/fotosPerfil/DEFAULT.png"; 
        BufferedImage imagem = ImageIO.read(new File(caminhoImagem));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imagem, "png", baos);
        return baos.toByteArray();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    User user = (User) obj;
    return username != null ? username.equals(user.username) : user.username == null;
}

}
