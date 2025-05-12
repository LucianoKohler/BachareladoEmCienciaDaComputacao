import java.util.ArrayList;

public class Sistema {
  private ArrayList<Pessoa> criancas;
  private ArrayList<Pessoa> adolescentes;
  private ArrayList<Pessoa> jovens;
  private ArrayList<Pessoa> adultos;
  private ArrayList<Pessoa> idosos;

  public Sistema() {
    criancas = new ArrayList<>();
    adolescentes = new ArrayList<>();
    jovens = new ArrayList<>();
    adultos = new ArrayList<>();
    idosos = new ArrayList<>();
  }

  public void AdicionarEOrdenarPessoas(Pessoa pessoa){
    if (pessoa.getIdade() <= 12) {
      criancas.add(pessoa);
      criancas.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
    } else if (pessoa.getIdade() <= 18) {
      adolescentes.add(pessoa);
      adolescentes.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
    } else if (pessoa.getIdade() <= 25) {
      jovens.add(pessoa);
      jovens.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
    } else if (pessoa.getIdade() <= 59) {
      adultos.add(pessoa);
      adultos.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
    } else {
      idosos.add(pessoa);
      idosos.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
    }
  }

  public void mostrarTodos(){
    System.out.println("\nCriancas:");
    for (Pessoa p : criancas) {
      System.out.println(p.toString());
    }
    System.out.println("\nAdolescentes:");
    for (Pessoa p : adolescentes) {
      System.out.println(p.toString());
    }
    System.out.println("\nJovens:");
    for (Pessoa p : jovens) {
      System.out.println(p.toString());
    }
    System.out.println("\nAdultos:");
    for (Pessoa p : adultos) {
      System.out.println(p.toString());
    }
    System.out.println("\nIdosos:");
    for (Pessoa p : idosos) {
      System.out.println(p.toString());
    }
    System.out.println("\n---------------------------------------------------");
  }
}
