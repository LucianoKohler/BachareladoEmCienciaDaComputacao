package apresentacao;

import dados.Pessoa;
import java.util.ArrayList;

public class Sorteador {

  public Pessoa sortearProximo(ArrayList<Pessoa> pessoas) {
    if (pessoas.size() == 0) { return null; }

    int i = (int) (Math.random() * (pessoas.size())); // gera entre 0 e n, sendo n o número de pessoas

    Pessoa sorteada = pessoas.get(i);
    pessoas.remove(i);
    return sorteada;
}
  public static void main(String[] args){
    ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

    pessoas.add(new Pessoa("Jonas", 20));
    pessoas.add(new Pessoa("Maria", 25));
    pessoas.add(new Pessoa("Ana", 35));
    pessoas.add(new Pessoa("Pedro", 30));
    pessoas.add(new Pessoa("Lucas", 40));

    Pessoa p = null;
    Sorteador s = new Sorteador();
    System.out.println("Sorteando pessoas: ");
    
    while (true) {
      p = s.sortearProximo(pessoas);
      if (p != null) {
        System.out.println(p.toString());
      }else {
        break;
      }
    }

    System.out.println("Sorteio finalizado");
  }
}
