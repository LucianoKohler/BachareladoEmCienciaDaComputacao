package apresentacao;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dados.User;
import negocio.Sistema;
import apresentacao.componentes.ImagemCircular;
import apresentacao.componentes.SeletorImagem;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

public class TelaPerfil extends JFrame {
  
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
  public JButton mudarCredenciaisButton = new JButton("Mudar credenciais de perfil");
  public JButton verSeguidoresButton = new JButton("Ver seus seguidores");
  public JButton verSeguindoButton = new JButton("Ver quem você segue");
  public JButton apagarContaButton = new JButton("Apagar conta");
  public JButton voltarButton = new JButton("Voltar");
  JButton adicionarImagemButton = new JButton("Foto de perfil");

  public TelaPerfil(User userLogado, Sistema s) {
    int DEFAULT_HEIGHT = 700;
    int DEFAULT_WIDTH = 400;
    setTitle("Perfil");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(painelPrincipal);

    ImagemCircular imagemPerfil = new ImagemCircular(userLogado.getFotoPerfil(), 100, 100);
    
    /* COMO CADA PANEL DEVE ARMAZENAR SEUS COMPONENTES */
    painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
    estatisticasPanel.setLayout(new BoxLayout(estatisticasPanel, BoxLayout.X_AXIS));
    qtdPostsPanel.setLayout(new BoxLayout(qtdPostsPanel, BoxLayout.Y_AXIS));
    seguidoresPanel.setLayout(new BoxLayout(seguidoresPanel, BoxLayout.Y_AXIS));
    seguindoPanel.setLayout(new BoxLayout(seguindoPanel, BoxLayout.Y_AXIS));

    /* CARACTERÍSTICAS DOS ELEMENTOS */
    labelUsername.setText(userLogado.getNomeCompleto());
    labelBiografia.setText(userLogado.getBiografia());
    labelUsername.setFont(new Font("Arial", Font.BOLD, 24));
    qtdPostsLabel1.setFont(new Font("Arial", Font.BOLD, 24));
    seguidoresLabel1.setFont(new Font("Arial", Font.BOLD, 24));
    seguindoLabel1.setFont(new Font("Arial", Font.BOLD, 24));
    ouLabel.setFont(new Font("arial", Font.BOLD, 16));
    
    mudarCredenciaisButton.setMaximumSize(new Dimension(250, 25));
    verSeguidoresButton.setMaximumSize(new Dimension(250, 25));
    verSeguindoButton.setMaximumSize(new Dimension(250, 25));
    apagarContaButton.setMaximumSize(new Dimension(250, 25));
    voltarButton.setMaximumSize(new Dimension(250, 25));
    
    qtdPostsLabel1.setText(String.valueOf(s.verPostsDeUmUser(userLogado).size()));
    seguidoresLabel1.setText(String.valueOf(s.verSeguidoresDeUmUser(userLogado).size()));
    seguindoLabel1.setText(String.valueOf(s.verSeguindoDeUmUser(userLogado).size()));

    /* INDICANDO COMO CADA ELEMENTO DEVE SE COMPORTAR */
    painelPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
    imagemPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
    painelAcoes.setAlignmentX(Component.CENTER_ALIGNMENT);
    mudarCredenciaisButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    verSeguidoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    verSeguindoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    apagarContaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    ouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    adicionarImagemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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

    /* DESIGN DO PAINEL DAS ACOES */
    painelAcoes.add(Box.createVerticalGlue());
    painelAcoes.add(mudarCredenciaisButton);
    painelAcoes.add(Box.createRigidArea(new Dimension(0, 10)));
    painelAcoes.add(verSeguidoresButton);
    painelAcoes.add(Box.createRigidArea(new Dimension(0, 10)));
    painelAcoes.add(verSeguindoButton);
    painelAcoes.add(Box.createRigidArea(new Dimension(0, 10)));
    painelAcoes.add(apagarContaButton);
    painelAcoes.add(Box.createRigidArea(new Dimension(0, 10)));
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
    mudarCredenciaisButton.addActionListener(e -> {
      JPanel formPainel = new JPanel(new GridLayout(0, 1));
      JButton usernameButton = new JButton("Username");
      JButton senhaButton = new JButton("Senha");
      JButton nomeCompletoButton = new JButton("Nome Completo");
      JButton biografiaButton = new JButton("Biografia");
      
      formPainel.add(new JLabel("Escolha a credencial para ser mudada: "));
      formPainel.add(usernameButton);
      formPainel.add(senhaButton);
      formPainel.add(nomeCompletoButton);
      formPainel.add(biografiaButton);
      formPainel.add(adicionarImagemButton);

     usernameButton.addActionListener(f -> {
      String novoUsername = JOptionPane.showInputDialog(this, "Digite o novo username:");
      if (novoUsername != null && !novoUsername.trim().isEmpty()) {
        if (s.mudarCredenciaisPerfil(userLogado, novoUsername.trim(), 1)) {
          JOptionPane.showMessageDialog(this, "Username alterado com sucesso!");
          TelaPerfil telaPerfil = new TelaPerfil(s.buscarPorId(userLogado.getId()), s);
          telaPerfil.setVisible(true);
          this.dispose();
        } else {
          JOptionPane.showMessageDialog(this, "Erro: Username já cadastrado ou vazio");
        }
      }
     });

      senhaButton.addActionListener(f -> {
      String novaSenha = JOptionPane.showInputDialog(this, "Digite a nova senha:");
      if (novaSenha != null && !novaSenha.trim().isEmpty()) {
        s.mudarCredenciaisPerfil(userLogado, novaSenha, 2);
        JOptionPane.showMessageDialog(null, "Informação alterada com sucesso!");
        TelaPerfil telaPerfil = new TelaPerfil(s.buscarPorId(userLogado.getId()), s);
        telaPerfil.setVisible(true);
        this.dispose();
      }
     });

      nomeCompletoButton.addActionListener(f -> {
      String novoNomeCompleto = JOptionPane.showInputDialog(this, "Digite o novo nome completo:");
      if (novoNomeCompleto != null && !novoNomeCompleto.trim().isEmpty()) {
        s.mudarCredenciaisPerfil(userLogado, novoNomeCompleto, 3);
        JOptionPane.showMessageDialog(null, "Informação alterada com sucesso!");
        TelaPerfil telaPerfil = new TelaPerfil(s.buscarPorId(userLogado.getId()), s);
        telaPerfil.setVisible(true);
        this.dispose();
      }
     });

      biografiaButton.addActionListener(f -> {
      String novaBiografia = JOptionPane.showInputDialog(this, "Digite a nova biografia:");
      if (novaBiografia != null && !novaBiografia.trim().isEmpty()) {
        s.mudarCredenciaisPerfil(userLogado, novaBiografia, 4);
        JOptionPane.showMessageDialog(null, "Informação alterada com sucesso!");
        TelaPerfil telaPerfil = new TelaPerfil(s.buscarPorId(userLogado.getId()), s);
        telaPerfil.setVisible(true);
        this.dispose();
      }
     });
     
      // Cria um JOptionPane customizado para exibir o painel de botões
      JOptionPane optionPane = new JOptionPane(formPainel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
      javax.swing.JDialog dialog = optionPane.createDialog(this, "Mudar credenciais");

      // Fecha o dialog ao clicar em qualquer botão do painel
      usernameButton.addActionListener(f -> dialog.dispose());
      senhaButton.addActionListener(f -> dialog.dispose());
      nomeCompletoButton.addActionListener(f -> dialog.dispose());
      biografiaButton.addActionListener(f -> dialog.dispose());
      adicionarImagemButton.addActionListener(f -> dialog.dispose());

      dialog.setVisible(true);
    });

    verSeguidoresButton.addActionListener(e -> {
      TelaVerSeguidores telaVerSeguidores = new TelaVerSeguidores(s, userLogado);
      telaVerSeguidores.setVisible(true);
      this.dispose();      
    });

    verSeguindoButton.addActionListener(e -> {
      TelaVerSeguindo telaVerSeguindo = new TelaVerSeguindo(s, userLogado);
      telaVerSeguindo.setVisible(true);
      this.dispose();
    });

    apagarContaButton.addActionListener(e -> {
      int resposta = JOptionPane.showConfirmDialog(
        null, 
        "Essa ação é irreversível, tem certeza?", 
        "Confirmação", JOptionPane.YES_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
          s.deletarUser(userLogado);
          TelaGuest telaGuest = new TelaGuest(s);
          telaGuest.setVisible(true);
          this.dispose();
        }
    });

    voltarButton.addActionListener(e -> {
      TelaUser telaUser = new TelaUser(userLogado, s);
      telaUser.setVisible(true);
      this.dispose();
    });

    adicionarImagemButton.addActionListener(e -> {
      BufferedImage imagemEscolhida = SeletorImagem.solicitarImagem(this);
      if(imagemEscolhida == null) {
        JOptionPane.showMessageDialog(this, "Nenhuma imagem escolhida");
        return;
      }
      s.mudarFotoPerfil(userLogado, Sistema.ImageParaBytes(imagemEscolhida));
      JOptionPane.showMessageDialog(this, "Foto de perfil alterada com sucesso!");
      TelaPerfil telaPerfil = new TelaPerfil(s.buscarPorUsername(userLogado.getUsername()), s);
      telaPerfil.setVisible(true);
      this.dispose();
    });
  }
}
