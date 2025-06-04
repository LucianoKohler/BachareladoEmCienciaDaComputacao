package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import dados.*;
import exceptions.NomeInvalidoException;

public class SistemaArquivos {
  private HashMap<String, ArrayList<Arquivo>> diretorios;

  public SistemaArquivos(){
    diretorios = new HashMap<String,ArrayList<Arquivo>>();
  }

  // Auxiliar
  public void insereNoMap(Arquivo a, String diretorio){
    for(String dir : diretorios.keySet()){
      if(dir.equals(diretorio)){
        diretorios.get(dir).add(a);
        return;
      }
    }

    // Se ele não encontrou o diretório, criar um novo
    diretorios.put(diretorio, new ArrayList<Arquivo>());
    diretorios.get(diretorio).add(a);
  }

  public void criarDocumento(String nome, String diretorio, String texto) throws NomeInvalidoException{
    Documento d;
    try{
      d = new Documento(nome, texto);
    } catch (NomeInvalidoException e){
      return;
    }
    insereNoMap(d, diretorio);
  }

  public void criarMusica(String nome, String diretorio, int duracao) throws NomeInvalidoException{
    Musica s;
    try{
      s = new Musica(nome, duracao);
    } catch (NomeInvalidoException e){
      return;
    }
    insereNoMap(s, diretorio);
  }

    public void criarVideo(String nome, String diretorio, Qualidade qualidade) throws NomeInvalidoException{
    Video v;
    try{
      v = new Video(nome, qualidade);
    } catch (NomeInvalidoException e){
      return;
    }
    insereNoMap(v, diretorio);
  }

  public String toString(){
    StringBuilder str = new StringBuilder();

    diretorios.forEach((diretorio, arquivos) -> {
      
      str.append(diretorio + ": \n");
      for(Arquivo arq : arquivos){
        str.append("\n\t" + arq.toString() + "\n");
      }

      str.append("\n");
    });
  return String.valueOf(str);
  }

}
