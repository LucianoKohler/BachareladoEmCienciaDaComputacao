package apresentacao;

import exceptions.NomeInvalidoException;
import negocio.SistemaArquivos;
import dados.*;

public class Main {
  public static void main(String[] args) {
    SistemaArquivos s = new SistemaArquivos();

    // Instanciando elementos
    // try{ Musica radioactive = new Musica("Imagine Dragons - Radioactive", 276); } catch (NomeInvalidoException e) {}
    // try{ Video HIMYM = new Video("HIMYM S01E01", Qualidade.P1080); } catch (NomeInvalidoException e) {}
    // try{ Documento lista = new Documento("Lista de Compras", "blablabla"); } catch (NomeInvalidoException e) {}
    // try{ Documento resumo = new Documento("resumo", "blablabla"); } catch (NomeInvalidoException e) {}
    // try{ Documento notas = new Documento("notasPOO", "blablabla"); } catch (NomeInvalidoException e) {}
    try{ s.criarMusica("Imagine Dragons - Radioactive", "Downloads", 276); } catch (NomeInvalidoException e) {} 
    try{ s.criarVideo("HIMYM S01E01", "Downloads", Qualidade.P1080); } catch (NomeInvalidoException e) {} 
    try{ s.criarDocumento("Lista de Compras", "Downloads", "blablabla"); } catch (NomeInvalidoException e) {} 
    try{ s.criarDocumento("Resumo", "Area de Trabalho", "blablabla"); } catch (NomeInvalidoException e) {} 
    try{ s.criarDocumento("notasPOO", "Area de Trabalho", "blablabla"); } catch (NomeInvalidoException e) {} 

    
    // Instanciando elementos inválidos
    try{ s.criarMusica("Ph0nk[]", "Downloads", 221); } catch (NomeInvalidoException e) {}
    try{ s.criarDocumento("a", "Area de Trabalho", "blablabla"); } catch (NomeInvalidoException e) {}


    // Printando
    System.out.println(s.toString());

  }  
}
