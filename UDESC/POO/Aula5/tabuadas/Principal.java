
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Principal {
  public static List<Integer> tabuada (int n){
    List<Integer> tabuada = new ArrayList<Integer>();
    for (int i = 1; i <= 10; i++){
      tabuada.add(i*n);
    }
    return tabuada;
  }

  public static void main(String[] args) {
    Map<Integer, List<Integer>> todasAsTabuadas = new HashMap<Integer, List<Integer>>();
    for (int i = 1; i <= 10; i++){
      todasAsTabuadas.put(i, tabuada(i));
    }

    todasAsTabuadas.forEach((chave, tabuada) -> {
      System.out.println("Tabuada do " + chave + ": " + tabuada);
    });
  }
}
