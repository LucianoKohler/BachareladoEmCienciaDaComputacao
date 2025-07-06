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

public class TelaVerSeguidores extends JFrame {
  
  /* INSTANCIANDO ELEMENTOS */
  JScrollPane painelPrincipal = new JScrollPane();
  JPanel painelConteudo = new JPanel();
  JLabel tituloLabel = new JLabel("Seus seguidores");
  JButton voltarButton = new JButton("Voltar");

  public TelaVerSeguidores(Sistema s, User userLogado) {
    int DEFAULT_HEIGHT = 700;
    int DEFAULT_WIDTH = 400;
    setTitle("Seguidores");
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

    ArrayList<User> seguidores = s.verSeguidoresDeUmUser(userLogado);
    for (User user : seguidores) {
      
      JPanel userPainel = new JPanel();
      userPainel.setLayout(new BoxLayout(userPainel, BoxLayout.X_AXIS));

      JLabel nomeLabel = new JLabel(user.getNomeCompleto());
      JButton removerSeguidorButton = new JButton("Remover seguidor");
      nomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
      removerSeguidorButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

      userPainel.add(Box.createRigidArea(new Dimension(20, 0)));
      userPainel.add(nomeLabel);
      userPainel.add(Box.createHorizontalGlue());
      userPainel.add(removerSeguidorButton);
      userPainel.add(Box.createRigidArea(new Dimension(20, 0)));
      
      painelConteudo.add(userPainel);
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

      removerSeguidorButton.addActionListener(e -> {
        JOptionPane.showMessageDialog(this, "Você removeu " + user.getNomeCompleto() + " da sua lista de seguidores");
        s.unfollowUser(user, userLogado);
        TelaVerSeguidores telaVerSeguidores = new TelaVerSeguidores(s, userLogado);
        telaVerSeguidores.setVisible(true);
        this.dispose();
      });
    }

    if(seguidores.size() == 0){
      JLabel semUsuariosLabel = new JLabel("Parece que ninguém te segue...");
      semUsuariosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      painelConteudo.add(semUsuariosLabel);
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    painelConteudo.add(voltarButton);
    painelPrincipal.setViewportView(painelConteudo);
    
    /* FUNCIONALIDADES DOS BOTÕES */
    voltarButton.addActionListener(e -> {
      TelaPerfil telaPerfil = new TelaPerfil(userLogado, s);
      telaPerfil.setVisible(true);
      this.dispose();
    });
  }

}
