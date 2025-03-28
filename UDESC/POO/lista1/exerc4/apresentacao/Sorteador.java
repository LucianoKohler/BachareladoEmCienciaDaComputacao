package apresentacao;

import dados.Pessoa;

public class Sorteador {

  public int sortearProximo(Pessoa[] pessoas, int quantPessoas) {
    if (quantPessoas == 0) { return quantPessoas; }

    int i = (int) (Math.random() * 5); // gera entre 0 e 4

    if (pessoas[i] != null) { // Pessoas já geradas se tornam null
      System.out.println("A pessoa sorteada foi: " + pessoas[i].getNome());
      pessoas[i] = null;
      quantPessoas--;
    } else {
        // Tenta de novo recursivamente
        return sortearProximo(pessoas, quantPessoas);
    }

    return quantPessoas;
}
  public static void main(String[] args){
    Pessoa pessoas[] = new Pessoa[5];
    int quantPessoas = 5;

    pessoas[0] = new Pessoa("Jonas", 20);
    pessoas[1] = new Pessoa("Maria", 25);
    pessoas[3] = new Pessoa("Ana", 35);
    pessoas[2] = new Pessoa("Pedro", 30);
    pessoas[4] = new Pessoa("Lucas", 40);

    Sorteador sorteador = new Sorteador();
    System.out.println("Sorteando pessoas: ");
    while(quantPessoas > 0){
      quantPessoas = sorteador.sortearProximo(pessoas, quantPessoas);
    }
  }
}
