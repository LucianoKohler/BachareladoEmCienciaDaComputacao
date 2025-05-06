package sistema;
import java.util.ArrayList;

import dados.Aluno;
import dados.Equipe;

public class Turma {
  // Presumindo um limite arbitrário de 40 alunos em uma turma
  private ArrayList<Aluno> alunos;

  // Construtor
  public Turma() {
    this.alunos = new ArrayList<Aluno>();
  }

  public boolean adicionarAluno(Aluno aluno) {
    if (alunos.size() < 40) {
      alunos.add(aluno);
      return true;
    } else {
      System.out.println("Limite máximo de alunos atingido!");
      return false;
    }
  }

  private void ordenaAlunosPorMedia(){
    for (int i = 0; i < alunos.size(); i++) {
      for (int j = 0; j < alunos.size() - 1; j++) {
        if (alunos.get(j).calcularMedia() > alunos.get(j + 1).calcularMedia()) {
          Aluno temp = alunos.get(j);
          alunos.set(j, alunos.get(j + 1));
          alunos.set(j + 1, temp);
        }
      }
    }
  }

  public ArrayList<Equipe> separarEmEquipes(){
    ordenaAlunosPorMedia();
    // Criando uma cópia da turma para poder manipular as equipes melhor
    ArrayList<Aluno> copiaTurma = alunos;
    ArrayList<Equipe> equipes = new ArrayList<>();
    int qtdEquipes = 0;
    
    if(copiaTurma.size() < 3) {
      System.out.println("A turma é pequena demais para separar em equipes");
      return null;
    }
    
    while(copiaTurma.size() > 0){
      Equipe eq = new Equipe(String.valueOf(qtdEquipes + 1));
      eq.adicionarAluno(copiaTurma.get(0)); // Primeiro aluno
      eq.adicionarAluno(copiaTurma.get(1)); // Segundo aluno
      eq.adicionarAluno(copiaTurma.get(copiaTurma.size() - 1)); // Último aluno

      copiaTurma.remove(0); // Remove o primeiro
      copiaTurma.remove(0); // Remove o segundo
      copiaTurma.remove(copiaTurma.size() -1); // Remove o último

      if(copiaTurma.size() == 1){ // Coloca o último zezinho na equipe
        eq.adicionarAluno(copiaTurma.get(0));
        copiaTurma.remove(0);

      }else if(copiaTurma.size() == 2){ // Coloca um zezinho na equipe anterior E o último na equipe atual
        equipes.get(qtdEquipes).adicionarAluno(copiaTurma.get(1));
        eq.adicionarAluno(copiaTurma.get(0));
        copiaTurma.remove(0); // Remove o penúltimo
        copiaTurma.remove(0); // Remove o último
      }

      equipes.add(eq);
      qtdEquipes++;
    };

    return equipes;
  }

  public String toString() {
    String str = "";
    for (Aluno aluno : alunos) {
      str += aluno.toString() + "\n";
    }
    return str;
  }
}
