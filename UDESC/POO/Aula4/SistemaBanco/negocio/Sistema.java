package negocio;

import dados.*;

public class Sistema {
  private ContaBancaria[] contasBancarias = new ContaBancaria[10];
  private int quantContas = 0;

  public void cadastrarConta(ContaBancaria conta){
    if(quantContas < 100){
      contasBancarias[quantContas] = conta; 
      quantContas++;
      System.out.println("Conta cadastrada com sucesso!");
    } else {
      System.out.println("Limite de contas atingido.");
    }
  }

  public void realizarSaque(ContaBancaria conta, float valor){
    conta.sacar(valor);
  }

  public boolean realizarDeposito(ContaCorrente conta, float valor){
    return conta.depositar(valor);
  }

  public boolean realizarDeposito(ContaSalario conta, float valor, int cnpj){
    return conta.depositar(valor, cnpj);
  }

  public ContaCorrente[] getContasCorrente(){
    
    // Encontrando a quantidade de contas corrente
    int max = 0;
    for(int i = 0; i < quantContas; i++){
      if(contasBancarias[i] instanceof ContaCorrente){
        max++;
      }
    }
    ContaCorrente[] contasCorrente = new ContaCorrente[max];

    int j = 0;
    for(int i = 0; i < quantContas; i++){
      if(contasBancarias[i] instanceof ContaCorrente){
        contasCorrente[j] = (ContaCorrente) contasBancarias[i];
        j++;
      }
    }
    return contasCorrente;
  }

  public ContaSalario[] getContasSalario(){
        
    // Encontrando a quantidade de contas salário
    int max = 0;
    for(int i = 0; i < quantContas; i++){
      if(contasBancarias[i] instanceof ContaSalario){
        max++;
      }
    }
    ContaSalario[] contasSalario = new ContaSalario[max];

    int j = 0;
    for(int i = 0; i < quantContas; i++){
      if(contasBancarias[i] instanceof ContaSalario){
        contasSalario[j] = (ContaSalario) contasBancarias[i];
        j++;
      }
    }
    return contasSalario;
  }

  public int getQuantContas() {
    return quantContas;
  }

  public ContaBancaria[] getContasBancarias() {
    return contasBancarias;
  }

  public String obterExtrato(ContaBancaria conta){
    return conta.gerarExtrato();
  }
}
