package dados;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Post {
  private int ID;
  private User donoPost;
  private String legenda;
  private byte[] imagem;
  private ArrayList<User> favoritadores;

  // Construtor
  public Post(User donoPost, String legenda, byte[] imagem){
    this.donoPost = donoPost;
    this.legenda = legenda;
    this.imagem = (imagem == null) ? carregarImagemPadrao() : imagem;
    this.favoritadores = new ArrayList<User>();
  }

  // Gets
  public int getID() {
    return ID;
  }
  public User getDonoPost() { // ID ??
    return donoPost;
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

  public void setID(int id) {
    ID = id;
  }

  // Métodos
public static byte[] carregarImagemPadrao() {
  try {
      String caminhoImagem = System.getProperty("user.dir") + "/imagens/fotosPost/postSemImagem.png"; 
      BufferedImage imagem = ImageIO.read(new File(caminhoImagem));
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(imagem, "png", baos);
      return baos.toByteArray();
  } catch (Exception e) {
      e.printStackTrace();
      return null;
  }
}

  public static byte[] ImageParaBytes(BufferedImage imagem, String formato) {
    if(imagem == null){ return null; }
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        ImageIO.write(imagem, formato, baos);
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

  public void adicionarFavoritador(User u){
    this.favoritadores.add(u);
  }

  public boolean removerFavoritador(User u){
    return this.favoritadores.remove(u);
  }

  public String toString(){
    return "\nPost n° " + ID + "\n Legenda do post: " + legenda + "\n";
  }
}
