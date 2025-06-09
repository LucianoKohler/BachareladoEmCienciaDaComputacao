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

public class TelaLogin extends JFrame {
  User userLogado = null;
  
  /* INSTANCIANDO ELEMENTOS */
  public JPanel painelGuest = new JPanel();
  public JLabel labelGuest = new JLabel("Tela de Login");

  public TelaLogin() {
    int DEFAULT_HEIGHT = 400;
    int DEFAULT_WIDTH = 400;
    setTitle("Tela Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(painelGuest);
    painelGuest.setLayout(new BoxLayout(painelGuest, BoxLayout.Y_AXIS));

    labelGuest.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    painelGuest.add(Box.createVerticalGlue());
    painelGuest.add(labelGuest);
    painelGuest.add(Box.createVerticalGlue());
    }
}
