package apresentacao;

import negocio.Sistema;

import java.util.ArrayList;
import java.util.Scanner;

import dados.*;

public class OldMain {
  public User userLogado = null;
  public Sistema s = new Sistema();
  public Scanner scanner = new Scanner(System.in);

  public void cadastrarUser(){
    System.out.print("Insira seu username: ");
    String username = scanner.nextLine();
    
    System.out.print("Insira sua senha: ");
    String senha = scanner.nextLine();
  
    System.out.print("Insira seu nome completo: ");
    String nome = scanner.nextLine();

    System.out.print("Insira sua biografia: ");
    String biografia = scanner.nextLine();

    User novoUser = new User(username, senha, nome, biografia);
    boolean sucesso = s.cadastrarUser(novoUser);
    if(!sucesso){
      System.out.println("Erro: nome de usuário já cadastrado.");
    }else{
      System.out.println("Usuário cadastrado com sucesso.");
      userLogado = novoUser;
    }
  }

  public void fazerLogin(){
    System.out.print("Insira seu username: ");
    String username = scanner.nextLine();

    System.out.print("Insira a senha: ");
    String senha = scanner.nextLine();

    userLogado = s.fazerLogin(username, senha);
    if(userLogado == null){
      System.out.println("Usuário ou senha inválidos");
    }else{
      System.out.println("Login realizado com sucesso");
    }
  }

  public void imprimeMenuGuest(){
    System.out.println("Não há uma conta logada, gostaria de logar em uma conta ou se cadastrar no software?");
    System.out.println("0. Sair do programa");
    System.out.println("1. Cadastro");
    System.out.println("2. Login");
    System.out.print("Sua escolha: ");
    int escolha = Integer.parseInt(scanner.nextLine());
    switch (escolha) {
      case 0:
        System.exit(0);
        break;
      case 1:
        cadastrarUser();
        break;
      case 2:
        fazerLogin();
        break;
    
      default:
        System.out.println("Escolha inválida.");
        break;
    }
 }

  public void imprimeMenuUser(){
    if(userLogado.equals(null)){
      imprimeMenuGuest();
    }else{
      System.out.println("Bem vindo, " + userLogado.getNomeCompleto() + "!");
      System.out.println("0. Sair do programa");
      System.out.println("1. Postar"); // ver
      System.out.println("2. Ver posts no seu feed");
      System.out.println("3. Ver/deletar seus posts");
      System.out.println("4. Ver seus seguidores");
      System.out.println("5. Ver/deixar de seguir quem você segue");
      System.out.println("6. Seguir um usuário");
      System.out.println("7. Ver/desfavoritar posts favoritos"); 
      System.out.println("8. ver/mudar perfil"); 
      System.out.println("9. logout"); 
      System.out.println("10. Apagar conta"); 
      System.out.print("Sua escolha: ");

      /*
      POSTAR
      ver:
        posts no feed
        seus próprios posts
        usuarios para seguir
        posts favoritos
      conta:
        perfil:
          mudar credenciais
          ver seus seguidores
          ver seus seguindo
          apagar conta
      logout
      
      */
    }
  }

  public void postar(){
    System.out.print("Insira a imagem (beta, insira apenas uma string): ");
    String img = scanner.nextLine();

    System.out.print("Insira uma legenda para o post: ");
    String legenda = scanner.nextLine();
    Post p = new Post(userLogado, img, legenda);

    s.criarPost(userLogado, p);
    System.out.println("Postado com sucesso");
  }

  public void deletarPost(Post p){
    s.deletarPost(p);
  }

  public void verProximoPostDisponivel(int qtdPostsVistos){
    ArrayList<Post> posts = s.verPostsDisponiveis(userLogado);
    if (posts.isEmpty()){
      System.out.println("Não há posts disponíveis no momento");
    }else{
        if(qtdPostsVistos >= posts.size()){
          System.out.println("Acabaram os posts do seu feed, voltando ao menu principal...");
          return;
        }

        Post post = posts.get(qtdPostsVistos);
        System.out.println(post.toString());

        if(post.getDonoPost() == userLogado){ System.out.println("Nota: Esse post é seu!"); }
        System.out.println();
        System.out.println("O que fazer agora?");
        System.out.println("1. Ver o próximo post");
        System.out.println("2. Voltar para o menu");
        System.out.println("3. Favoritar post");
        System.out.print("Sua escolha: ");
        int escolha = Integer.parseInt(scanner.nextLine());
        switch (escolha) {
          case 1:
            verProximoPostDisponivel(qtdPostsVistos+1);
            break;

          case 2:
            return;
            
          case 3:
            favoritarPost(post);
            System.out.println("Indo para o próximo post: ");
            verProximoPostDisponivel(qtdPostsVistos+1);

          default:
            break;
        }
    }
  }

  public void verPropriosPosts(int qtdPostsPropriosVistos){
    ArrayList<Post> posts = s.verPostsDeUmUser(userLogado);
    if (posts.isEmpty()){
      System.out.println("Você ainda não postou nada.");
    }else{
        if(qtdPostsPropriosVistos >= posts.size()){
          System.out.println("Acabaram os seus posts, voltando ao menu principal...");
          return;
        }

        Post post = posts.get(qtdPostsPropriosVistos);
        System.out.println(post.toString());
        
        System.out.println("O que fazer agora?");
        System.out.println("1. Ver o próximo post");
        System.out.println("2. Voltar para o menu");
        System.out.println("3. Deletar post");
        System.out.print("Sua escolha: ");
        int escolha = Integer.parseInt(scanner.nextLine());
        switch (escolha) {
          case 1:
            verPropriosPosts(qtdPostsPropriosVistos+1);
            break;

          case 2:
            return;
            
          case 3:
            deletarPost(post);
            System.out.println("Indo para o próximo post: ");
            verPropriosPosts(qtdPostsPropriosVistos);
            // Sem incrementar 1 pois a lista também perdeu 1 elemento
          default:
            break;
        }
    }
  }

  public void verSeguidoresDeUmUser(){
    ArrayList<User> seguidores = s.verSeguidoresDeUmUser(userLogado);
    if(seguidores.isEmpty()){
      System.out.println("Você não possui seguidores...");
    }else{
      for(User seguidor : seguidores){
        System.out.println(seguidor.toString());
      }
    }
  }

  public void verSeguindoDeUmUser(int qtdSeguindoVistos){
    ArrayList<User> seguindos = s.verSeguindoDeUmUser(userLogado);
    if(seguindos.isEmpty()){
      System.out.println("Você não segue ninguém");
    }else{
      
      User user = seguindos.get(qtdSeguindoVistos);
      System.out.println(user.toString());
      System.out.println("O que fazer agora?");
      System.out.println("1. Ver o próximo user que você segue");
      System.out.println("2. Voltar para o menu");
      System.out.println("3. Deixar de seguir este user");
      System.out.print("Sua escolha: ");
      int escolha = Integer.parseInt(scanner.nextLine());
      switch (escolha) {
        case 1:
          verSeguindoDeUmUser(qtdSeguindoVistos+1);
        break;
        
        case 2:
        return;
        
        case 3:
        s.unfollowUser(userLogado, user);
        System.out.println("Você não segue " + user.getUsername() + " mais, indo para o próximo user");
        verSeguindoDeUmUser(qtdSeguindoVistos); 
        // Sem incrementar 1 pois a lista também perdeu 1 elemento
        default:
        break;
      }
    }
  }

  public void verTodosOsUsers(int modificador){ // Se o modificador for -1, não mostrar o usuário logado
    ArrayList<User> users = s.getAllUsers();
    System.out.println("Veja os usuários disponíveis: ");
    for(User u : users){
      if(u.equals(userLogado) && modificador == -1){ continue; }
      System.out.println(u.toString());
    }
  }

  public User escolherUser(){
    verTodosOsUsers(-1);
    System.out.print("Escolha um usuário pelo seu username: ");
    String escolha = scanner.nextLine();
    for(User u : s.getAllUsers()){
      if(u.getUsername().equals(escolha)){
        return u;
      }
    }
    System.out.println("\nUsuário não encontrado");
    return null;
  }

  public void seguirUser(){
    User escolhido = escolherUser();

    if(escolhido == userLogado){
      System.out.println("Você não pode se seguir!");
      return;
    }

    if(escolhido != null){
      userLogado.follow(escolhido);
      escolhido.novoSeguidor(userLogado);
      System.out.println("\nVocê agora segue: " + escolhido.getUsername());
    }
  }

  public void verProximoPostFavoritoDisponivel(int qtdPostsFavVistos){
        ArrayList<Post> posts = userLogado.getFavoritos();
    if (posts.isEmpty()){
      System.out.println("Não há posts favoritos");
    }else{
        if(qtdPostsFavVistos >= posts.size()){
          System.out.println("Acabaram os posts favoritos, voltando ao menu principal...");
          return;
        }

        Post post = posts.get(qtdPostsFavVistos);
        System.out.println(post.toString());
        System.out.println("O que fazer agora?");
        System.out.println("1. Ver o próximo post favorito");
        System.out.println("2. Voltar para o menu");
        System.out.println("3. Remover o post dos favoritos");
        System.out.print("Sua escolha: ");
        int escolha = Integer.parseInt(scanner.nextLine());
        switch (escolha) {
          case 1:
            verProximoPostFavoritoDisponivel(qtdPostsFavVistos+1);
            break;

          case 2:
            return;
            
          case 3:
            s.desFavoritarPost(userLogado, post);
            System.out.println("Post desfavoritado, indo para o próximo post favorito: ");
            verProximoPostFavoritoDisponivel(qtdPostsFavVistos);
            // Sem incrementar 1 pois a lista também perdeu 1 elemento
          default:
            break;
        }
    }
  }
  
  public void verPerfil(){
  System.out.println("Seu perfil: ");
  System.out.println(userLogado.toStringCompleto());
  System.out.print("Quer mudar alguma informação do seu perfil? (S - sim, N - não): ");
  String escolha = scanner.nextLine();
  if(escolha.equals("S")){
      mudarCredenciaisPerfil();
    }
  }

  public void mudarCredenciaisPerfil(){
    System.out.println("Qual credencial será mudada? ");
    System.out.println("1. Username");
    System.out.println("2. Senha");
    System.out.println("3. Nome Completo");
    System.out.println("4. Biografia");

    int escolha = Integer.parseInt(scanner.nextLine());

    System.out.print("Insira a nova credencial: ");
    String credencial = scanner.nextLine();
    s.mudarCredenciaisPerfil(userLogado, credencial, escolha);
  }

  public boolean favoritarPost(Post p){
    boolean sucesso = s.favoritarPost(userLogado, p);
    if(!sucesso){
      System.out.println("Post já favoritado, remova-o no menu de favoritos");
      return false;
    }else{
      System.out.println("Post favoritado com sucesso!");
      return true;
    }
  }

  public void logout(){
    System.out.println("Saindo da conta...");
    userLogado = null;
  }

  public void apagarConta(){
    System.out.print("Você tem CERTEZA disso? (Escreva SIM para confirmar): ");
    String confirmacao = scanner.nextLine();
    if(confirmacao.equals("SIM")){
      System.out.println("Conta deletada com sucesso");
      s.deletarUser(userLogado);
      userLogado = null;
    }
  }
  
  public static void oldMain(String[] args) {
    OldMain m = new OldMain();
    int escolha = -1;
    
    // Criando uma base de usuários, cada um com 3 posts: 
    User u1 = new User("joaoInstagram", "Joao123", "João Inácio da Silva", "Apenas um cara tranks");
    User u2 = new User("FelipeDiasss", "Filipao", "Felipe Dias", "Conto os dias");
    User u3 = new User("JohnatanPipas", "Pipas123", "Johnatan Holz", "Adoro pipas!");
    m.s.cadastrarUser(u1);
    m.s.cadastrarUser(u2);
    m.s.cadastrarUser(u3);
    
    Post p1 = new Post(u1, "imagemMinha.png", "Uma fotinha minha...");
    Post p2 = new Post(u1, "cafeDaManha.jpg", "Veja meu cafézinho");
    Post p3 = new Post(u1, "futComManos.jpg", "Futebom com os manos");
    Post p4 = new Post(u2, "calendarios.png", "Adoro calendários");
    Post p5 = new Post(u2, "muitosDias.webp", "Veja meus calendários!");
    Post p6 = new Post(u2, "passeioBelo.jpg", "Passeando com amigos");
    Post p7 = new Post(u3, "primeiraPipa.jgp", "Olha minha primeira pipa" );
    Post p8 = new Post(u3, "segundaPipa.jpg", "Olha minha segunda pipa");
    Post p9 = new Post(u3, "terceiraPipa.jpg", "Olha minha terceira pipa" );
    m.s.criarPost(u1, p1);
    m.s.criarPost(u1, p2);
    m.s.criarPost(u1, p3);
    m.s.criarPost(u2, p4);
    m.s.criarPost(u2, p5);
    m.s.criarPost(u2, p6);
    m.s.criarPost(u3, p7);
    m.s.criarPost(u3, p8);
    m.s.criarPost(u3, p9);
    
    while(escolha != 0){
      System.out.println("====================================");

      if(m.userLogado == null){
        m.imprimeMenuGuest();
      }else{
        m.imprimeMenuUser();
        escolha = Integer.parseInt(m.scanner.nextLine());
        switch (escolha) {
          case 0:
            System.out.println("Saindo do programa...");
            break;
          case 1:
            m.postar();
            break;
          case 2:
            m.verProximoPostDisponivel(0); // 0 pois seria como se ele acabasse de abrir o feed
            break;
          case 3:
            m.verPropriosPosts(0);
            break;
          case 4:
            m.verSeguidoresDeUmUser();
            break;
          case 5:
            m.verSeguindoDeUmUser(0);
            break;
          case 6:
            m.seguirUser();
            break;
          case 7:
            m.verProximoPostFavoritoDisponivel(0);
          break;
          case 8:
            m.verPerfil();
            break;
          case 9:
            m.logout();
            break;
          case 10:
          m.apagarConta();
            break;
        
          default:
            System.out.println("Escolha inválida");
            break;
        }
      }
      System.out.println();  
    }
  }
}
