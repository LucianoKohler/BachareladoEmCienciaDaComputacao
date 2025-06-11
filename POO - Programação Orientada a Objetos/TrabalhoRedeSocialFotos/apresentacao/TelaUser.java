package apresentacao;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dados.User;
import negocio.Sistema;
import apresentacao.componentes.ImagemCircular;

import java.awt.Component;
import java.awt.Dimension;

public class TelaUser extends JFrame {
  
  /* INSTANCIANDO ELEMENTOS */
  public JPanel painelPrincipal = new JPanel();
  public JLabel labelGuest = new JLabel("Bem vindo, usuário!"); // Setado no construtor
  public JPanel paineisPainel = new JPanel();
  public JPanel descubraPainel = new JPanel();
  public JPanel contaPainel = new JPanel();
  
  /* BOTÕES */
  public JButton postarButton = new JButton("POSTAR");
  public JButton verPostsButton = new JButton("Posts no Feed");
  public JButton verPropriosPostsButton = new JButton("Seus posts");
  public JButton verUsersButton = new JButton("Novos Usuários");
  public JButton verFavoritosButton = new JButton("Posts favoritos");
  public JButton verPerfilButton = new JButton("Ver perfil");
  public JButton logoutButton = new JButton("Logout");

  public TelaUser(User userLogado, Sistema s) {
    int DEFAULT_HEIGHT = 700;
    int DEFAULT_WIDTH = 400;
    setTitle("Rede Social de Fotos");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(painelPrincipal);
    
    String caminhoImagem = System.getProperty("user.dir") + "/imagens/fotosPerfil/" + userLogado.getNomeImagem();
    ImagemCircular imagemPerfil = new ImagemCircular(caminhoImagem, 100, 100);

    painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
    labelGuest.setText("Bem vindo, " + userLogado.getNomeCompleto() + "!");
    
    descubraPainel.setBorder(BorderFactory.createTitledBorder("DESCUBRA"));
    contaPainel.setBorder(BorderFactory.createTitledBorder("CONTA"));

    descubraPainel.setLayout(new BoxLayout(descubraPainel, BoxLayout.Y_AXIS));
    contaPainel.setLayout(new BoxLayout(contaPainel, BoxLayout.Y_AXIS));
    paineisPainel.setLayout(new BoxLayout(paineisPainel, BoxLayout.X_AXIS));
    descubraPainel.setPreferredSize(new Dimension(175, 150));
    descubraPainel.setMaximumSize(new Dimension(175, 150));
    contaPainel.setPreferredSize(new Dimension(175, 150));
    contaPainel.setMaximumSize(new Dimension(175, 150));

    postarButton.setMaximumSize(new Dimension(150, 25));
    verPostsButton.setMaximumSize(new Dimension(150, 25));
    verPropriosPostsButton.setMaximumSize(new Dimension(150, 25));
    verUsersButton.setMaximumSize(new Dimension(150, 25));
    verFavoritosButton.setMaximumSize(new Dimension(150, 25));
    verPerfilButton.setMaximumSize(new Dimension(150, 25));
    logoutButton.setMaximumSize(new Dimension(150, 25));


    verPostsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    verPropriosPostsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    verUsersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    verFavoritosButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    verPerfilButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    postarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelGuest.setAlignmentX(Component.CENTER_ALIGNMENT);
    imagemPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);


    /* DESIGN DO PAINEL DESCUBRA */
    descubraPainel.add(Box.createVerticalGlue());
    descubraPainel.add(verPostsButton);
    descubraPainel.add(Box.createRigidArea(new Dimension(0, 10)));
    descubraPainel.add(verUsersButton);
    descubraPainel.add(Box.createRigidArea(new Dimension(0, 10)));
    descubraPainel.add(verFavoritosButton);
    descubraPainel.add(Box.createVerticalGlue());
    
    /* DESIGN DO PAINEL CONTA */
    contaPainel.add(Box.createVerticalGlue());
    contaPainel.add(verPerfilButton);
    contaPainel.add(Box.createRigidArea(new Dimension(0, 10)));
    contaPainel.add(verPropriosPostsButton);
    contaPainel.add(Box.createRigidArea(new Dimension(0, 10)));
    contaPainel.add(logoutButton);
    contaPainel.add(Box.createVerticalGlue());

    /* DESIGN DO PAINEL DOS PAINEIS */
    paineisPainel.add(Box.createHorizontalGlue());
    paineisPainel.add(descubraPainel);
    paineisPainel.add(Box.createRigidArea(new Dimension(10, 0)));
    paineisPainel.add(contaPainel);
    paineisPainel.add(Box.createHorizontalGlue());
    
    /* DESIGN DO PAINEL PRINCIPAL */
    painelPrincipal.add(Box.createVerticalGlue());  
    painelPrincipal.add(imagemPerfil);
    painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
    painelPrincipal.add(labelGuest);
    painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
    painelPrincipal.add(postarButton);
    painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
    painelPrincipal.add(paineisPainel);
    painelPrincipal.add(Box.createVerticalGlue());

    /* FUNCIONALIDADES DOS BOTÕES */

    postarButton.addActionListener(e -> {});    
    verPostsButton.addActionListener(e -> {});    
    verUsersButton.addActionListener(e -> {
      TelaDescobrirUsers telaDescobrirUsers = new TelaDescobrirUsers(s, s.getAllUsers(), userLogado);
      telaDescobrirUsers.setVisible(true);
      this.dispose();
    });    
    verPropriosPostsButton.addActionListener(e -> {});    
    verFavoritosButton.addActionListener(e -> {});    
    verPerfilButton.addActionListener(e -> {
      TelaPerfil telaPerfil = new TelaPerfil(userLogado, s);
      telaPerfil.setVisible(true);
      this.dispose();
    });  

    /* FUNÇÃO DE LOGOUT */  
    logoutButton.addActionListener(e -> {
      TelaGuest telaGuest = new TelaGuest(s);
      telaGuest.setVisible(true);
      this.dispose();
    });    
  }
}
