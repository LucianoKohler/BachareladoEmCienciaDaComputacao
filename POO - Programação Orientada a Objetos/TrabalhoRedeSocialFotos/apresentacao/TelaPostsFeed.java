package apresentacao;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import apresentacao.componentes.ImagemQuadrada;
import dados.Post;
import dados.User;
import negocio.Sistema;

public class TelaPostsFeed extends JFrame { 

  JScrollPane painelPrincipal = new JScrollPane();
  public JLabel labelTitulo = new JLabel("Posts no seu feed");
  public JLabel labelNenhumPost = new JLabel("Não há posts disponíveis no momento...");
  public JPanel painelConteudo = new JPanel();
  public JButton buttonVoltar = new JButton("Voltar");

  public TelaPostsFeed(User userLogado, Sistema s){
    int DEFAULT_HEIGHT = 700;
    int DEFAULT_WIDTH = 400;
    setTitle("Feed");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(painelPrincipal);
    painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));

    labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
    labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelNenhumPost.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

    painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));
    painelConteudo.add(labelTitulo);
    
    if(s.verPostsDisponiveis(userLogado).size() == 0){
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));
      painelConteudo.add(labelNenhumPost);
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));
    }else{
      for(Post post : s.verPostsDisponiveis(userLogado)){
        JPanel painelPost = new JPanel();

        ImagemQuadrada imagemPost = new ImagemQuadrada(post.getImagem(), 200, 300);
        JLabel legendaPost = new JLabel(post.getLegenda());
        JButton buttonFavoritar = new JButton("Favoritar");
        
        painelPost.setLayout(new BoxLayout(painelPost, BoxLayout.Y_AXIS));

        imagemPost.setAlignmentX(Component.CENTER_ALIGNMENT);
        legendaPost.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonFavoritar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        buttonFavoritar.setMaximumSize(new Dimension(130, 25));
                
        /* PAINEL POST */
        painelPost.add(imagemPost);
        painelPost.add(Box.createRigidArea(new Dimension(0, 10)));
        painelPost.add(legendaPost);
        if(userLogado.getFavoritos().contains(post)){
          buttonFavoritar.setText("Desfavoritar");

          JLabel labelFavorito = new JLabel("Você favoritou este post");
          labelFavorito.setAlignmentX(Component.CENTER_ALIGNMENT);

          painelPost.add(Box.createRigidArea(new Dimension(0, 10)));
          painelPost.add(labelFavorito);
        }
        painelPost.add(Box.createRigidArea(new Dimension(0, 10)));
        painelPost.add(buttonFavoritar);
        
        /* PAINEL CONTEÚDO */
        
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));
        painelConteudo.add(painelPost);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        /* FUNCIONALIDADES DOS BOTÕES */
        buttonFavoritar.addActionListener(e -> {
          if(userLogado.getFavoritos().contains(post)){
            s.desFavoritarPost(userLogado, post);
            JOptionPane.showMessageDialog(this, "Você desfavoritou o post");
          }else{
            s.favoritarPost(userLogado, post);
            JOptionPane.showMessageDialog(this, "Você favoritou o post");
          }
            TelaPostsFeed TelaPostsFeed = new TelaPostsFeed(userLogado, s);
            TelaPostsFeed.setVisible(true);
            this.dispose();
        });
      }
    }

    painelConteudo.add(buttonVoltar);
    painelConteudo.add(Box.createVerticalGlue());
    painelPrincipal.setViewportView(painelConteudo);

    buttonVoltar.addActionListener(e -> {
      TelaUser telaUser = new TelaUser(userLogado, s);
      telaUser.setVisible(true);
      this.dispose();
    });
  }
}
