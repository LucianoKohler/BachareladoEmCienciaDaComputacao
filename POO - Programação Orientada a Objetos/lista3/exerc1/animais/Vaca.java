package animais;
public class Vaca extends Animal {
    public Vaca(String nome) {
        super(nome);
    }

    public String emitirSom() {
        return this.getNome() + ": Mooooo!";
    }
  
}
