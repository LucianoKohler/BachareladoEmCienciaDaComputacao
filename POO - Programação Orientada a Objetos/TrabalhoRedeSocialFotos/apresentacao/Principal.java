package apresentacao;

import dados.User;
import negocio.Sistema;

public class Principal {
    public static void main(String[] args){
    Sistema s = new Sistema();
    
    User u0 = new User("teste", "a123", "Conta teste", "Uma conta teste", "gemaplys.png");
    User u1 = new User("joaoInstagram", "Joao123", "João Inácio da Silva", "Apenas um cara tranks", null);
    User u2 = new User("FelipeDiasss", "Filipao", "Felipe Dias", "Conto os dias", null);
    User u3 = new User("JohnatanPipas", "Pipas123", "Johnatan Holz", "Adoro pipas!", null);

    s.cadastrarUser(u0);
    s.cadastrarUser(u1);
    s.cadastrarUser(u2);
    s.cadastrarUser(u3);
    
    TelaUser telaUser = new TelaUser(u0, s);
    telaUser.setVisible(true);
    // TelaGuest telaGuest = new TelaGuest(s);
    // telaGuest.setVisible(true);
  }
}
