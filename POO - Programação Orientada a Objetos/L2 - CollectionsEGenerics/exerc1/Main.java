import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) {
    Sistema s = new Sistema();
    Scanner dados;
    int qtdPessoas = 0;

    try{
      dados = new Scanner(new File("dados.txt"), "UTF-8"); // UTF-8 Para ler caracteres especiais
    }catch(FileNotFoundException e){
      System.out.println("Arquivo de texto não encontrado!");
      dados = new Scanner(System.in, "UTF-8");
    }
    int continuarLeitura = 1;
    while(continuarLeitura != -1){
      String nome = dados.nextLine();
      int idade = Integer.parseInt(dados.nextLine());
      String cpf = dados.nextLine();
      String cidade = dados.nextLine();
      continuarLeitura = Integer.parseInt(dados.nextLine());

      Pessoa p = new Pessoa(nome, idade, cpf, cidade);
      s.AdicionarEOrdenarPessoas(p);
      qtdPessoas++;
      System.out.println("Pessoas adicionadas: " + qtdPessoas);
      s.mostrarTodos();
    }
  }
}
