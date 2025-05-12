package animais;
public class Gato extends Animal {

  public Gato(String nome) {
    super(nome);
  }
  
  public String emitirSom() {
    return this.getNome() + ": Miau, miau!";
  }
} 
