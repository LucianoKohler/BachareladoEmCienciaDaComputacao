package dados;

public class ContaCorrente extends ContaBancaria {

    // Construtor
    public ContaCorrente() { super(); } // Chama o construtor da classe pai (ContaBancaria) 

    // Métodos
    public boolean depositar(float valor){
        this.saldo += valor; // Atualiza o saldo com o valor depositado
        System.out.println("Valor depositado: " + valor);
        System.out.println("Novo saldo: " + this.getSaldo()); // Exibe o novo saldo
        return true;
    }

    @Override
    public String gerarExtrato(){
        return "Conta corrente: \n" + "CPF: " + this.getCpf() + "\n" + super.gerarExtrato();
    }

    @Override
    public String toString(){
        return "\nConta Corrente: \n" + super.toString();
    }
}
