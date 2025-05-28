package codigoDaAula;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Principal extends  JFrame {

  // Valores padrão para a janela
  private static final int DEFAULT_WIDTH = 300;
  private static final int DEFAULT_HEIGHT = 300;

  // Criando os elementos para a tela
  private JTextField origem = new JTextField("copiar");
  private JButton destino = new JButton("destino");
  private JButton copiar = new JButton("copiar");
  private JButton sair = new JButton("sair");

  public Principal() {
    this.setContentPane(new JPanel(new BorderLayout()));
    this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    JPanel labelsDasTextAreas = new JPanel(new GridLayout(0, 1));
    labelsDasTextAreas.add(new JLabel("Origem"));
    labelsDasTextAreas.add(new JLabel("destino"));

    JPanel textAreas = new JPanel(new GridLayout(0, 1));
    textAreas.add(origem);
    textAreas.add(destino);

    this.add(labelsDasTextAreas, BorderLayout.WEST);
    this.add(textAreas, BorderLayout.CENTER);

    // Temos duas "divs": labelsDasTextAreas e textAreas, enquanto ambas usam layout grid, a div que engloba as duas, é em borderLayout

    // Criando a div botoes e colocando botoes dentro dela
    JPanel botoes = new JPanel();
    botoes.add(copiar);
    botoes.add(sair);

    this.add(botoes, BorderLayout.SOUTH);

    // Dando eventos para os botões
    copiar.addActionListener(new ActionListener() { // Como uma arrow function em JS
      @Override
      public void actionPerformed(ActionEvent e) { // actionPerformed é a função que fará o evento propriamente dito
        destino.setText(origem.getText());
      }
    });

    sair.addActionListener(new ActionListener() { 
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
  }

  public static void main(String[] args) {
      Principal tela = new Principal();

      /* Essas características podem ser colocadas no construtor também. */
      tela.setTitle("Tela principal Teste");
      tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // tela.setResizable(false);
      tela.setVisible(true);
      
  }
}