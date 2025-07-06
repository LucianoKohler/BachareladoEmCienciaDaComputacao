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

public class TelaDescobrirUsers extends JFrame {
  
  /* INSTANCIANDO ELEMENTOS */
  JScrollPane painelPrincipal = new JScrollPane();
  JPanel painelConteudo = new JPanel();
  JLabel tituloLabel = new JLabel("Usuários que você pode seguir");
  JButton voltarButton = new JButton("Voltar");

  public TelaDescobrirUsers(Sistema s, ArrayList<User> users, User userLogado) {
    int DEFAULT_HEIGHT = 700;
    int DEFAULT_WIDTH = 400;
    setTitle("Descubra novos usuários");
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

    int qtdUsersListados = 0;
    ArrayList<User> seguindos = s.verSeguindoDeUmUser(userLogado);
    for (User user : users) {
      if(seguindos.contains(user)){ continue; } // Se o usuário já está seguindo o usuário, pula para o próximo
      if(user.equals(userLogado)) { continue; } // Mesma coisa se o usário for ele mesmo
      
      qtdUsersListados++;
      JPanel userPainel = new JPanel();
      userPainel.setLayout(new BoxLayout(userPainel, BoxLayout.X_AXIS));

      JLabel nomeLabel = new JLabel(user.getNomeCompleto());
      JButton seguirButton = new JButton("Seguir");
      
      nomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
      seguirButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

      userPainel.add(Box.createRigidArea(new Dimension(20, 0)));
      userPainel.add(nomeLabel);
      userPainel.add(Box.createHorizontalGlue());
      userPainel.add(seguirButton);
      userPainel.add(Box.createRigidArea(new Dimension(20, 0)));
      
      painelConteudo.add(userPainel);
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

      seguirButton.addActionListener(e -> {
        JOptionPane.showMessageDialog(this, "Agora você segue: " + user.getNomeCompleto());
        s.followUser(userLogado, user);
        TelaDescobrirUsers telaDescobrirUsers = new TelaDescobrirUsers(s, users, userLogado);
        telaDescobrirUsers.setVisible(true);
        this.dispose();
      });
    }

    if(qtdUsersListados == 0){
      JLabel semUsuariosLabel = new JLabel("Parece que não há usuários disponíveis para seguir...");
      semUsuariosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      painelConteudo.add(semUsuariosLabel);
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    painelConteudo.add(voltarButton);
    
    painelPrincipal.setViewportView(painelConteudo);

    voltarButton.addActionListener(e -> {
      TelaUser telaUser = new TelaUser(userLogado, s);
      telaUser.setVisible(true);
      this.dispose();
    });
  }

}
