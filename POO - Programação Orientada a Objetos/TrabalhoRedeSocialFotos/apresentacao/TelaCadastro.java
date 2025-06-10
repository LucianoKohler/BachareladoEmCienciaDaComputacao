package apresentacao;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dados.User;
import negocio.Sistema;

import java.awt.Component;
import java.awt.Dimension;

public class TelaCadastro extends JFrame {
  User userLogado = null;
  
  /* INSTANCIANDO ELEMENTOS */
  public JPanel mainPanel = new JPanel();
  public JLabel labelUsername = new JLabel("Username: ");
  public JTextField textFieldUsername = new JTextField();
  public JLabel labelSenha = new JLabel("Senha: ");
  public JPasswordField textFieldSenha = new JPasswordField();
  public JLabel labelNomeCompleto = new JLabel("Nome Completo: ");
  public JTextField textFieldNomeCompleto = new JTextField();
  public JLabel labelBiografia = new JLabel("Biografia: ");
  public JTextArea textAreaBiografia = new JTextArea();
  public JButton buttonCadastro = new JButton("Cadastrar");
  public JButton buttonIrParaLogin = new JButton("Já tem uma conta? Faça login");
  

  public TelaCadastro(Sistema s) {
    int DEFAULT_HEIGHT = 400;
    int DEFAULT_WIDTH = 400;
    setTitle("Tela Cadastro");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(mainPanel);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    /* INDICANDO COMO CADA ELEMENTO DEVE SE COMPORTAR */
    labelUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
    textFieldUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
    textFieldSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelNomeCompleto.setAlignmentX(Component.CENTER_ALIGNMENT);
    textFieldNomeCompleto.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelBiografia.setAlignmentX(Component.CENTER_ALIGNMENT);
    textAreaBiografia.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonCadastro.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonIrParaLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

    textFieldUsername.setMaximumSize(new Dimension(200, 25));
    textFieldSenha.setMaximumSize(new Dimension(200, 25));
    textFieldNomeCompleto.setMaximumSize(new Dimension(200, 25));
    textAreaBiografia.setRows(2);
    textAreaBiografia.setLineWrap(true); // quebra linha
    textAreaBiografia.setWrapStyleWord(true);
    textAreaBiografia.setMaximumSize(new Dimension(200, 50));
    textAreaBiografia.setPreferredSize(new Dimension(200, 50));

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
    mainPanel.add(labelNomeCompleto);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(textFieldNomeCompleto);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(labelBiografia);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(textAreaBiografia);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(buttonCadastro);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(buttonIrParaLogin);
    mainPanel.add(Box.createVerticalGlue());

    /* FUNCIONALIDADES DOS BOTÕES */
    buttonCadastro.addActionListener(e -> {
      String username = textFieldUsername.getText();
      String senha = new String(textFieldSenha.getPassword());
      String nomeCompleto = new String(textFieldNomeCompleto.getText());
      String biografia = new String(textAreaBiografia.getText());

      User novoUser = new User(username, senha, nomeCompleto, biografia);
      boolean sucesso = s.cadastrarUser(novoUser);
        if(!sucesso){
          javax.swing.JOptionPane.showMessageDialog(this, "Erro: nome de usuário já cadastrado.");
      }else{
        javax.swing.JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
        TelaUser telaUser = new TelaUser(novoUser, s);
        telaUser.setVisible(true);
        TelaCadastro.this.dispose(); // fecha e remove a janela anterior
      }
    });

    buttonIrParaLogin.addActionListener(e -> {
      TelaLogin telaLogin = new TelaLogin(s);
      telaLogin.setVisible(true);
      TelaCadastro.this.setVisible(false);
    });
    }
}
