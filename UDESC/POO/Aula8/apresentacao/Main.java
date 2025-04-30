package apresentacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

  public void gravaTexto(String caminho, String linha){
    Writer arquivo;
    try {
      arquivo = new FileWriter(caminho, true);
      arquivo.write(linha + "\n");
      arquivo.close();
    } catch (IOException e){
      System.err.println("Erro ao gravar arquivo: " + e.getMessage());
      System.exit(0);
    }
  }

  public void leTexto(String caminho){
    BufferedReader arquivo;
    try {
      arquivo = new BufferedReader(new FileReader(caminho));
      String linha = arquivo.readLine();
      while (linha != null) {
        System.out.println(linha);
        linha = arquivo.readLine();
      }
      arquivo.close();
    } catch (IOException e){
      System.err.println("Erro ao ler arquivo: " + e.getMessage());
      System.exit(0);
    }
  }
  
  public static void cadastrarPessoa() {}

  public static void removerPessoa() {}

  public static void mostrarPessoas() {}
  public static void main(String[] args){


  }
}
