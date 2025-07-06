package apresentacao;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import negocio.Sistema;

import java.awt.Component;
import java.awt.Dimension;

public class TelaGuest extends JFrame {
  
  /* INSTANCIANDO ELEMENTOS */
  public JPanel painelGuest = new JPanel();
  public JLabel labelGuest = new JLabel("Você não está logado!");
  public JButton loginButton = new JButton("Fazer login");
  public JButton cadastrarButton = new JButton("Cadastrar-se");

  public TelaGuest(Sistema s) {
    int DEFAULT_HEIGHT = 700;
    int DEFAULT_WIDTH = 450;
    setTitle("Rede Social de Fotos");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(painelGuest);
    painelGuest.setLayout(new BoxLayout(painelGuest, BoxLayout.Y_AXIS));

    /* INDICANDO COMO CADA ELEMENTO DEVE SE COMPORTAR */
    labelGuest.setAlignmentX(Component.CENTER_ALIGNMENT);
    loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    /* INSERINDO OS ELEMENTOS NA TELA */
    painelGuest.add(Box.createVerticalGlue());
    painelGuest.add(labelGuest);
    painelGuest.add(Box.createRigidArea(new Dimension(0, 10))); // Padding
    painelGuest.add(loginButton);
    painelGuest.add(Box.createRigidArea(new Dimension(0, 10))); // Padding
    painelGuest.add(cadastrarButton);
    painelGuest.add(Box.createVerticalGlue());
    
    /* FUNCIONALIDADES DOS BOTÕES */
    loginButton.addActionListener(e -> {
        TelaLogin telaLogin = new TelaLogin(s);
        telaLogin.setVisible(true);
        TelaGuest.this.setVisible(false);
    });
    
    cadastrarButton.addActionListener(e -> {
        TelaCadastro telaCadastro = new TelaCadastro(s);
        telaCadastro.setVisible(true);
        this.dispose(); // fecha e remove a janela anterior
    });
  }

}
