package apresentacao;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import apresentacao.componentes.SeletorImagem;
import dados.Post;
import dados.User;
import negocio.Sistema;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class TelaPostar extends JFrame {
  BufferedImage imagemPost = null;
  
  /* INSTANCIANDO ELEMENTOS */
  public JPanel mainPanel = new JPanel();
  public JTextField textFieldLegenda = new JTextField();

  /* LABELS */
  public JLabel labelImagemEscolhida = new JLabel("Imagem escolhida: ");
  public JLabel labelLegenda = new JLabel("Legenda do post: ");

  /* BUTTONS */
  public JButton buttonEscolherImagem = new JButton("Escolher");
  public JButton buttonPostar = new JButton("POSTAR");
  public JButton buttonVoltar = new JButton("Voltar");

  public TelaPostar(Sistema s, User userLogado) {
    int DEFAULT_HEIGHT = 500;
    int DEFAULT_WIDTH = 400;
    setTitle("Postar");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(mainPanel);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    /* INDICANDO COMO CADA ELEMENTO DEVE SE COMPORTAR */
    labelImagemEscolhida.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonEscolherImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelLegenda.setAlignmentX(Component.CENTER_ALIGNMENT);
    textFieldLegenda.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonPostar.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

    textFieldLegenda.setMaximumSize(new Dimension(200, 25));

    /* INSERINDO OS ELEMENTOS NA TELA */
    mainPanel.add(Box.createVerticalGlue());
    mainPanel.add(labelImagemEscolhida);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(buttonEscolherImagem);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(labelLegenda);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(textFieldLegenda);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(buttonPostar);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(buttonVoltar);
    mainPanel.add(Box.createVerticalGlue());

    /* FUNCIONALIDADES DOS BOTÕES */
    buttonEscolherImagem.addActionListener(e -> {
      imagemPost = SeletorImagem.solicitarImagem(this);
      if(imagemPost != null){
        buttonEscolherImagem.setText("Selecionada! Selecionar outra imagem?");
      }
    });

    buttonPostar.addActionListener(e -> {
      String legenda = textFieldLegenda.getText();

      if(legenda.isEmpty()){
        JOptionPane.showMessageDialog(this, "Erro: Sua imagem precisa de uma descrição");
        return;
      }
      if(imagemPost == null){
        JOptionPane.showMessageDialog(this, "Erro: O post não tem uma imagem");
        return;        
      }
      Post post = new Post(userLogado, legenda, Sistema.ImageParaBytes(imagemPost));
      s.criarPost(userLogado, post);
        JOptionPane.showMessageDialog(this, "Postado com sucesso!");
        TelaUser telaUser = new TelaUser(userLogado, s);
        telaUser.setVisible(true);
        this.dispose();
    });

    buttonVoltar.addActionListener(e -> {
      TelaUser telaUser = new TelaUser(userLogado, s);
      telaUser.setVisible(true);
      this.dispose();
    });

    }
}
