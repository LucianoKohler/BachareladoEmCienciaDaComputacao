package apresentacao;

import java.io.File;

import javax.imageio.ImageIO;

import dados.Post;
import dados.User;
import negocio.Sistema;

public class Principal {
    public static void main(String[] args){
    Sistema s = new Sistema();
    
    /* IMPORTANTE: OS DADOS À SEGUIR SÃO TEMPORÁRIOS E SERÃO PERMANENTES AO IMPLEMENTAR A 3° PARTE DO TRABALHO: PERSISTÊNCIA DE DADOS COM JDBC */
    
    /* Gerando imagens para colocar nos posts/fotos de perfil */
    byte[] i1 = null;
    try { i1 = Sistema.ImageParaBytes(ImageIO.read(new File("imagens/foto1.jpg")));
    } catch (java.io.IOException e) { System.out.println(e.getMessage()); }

    byte[] i2 = null;
    try { i2 = Sistema.ImageParaBytes(ImageIO.read(new File("imagens/foto2.jpg")));
    } catch (java.io.IOException e) { System.out.println(e.getMessage()); }

    byte[] i3 = null;
    try { i3 = Sistema.ImageParaBytes(ImageIO.read(new File("imagens/foto3.jpg")));
    } catch (java.io.IOException e) { System.out.println(e.getMessage()); }

    byte[] i4 = null;
    try { i4 = Sistema.ImageParaBytes(ImageIO.read(new File("imagens/calendario1.jpg")));
    } catch (java.io.IOException e) { System.out.println(e.getMessage()); }

    byte[] i5 = null;
    try { i5 = Sistema.ImageParaBytes(ImageIO.read(new File("imagens/calendario2.jpg")));
    } catch (java.io.IOException e) { System.out.println(e.getMessage()); }

    byte[] i6 = null;
    try { i6 = Sistema.ImageParaBytes(ImageIO.read(new File("imagens/amigos.jpg")));
    } catch (java.io.IOException e) { System.out.println(e.getMessage()); }

    byte[] i7 = null;
    try { i7 = Sistema.ImageParaBytes(ImageIO.read(new File("imagens/pipa1.jpg")));
    } catch (java.io.IOException e) { System.out.println(e.getMessage()); }

    byte[] i8 = null;
    try { i8 = Sistema.ImageParaBytes(ImageIO.read(new File("imagens/pipa2.jpg")));
    } catch (java.io.IOException e) { System.out.println(e.getMessage()); }

    byte[] i9 = null;
    try { i9 = Sistema.ImageParaBytes(ImageIO.read(new File("imagens/pipa3.jpg")));
    } catch (java.io.IOException e) { System.out.println(e.getMessage()); }

    /* Instanciando usuários */
    User u0 = new User("teste", "a123", "Conta teste", "Uma conta teste", null);
    User u1 = new User("joaoInstagram", "Joao123", "João Inácio da Silva", "Apenas um cara tranks", i1);
    User u2 = new User("FelipeDiasss", "Filipao", "Felipe Dias", "Conto os dias", i4);
    User u3 = new User("JohnatanPipas", "Pipas123", "Johnatan Holz", "Adoro pipas!", i7);

    /* Cadastrando usuários */
    s.cadastrarUser(u0);
    s.cadastrarUser(u1);
    s.cadastrarUser(u2);
    s.cadastrarUser(u3);

    /* Criando 3 posts para cada usuário */
    Post p1 = new Post(u1, "Uma fotinha minha...", i1);
    Post p2 = new Post(u1, "Veja meu cafézinho", i2);
    Post p3 = new Post(u1, "Futebom com os manos", i3);
    Post p4 = new Post(u2, "Adoro calendários", i4);
    Post p5 = new Post(u2, "Veja meus calendários!", i5);
    Post p6 = new Post(u2, "Passeando com amigos", i6);
    Post p7 = new Post(u3, "Olha minha primeira pipa" , i7);
    Post p8 = new Post(u3, "Olha minha segunda pipa", i8);
    Post p9 = new Post(u3, "Olha minha terceira pipa" , i9);

    /* Postando os posts */
    s.criarPost(u1, p1);
    s.criarPost(u1, p2);
    s.criarPost(u1, p3);
    s.criarPost(u2, p4);
    s.criarPost(u2, p5);
    s.criarPost(u2, p6);
    s.criarPost(u3, p7);
    s.criarPost(u3, p8);
    s.criarPost(u3, p9);

    /* Descomente as duas linhas abaixo e comente as duas próximas para entrar logado diretamente */
    // TelaUser telaUser = new TelaUser(u0, s);
    // telaUser.setVisible(true);
    TelaGuest telaGuest = new TelaGuest(s);
    telaGuest.setVisible(true);
  }
}
