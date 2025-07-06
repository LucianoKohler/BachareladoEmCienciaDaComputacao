package apresentacao;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dados.User;
import negocio.Sistema;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

public class TelaVerSeguindo extends JFrame {
  
  /* INSTANCIANDO ELEMENTOS */
  JScrollPane painelPrincipal = new JScrollPane();
  JPanel painelConteudo = new JPanel();
  JLabel tituloLabel = new JLabel("Usuários que você segue");
  JButton voltarButton = new JButton("Voltar");

  public TelaVerSeguindo(Sistema s, User userLogado) {
    int DEFAULT_HEIGHT = 700;
    int DEFAULT_WIDTH = 450;
    setTitle("Seguindo");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(painelPrincipal);
    painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
    voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));

    painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));
    painelConteudo.add(tituloLabel);
    painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));

    ArrayList<User> seguindos = s.verSeguindoDeUmUser(userLogado);
    for (User user : seguindos) {
      
      JPanel userPainel = new JPanel();
      userPainel.setLayout(new BoxLayout(userPainel, BoxLayout.X_AXIS));

      JLabel nomeLabel = new JLabel(user.getNomeCompleto());
      JButton deixarDeSeguirButton = new JButton("Deixar de seguir");
      JButton verPerfilButton = new JButton("Ver Perfil");

      nomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
      deixarDeSeguirButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
      verPerfilButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

      userPainel.add(Box.createRigidArea(new Dimension(20, 0)));
      userPainel.add(nomeLabel);
      userPainel.add(Box.createHorizontalGlue());
      userPainel.add(verPerfilButton);
      userPainel.add(Box.createRigidArea(new Dimension(10, 0)));
      userPainel.add(deixarDeSeguirButton);
      userPainel.add(Box.createRigidArea(new Dimension(20, 0)));
      
      painelConteudo.add(userPainel);
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

      deixarDeSeguirButton.addActionListener(e -> {
        JOptionPane.showMessageDialog(this, "Você deixou de seguir: " + user.getNomeCompleto());
        s.unfollowUser(userLogado, user);
        TelaVerSeguindo telaVerSeguindo = new TelaVerSeguindo(s, userLogado);
        telaVerSeguindo.setVisible(true);
        this.dispose();
      });

      verPerfilButton.addActionListener(e -> {
        TelaVerPerfil telaVerPerfil = new TelaVerPerfil(user, s, userLogado);
        telaVerPerfil.setVisible(true);
      });
    }

    if(seguindos.size() == 0){
      JLabel semUsuariosLabel = new JLabel("Parece que você não segue ninguém...");
      semUsuariosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      painelConteudo.add(semUsuariosLabel);
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    painelConteudo.add(voltarButton);
    painelPrincipal.setViewportView(painelConteudo);
    /* INDICANDO COMO CADA ELEMENTO DEVE SE COMPORTAR */

    /* INSERINDO OS ELEMENTOS NA TELA */
    
    /* FUNCIONALIDADES DOS BOTÕES */
    voltarButton.addActionListener(e -> {
      TelaPerfil telaPerfil = new TelaPerfil(userLogado, s);
      telaPerfil.setVisible(true);
      this.dispose();
    });
  }

}
