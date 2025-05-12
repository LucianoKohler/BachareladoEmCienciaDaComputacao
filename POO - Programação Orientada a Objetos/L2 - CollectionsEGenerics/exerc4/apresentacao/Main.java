package apresentacao;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dados.Aluno;
import dados.Equipe;
import sistema.Turma;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args){
    Turma t = new Turma();
    Scanner dados;

    try{
      dados = new Scanner(new File("dados.txt"), "UTF-8"); // UTF-8 Para ler caracteres especiais
    }catch(FileNotFoundException e){
      System.out.println("Arquivo de texto não encontrado!");
      dados = new Scanner(System.in, "UTF-8");
    }

    // Recebendo o arquivo/input para a turma
    int continuarLeitura = 1;
    while(continuarLeitura != -1){
      String nome = dados.nextLine();
      int idade = Integer.parseInt(dados.nextLine());
      double n1 = Double.parseDouble(dados.nextLine());
      double n2 = Double.parseDouble(dados.nextLine());
      double n3 = Double.parseDouble(dados.nextLine());
      double n4 = Double.parseDouble(dados.nextLine());
      double n5 = Double.parseDouble(dados.nextLine());
      continuarLeitura = Integer.parseInt(dados.nextLine());
      Aluno a = new Aluno(nome, idade, n1, n2, n3, n4, n5);
      t.adicionarAluno(a);
    }

    // Mostrando turma
    System.out.print("----------------------------------------------------\n");
    System.out.println("Alunos na turma: ");
    System.out.print("----------------------------------------------------\n");
    System.out.println(t.toString());

    // Separando as equipes
    System.out.print("----------------------------------------------------\n");
    System.out.println("Separando as equipes: ");
    System.out.print("----------------------------------------------------\n");
    ArrayList<Equipe> equipes = t.separarEmEquipes();
    equipes.forEach(eq -> System.out.println(eq.toString()));
  }
}
