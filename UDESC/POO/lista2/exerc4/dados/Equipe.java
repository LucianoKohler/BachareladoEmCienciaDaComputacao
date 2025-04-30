package dados;
import java.util.ArrayList;

public class Equipe<T> {
  // Limite máximo de alunos por equipe: 4
  private String nome;
  private ArrayList<T> alunos;

  // Construtor
  public Equipe(String nome) {
    this.nome = nome;
    this.alunos = new ArrayList<T>();
  }

  // Gets e Sets
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public boolean adicionarAluno(T aluno) {
    if (alunos.size() < 4) {
      alunos.add(aluno);
      return true;
    } else {
      System.out.println("Limite máximo de alunos atingido!");
      return false;
    }
  }

    public String toString() {
        String str = "";
        str += "Equipe N° " + nome + "\n";
        str += "Alunos: \n";
        for (int i = 0; i < alunos.size(); i++) {
            str += alunos.get(i).toString() + "\n";
        }
        return str;
    }
  }

