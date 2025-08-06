package apresentacao;

import negocio.Sistema;

public class Principal {
    public static void main(String[] args){
    Sistema s = new Sistema();

    /* Descomente as duas linhas abaixo e comente as duas próximas para entrar logado diretamente */
    // TelaUser telaUser = new TelaUser(s.buscarPorUsername("joaoInstagram"), s);
    // telaUser.setVisible(true);
    TelaGuest telaGuest = new TelaGuest(s);
    telaGuest.setVisible(true);
  }
}
