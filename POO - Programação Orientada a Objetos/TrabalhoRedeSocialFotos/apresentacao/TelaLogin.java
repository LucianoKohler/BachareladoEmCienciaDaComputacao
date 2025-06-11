package apresentacao;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dados.User;
import negocio.Sistema;

import java.awt.Component;
import java.awt.Dimension;

public class TelaLogin extends JFrame {
  User userLogado = null;

  /* INSTANCIANDO ELEMENTOS */
  public JPanel mainPanel = new JPanel();
  public JLabel labelUsername = new JLabel("Username: ");
  public JTextField textFieldUsername = new JTextField();
  public JLabel labelSenha = new JLabel("Senha: ");
  public JPasswordField textFieldSenha = new JPasswordField();
  public JButton buttonLogin = new JButton("Logar");
  public JButton buttonIrParaCadastro = new JButton("Não tem uma conta? Cadastre-se");
  
  public TelaLogin(Sistema s) {
    int DEFAULT_HEIGHT = 400;
    int DEFAULT_WIDTH = 400;
    setTitle("Tela Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(mainPanel);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    /* INDICANDO COMO CADA ELEMENTO DEVE SE COMPORTAR */
    labelUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
    textFieldUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
    textFieldUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
    textFieldSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonIrParaCadastro.setAlignmentX(Component.CENTER_ALIGNMENT);

    textFieldUsername.setMaximumSize(new Dimension(200, 25));
    textFieldSenha.setMaximumSize(new Dimension(200, 25));
    
    /* INSERINDO OS ELEMENTOS NA TELA */
    mainPanel.add(Box.createVerticalGlue());
    mainPanel.add(labelUsername);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(textFieldUsername);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(labelSenha);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(textFieldSenha);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(buttonLogin);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(buttonIrParaCadastro);
    mainPanel.add(Box.createVerticalGlue());

    /* FUNCIONALIDADES DOS BOTÕES */
    buttonLogin.addActionListener(e -> {
      String username = textFieldUsername.getText();
      String senha = new String(textFieldSenha.getPassword());

      userLogado = s.fazerLogin(username, senha);
        if(userLogado == null){
          JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
      }else{
        JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
        TelaUser telaUser = new TelaUser(userLogado, s);
        telaUser.setVisible(true);
        TelaLogin.this.setVisible(false);
      }
    });

    buttonIrParaCadastro.addActionListener(e -> {
      TelaCadastro telaCadastro = new TelaCadastro(s);
      telaCadastro.setVisible(true);
      this.dispose(); // fecha e remove a janela anterior
    });
    }
}
