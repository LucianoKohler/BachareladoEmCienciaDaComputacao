package dados;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Post {
  private int id;
  private int idDonoPost;
  private String legenda;
  private byte[] imagem;
  private ArrayList<User> favoritadores;

  // Construtor
  public Post(int id, int idDonoPost, String legenda, byte[] imagem){
    this.id = id;
    this.idDonoPost = idDonoPost;
    this.legenda = legenda;
    this.imagem = (imagem == null) ? carregarImagemPadrao() : imagem;
    this.favoritadores = new ArrayList<User>();
  }

  // Gets
  public int getId() {
    return id;
  }
  public int getDonoPost() { // id ??
    return idDonoPost;
  }
  public byte[] getImagem() {
    return imagem;
  }
  public String getLegenda() {
    return legenda;
  }
  public ArrayList<User> getFavoritadores() {
    return favoritadores;
  }

  public void setId(int id) {
    this.id = id;
  }

  // Métodos
public static byte[] carregarImagemPadrao() {
  try {
      String caminhoImagem = System.getProperty("user.dir") + "/src/imagens/fotosPost/postSemImagem.png"; 
      BufferedImage imagem = ImageIO.read(new File(caminhoImagem));
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(imagem, "png", baos);
      return baos.toByteArray();
  } catch (Exception e) {
      e.printStackTrace();
      return null;
  }
}

  public void adicionarFavoritador(User u){
    this.favoritadores.add(u);
  }

  public boolean removerFavoritador(User u){
    return this.favoritadores.remove(u);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Post other = (Post) obj;
    return id == other.id;
  }
}
