package apresentacao;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dados.Fibonacci;
import dados.Primos;
import dados.Quadrados;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculadora extends JFrame {
  public JPanel painel = new JPanel();
  public JPanel painelEntrada = new JPanel();
  public JLabel infoCaixaTexto = new JLabel("Digite um valor: ");
  private JTextField caixaTexto = new JTextField();
  private JButton botaoAdicionar = new JButton("adicionar");
  private JButton botaoLimpar = new JButton("limpar");
  
  public JScrollPane painelScrollTabelaResultados = new JScrollPane();
  private TabelaResultados resultados = new TabelaResultados();
  private JTable tabelaResultados = new JTable(resultados);

  public JScrollPane painelScrollTabelaValores = new JScrollPane();
  private TabelaValores valores = new TabelaValores();
  private JTable tabelaValores = new JTable(valores);

  // Painel gerador (desafio do professor)
  public JPanel painelSequencia = new JPanel();
  public JTextField caixaTextoSequencia = new JTextField();
  private JButton botaoFibonacci = new JButton("Fibonacci");
  private JButton botaoPrimos = new JButton("Primos");
  private JButton botaoQuadrados = new JButton("Quadrados");

  

  public Calculadora(){
    /*********** INSERINDO ELEMENTOS NA TELA **********/
    // Painel principal
    setTitle("Calculadora Estatística");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 900, 300);
    setContentPane(painel);
    painel.setLayout(null);

    // Painel de entrada de dados
    painelEntrada.setBounds(15, 80, 280, 173);
    painelEntrada.setLayout(null);
    painel.add(painelEntrada);

    // Caixa de texto de informações
    infoCaixaTexto.setBounds(30, 30, 200, 15);
    painelEntrada.add(infoCaixaTexto);

    // Caixa de texto de input
    caixaTexto.setBounds(30, 50, 200, 20);
    painelEntrada.add(caixaTexto);

    // Botões de adicionar e limpar
    botaoAdicionar.setBounds(77, 94, 117, 25);
    botaoLimpar.setBounds(77, 136, 117, 25);
    painelEntrada.add(botaoAdicionar);
    painelEntrada.add(botaoLimpar);

    // Painel para a tabela resultados
    painelScrollTabelaResultados.setBounds(10, 10, 870, 50);
    painel.add(painelScrollTabelaResultados);

    // Painel para a tabela valores
    painelScrollTabelaValores.setBounds(307, 80, 173, 173);
    painel.add(painelScrollTabelaValores);
  
    // Tabela resultados e valores
    painelScrollTabelaResultados.setViewportView(tabelaResultados);
    painelScrollTabelaValores.setViewportView(tabelaValores);

    // Desafio do professor

    // Painel de sequência
    painelSequencia.setBounds(550, 75, 280, 173);
    painelSequencia.setBorder(BorderFactory.createTitledBorder("Gerar Valores"));
    painelSequencia.setLayout(null);
    painel.add(painelSequencia);

    // Caixa de texto geradora de sequência
    caixaTextoSequencia.setBounds(30, 30, 220, 20);
    painelSequencia.add(caixaTextoSequencia);

    // Botões geradores de sequência
    botaoFibonacci.setBounds(60, 60, 160, 25);
    botaoPrimos.setBounds(60, 95, 160, 25);
    botaoQuadrados.setBounds(60, 130, 160, 25);
    painelSequencia.add(botaoFibonacci);
    painelSequencia.add(botaoPrimos);
    painelSequencia.add(botaoQuadrados);

    // Fim do desafio

    /*********** INSERINDO FUNCIONALIDADES AOS ELEMENTOS **********/
    botaoAdicionar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0){
        valores.adicionaValor(Integer.parseInt(caixaTexto.getText()));
        resultados.atualizar();
        caixaTexto.setText("");
      }
    });

    botaoLimpar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0){
        valores.limpar();
        resultados.atualizar();
      }
    });

    // Desafio do professor 

    botaoFibonacci.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0){
        if(!caixaTextoSequencia.getText().trim().isEmpty()){
          int qtdGerar = Integer.parseInt(caixaTextoSequencia.getText());
          Fibonacci novaSequencia = new Fibonacci();
          novaSequencia.gerar(qtdGerar);
          for(int valor : novaSequencia.getSequencia()){
            valores.adicionaValor(valor);
          }
          resultados.atualizar();
          caixaTextoSequencia.setText("");
        }else{
          JOptionPane.showMessageDialog(null, "Insira um valor válido na caixa de texto!");
        }
      }
    });

    botaoPrimos.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0){
        if(!caixaTextoSequencia.getText().trim().isEmpty()){
          int qtdGerar = Integer.parseInt(caixaTextoSequencia.getText());
          Primos novaSequencia = new Primos();
          novaSequencia.gerar(qtdGerar);
          for(int valor : novaSequencia.getSequencia()){
            valores.adicionaValor(valor);
          }
          resultados.atualizar();
          caixaTextoSequencia.setText("");
        }else{
          JOptionPane.showMessageDialog(null, "Insira um valor válido na caixa de texto!");
        }
      }
    });

    botaoQuadrados.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0){
        if(!caixaTextoSequencia.getText().trim().isEmpty()){
          int qtdGerar = Integer.parseInt(caixaTextoSequencia.getText());
          Quadrados novaSequencia = new Quadrados();
          novaSequencia.gerar(qtdGerar);
          for(int valor : novaSequencia.getSequencia()){
            valores.adicionaValor(valor);
          }
          resultados.atualizar();
          caixaTextoSequencia.setText("");
        }else{
          JOptionPane.showMessageDialog(null, "Insira um valor válido na caixa de texto!");
        }
      }
    });
  
    // Fim do desafio
  }
  public static void main(String[] args) {
    Calculadora frame = new Calculadora();
    frame.setVisible(true);
  }

}
