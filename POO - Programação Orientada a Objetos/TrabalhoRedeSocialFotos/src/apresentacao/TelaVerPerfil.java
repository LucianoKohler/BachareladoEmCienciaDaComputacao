package apresentacao;

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
import java.awt.Font;

public class TelaVerPerfil extends JFrame {
  
  /* INSTANCIANDO ELEMENTOS */
  public JPanel painelPrincipal = new JPanel();
  public JLabel labelUsername = new JLabel("USUÁRIO"); // Setado no construtor
  public JLabel labelBiografia = new JLabel("BIOGRAFIA"); // Setado no construtor
  public JPanel painelAcoes = new JPanel();

  /* LABELS */
  public JPanel estatisticasPanel = new JPanel();
  public JPanel qtdPostsPanel = new JPanel();
  public JLabel qtdPostsLabel1 = new JLabel("0");
  public JLabel qtdPostsLabel2 = new JLabel("POSTS");
  public JPanel seguidoresPanel = new JPanel();
  public JLabel seguidoresLabel1 = new JLabel("0");
  public JLabel seguidoresLabel2 = new JLabel("SEGUIDORES");
  public JPanel seguindoPanel = new JPanel();
  public JLabel seguindoLabel1 = new JLabel("0");
  public JLabel seguindoLabel2 = new JLabel("SEGUINDO");
  public JLabel ouLabel = new JLabel("OU");

  /* BOTÕES */
  public JButton voltarButton = new JButton("Voltar");

  public TelaVerPerfil(User user, Sistema s, User userLogado) {
    int DEFAULT_HEIGHT = 450;
    int DEFAULT_WIDTH = 450;
    setTitle("Perfil");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(600, 250, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(painelPrincipal);

    ImagemCircular imagemPerfil = new ImagemCircular(user.getFotoPerfil(), 100, 100);
    
    /* COMO CADA PANEL DEVE ARMAZENAR SEUS COMPONENTES */
    painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
    estatisticasPanel.setLayout(new BoxLayout(estatisticasPanel, BoxLayout.X_AXIS));
    qtdPostsPanel.setLayout(new BoxLayout(qtdPostsPanel, BoxLayout.Y_AXIS));
    seguidoresPanel.setLayout(new BoxLayout(seguidoresPanel, BoxLayout.Y_AXIS));
    seguindoPanel.setLayout(new BoxLayout(seguindoPanel, BoxLayout.Y_AXIS));

    /* CARACTERÍSTICAS DOS ELEMENTOS */
    labelUsername.setText(user.getNomeCompleto());
    labelBiografia.setText(user.getBiografia());
    labelUsername.setFont(new Font("Arial", Font.BOLD, 24));
    qtdPostsLabel1.setFont(new Font("Arial", Font.BOLD, 24));
    seguidoresLabel1.setFont(new Font("Arial", Font.BOLD, 24));
    seguindoLabel1.setFont(new Font("Arial", Font.BOLD, 24));
    ouLabel.setFont(new Font("arial", Font.BOLD, 16));
    
    voltarButton.setMaximumSize(new Dimension(250, 25));
    
    qtdPostsLabel1.setText(String.valueOf(s.verPostsDeUmUser(user).size()));
    seguidoresLabel1.setText(String.valueOf(s.verSeguidoresDeUmUser(user).size()));
    seguindoLabel1.setText(String.valueOf(s.verSeguindoDeUmUser(user).size()));

    /* INDICANDO COMO CADA ELEMENTO DEVE SE COMPORTAR */
    painelPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
    imagemPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
    painelAcoes.setAlignmentX(Component.CENTER_ALIGNMENT);
    voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    ouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelBiografia.setAlignmentX(Component.CENTER_ALIGNMENT);

    qtdPostsLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
    qtdPostsLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
    seguidoresLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
    seguidoresLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
    seguindoLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
    seguindoLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

    /* FORMATANDO O PANEL ESTATÍSTICAS */
    qtdPostsPanel.add(Box.createVerticalGlue());
    qtdPostsPanel.add(qtdPostsLabel1);
    qtdPostsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    qtdPostsPanel.add(qtdPostsLabel2);
    qtdPostsPanel.add(Box.createVerticalGlue());

    seguidoresPanel.add(Box.createVerticalGlue());
    seguidoresPanel.add(seguidoresLabel1);
    seguidoresPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    seguidoresPanel.add(seguidoresLabel2);
    seguidoresPanel.add(Box.createVerticalGlue());

    seguindoPanel.add(Box.createVerticalGlue());
    seguindoPanel.add(seguindoLabel1);
    seguindoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    seguindoPanel.add(seguindoLabel2);
    seguindoPanel.add(Box.createVerticalGlue());

    estatisticasPanel.add(Box.createHorizontalGlue());
    estatisticasPanel.add(seguidoresPanel);
    estatisticasPanel.add(Box.createRigidArea(new Dimension(50, 0)));
    estatisticasPanel.add(qtdPostsPanel);
    estatisticasPanel.add(Box.createRigidArea(new Dimension(50, 0)));
    estatisticasPanel.add(seguindoPanel);
    estatisticasPanel.add(Box.createHorizontalGlue());

    painelAcoes.setLayout(new BoxLayout(painelAcoes, BoxLayout.Y_AXIS));

    painelAcoes.add(Box.createVerticalGlue());
    painelAcoes.add(voltarButton);
    painelAcoes.add(Box.createVerticalGlue());

    /* DESIGN DO PAINEL PRINCIPAL */
    painelPrincipal.add(Box.createVerticalGlue());
    painelPrincipal.add(imagemPerfil);
    painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
    painelPrincipal.add(labelUsername);
    painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
    painelPrincipal.add(labelBiografia);
    painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
    painelPrincipal.add(estatisticasPanel);
    painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
    painelPrincipal.add(painelAcoes);
    painelPrincipal.add(Box.createVerticalGlue());

    /* FUNCIONALIDADES DOS BOTÕES */
    voltarButton.addActionListener(e -> this.dispose());
  }
}
