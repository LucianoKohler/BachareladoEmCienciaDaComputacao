package animais;

public class Esquilo extends Animal {

    public Esquilo(String nome) {
        super(nome);
    }

    @Override
    public String emitirSom() {
        return this.getNome() + ": Squeak, squeak!";
    }  
}
