package apresentacao;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

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

public class TelaVerPropriosPosts extends JFrame { 

  JScrollPane painelPrincipal = new JScrollPane();
  public JLabel labelTitulo = new JLabel("Seus Posts");
  public JLabel labelNenhumPost = new JLabel("Não há posts disponíveis no momento...");
  public JPanel painelConteudo = new JPanel();
  public JButton buttonVoltar = new JButton("Voltar");


  public TelaVerPropriosPosts(User userLogado, Sistema s){
    painelPrincipal.getVerticalScrollBar().setUnitIncrement(16); // 16 pixels per scroll "tick"
    int DEFAULT_HEIGHT = 700;
    int DEFAULT_WIDTH = 400;
    setTitle("Seus posts");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    setContentPane(painelPrincipal);
    painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));

    labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
    labelNenhumPost.setAlignmentX(Component.CENTER_ALIGNMENT);
    buttonVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

    painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));
    painelConteudo.add(labelTitulo);
    
    if(s.verPostsDeUmUser(userLogado).size() == 0){
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));
      painelConteudo.add(labelNenhumPost);
      painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));
    }else{
      for(Post post : s.verPostsDeUmUser(userLogado)){
        JPanel painelPost = new JPanel();
        JPanel painelBotoes = new JPanel();

        ImagemQuadrada imagemPost = new ImagemQuadrada(post.getImagem(), 200, 300);
        JLabel legendaPost = new JLabel(post.getLegenda());
        JButton buttonFavoritar = new JButton("Favoritar");
        JButton buttonDeletar = new JButton("Deletar"); 
        
        painelPost.setLayout(new BoxLayout(painelPost, BoxLayout.Y_AXIS));
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));

        imagemPost.setAlignmentX(Component.CENTER_ALIGNMENT);
        legendaPost.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonFavoritar.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonDeletar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        buttonFavoritar.setMaximumSize(new Dimension(130, 25));
        buttonDeletar.setMaximumSize(new Dimension(130, 25));
                
        /* PAINEL BOTÕES */
        painelBotoes.add(buttonFavoritar);
        painelBotoes.add(Box.createRigidArea(new Dimension(20, 0)));
        painelBotoes.add(buttonDeletar);

        /* PAINEL POST */
        painelPost.add(imagemPost);
        painelPost.add(Box.createRigidArea(new Dimension(0, 10)));
        painelPost.add(legendaPost);
        ArrayList<Post> favoritos = s.verFavoritosDeUmUser(userLogado);

          final boolean[] isFavorito = {false};
          if(favoritos.contains(post)){
            isFavorito[0] = true;
          }
          if(isFavorito[0]){
            buttonFavoritar.setText("Desfavoritar");
            
            JLabel labelFavorito = new JLabel("Você favoritou este post");
            labelFavorito.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            painelPost.add(Box.createRigidArea(new Dimension(0, 10)));
            painelPost.add(labelFavorito);
          }
          
          buttonFavoritar.addActionListener(e -> {
            if(isFavorito[0]){
              s.desFavoritarPost(userLogado, post);
              JOptionPane.showMessageDialog(this, "Você desfavoritou o post");
            }else{
              s.favoritarPost(userLogado, post);
              JOptionPane.showMessageDialog(this, "Você favoritou o post");
            }

            TelaVerPropriosPosts telaVerPropriosPosts = new TelaVerPropriosPosts(s.buscarPorUsername(userLogado.getUsername()), s);
            telaVerPropriosPosts.setVisible(true);
            this.dispose();
        });
          
        painelPost.add(Box.createRigidArea(new Dimension(0, 10)));
        painelPost.add(painelBotoes);
        
        /* PAINEL CONTEÚDO */
        
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));
        painelConteudo.add(painelPost);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        /* FUNCIONALIDADES DOS BOTÕES */
        buttonDeletar.addActionListener(e -> {
          s.deletarPost(post);
          JOptionPane.showMessageDialog(this, "Post deletado com sucesso");
          TelaVerPropriosPosts telaVerPropriosPosts = new TelaVerPropriosPosts(userLogado, s);
          telaVerPropriosPosts.setVisible(true);
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
