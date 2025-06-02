package dados;

import java.util.ArrayList;

import exceptions.pilhaCheiaException;
import exceptions.pilhaVaziaException;

public class Pilha<T> {
  private int limite;
  private ArrayList<T> elementos;

  public Pilha(int limite) {
    this.limite = limite;
    this.elementos = new ArrayList<>(limite);
  }

  public void inserir (T obj) throws pilhaCheiaException {
    if(elementos.size() >= limite){
      throw new pilhaCheiaException();
    }else{
      elementos.add(obj);
    }
  }

  public T remover() throws pilhaVaziaException {
    if(elementos.size() == 0){
      throw new pilhaVaziaException();
    }else{
      T elemento = elementos.get(elementos.size() - 1);
      elementos.remove(elementos.size() - 1); // Remove by index to ensure correct element is removed
      return elemento;
    }
  }

  public int getLimite(){
    return limite;
  }

  public int getSize(){
    return elementos.size();
  }

}
