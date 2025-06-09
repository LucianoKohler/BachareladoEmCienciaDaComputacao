package apresentacao;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dados.User;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaGuest extends JFrame {
  User userLogado = null;
  
  /* INSTANCIANDO ELEMENTOS */
  public JPanel painelGuest = new JPanel();
  public JLabel labelGuest = new JLabel("Você não está logado!");
  public JButton loginButton = new JButton("Fazer login");
  public JButton cadastrarButton = new JButton("Cadastrar-se");

  public TelaGuest() {
    int DEFAULT_HEIGHT = 700;
    int DEFAULT_WIDTH = 400;
    setTitle("Rede Social de Fotos");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(painelGuest);
    painelGuest.setLayout(new BoxLayout(painelGuest, BoxLayout.Y_AXIS));

    labelGuest.setAlignmentX(Component.CENTER_ALIGNMENT);
    loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    painelGuest.add(Box.createVerticalGlue());
    painelGuest.add(labelGuest);
    painelGuest.add(Box.createRigidArea(new Dimension(0, 10))); // Padding
    painelGuest.add(loginButton);
    painelGuest.add(Box.createRigidArea(new Dimension(0, 10))); // Padding
    painelGuest.add(cadastrarButton);
    painelGuest.add(Box.createVerticalGlue());
    
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
      }
    });

    cadastrarButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.setVisible(true);
      }
    });
  }

}
