import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;



public class Principal extends  JFrame {
  private static final int DEFAULT_WIDTH = 300;
  private static final int DEFAULT_HEIGHT = 300;

  private JTextField origem = new JTextField("copiar");
  private JButton destino = new JButton("destino");
  
  private JButton copiar = new JButton("copiar");
  private JButton sair = new JButton("sair");

  public Principal() {
    this.setContentPane(new JPanel(new BorderLayout()));
    this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    JPanel nomes = new JPanel(new GridLayout(0, 1));
    nomes.add(new JLabel("Origem"));
    nomes.add(new JLabel("destino"));

    JPanel conteudos = new JPanel(new GridLayout(0, 1));
    conteudos.add(origem);
    conteudos.add(destino);

    this.add(nomes, BorderLayout.WEST);
    this.add(conteudos, BorderLayout.CENTER);

    // Add copiar and sair buttons to the SOUTH region
    JPanel botoes = new JPanel();
    botoes.add(copiar);
    botoes.add(sair);
    this.add(botoes, BorderLayout.SOUTH);
  }

  public static void main(String[] args) {
      Principal tela = new Principal();

      tela.setTitle("Tela principal Teste");
      tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // tela.setResizable(false);
      tela.setVisible(true);
      
  }
}