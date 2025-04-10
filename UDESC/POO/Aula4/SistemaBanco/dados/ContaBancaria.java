package dados;

public class ContaBancaria {
    private long cpf;
    protected float saldo;

    // Construtor
    public ContaBancaria(){
        this.saldo = 0;
    }

    // Métodos
    public long getCpf() {
        return cpf;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public float sacar(float valor){
        // No exemplo do professor, a função retora valor, mas acho mais condizente retornar o novo saldo.
        float novoSaldo = this.saldo - valor;

        if (novoSaldo < 0) {
            System.out.println("Saldo insuficiente.");
            return this.saldo;
        }else{
            System.out.println("Valor sacado: " + valor);
            System.out.println("Novo saldo: " + novoSaldo);
            this.saldo = novoSaldo;
            return novoSaldo;
        }
    }

    public String gerarExtrato(){
        String str = "Extrato: R$" + this.saldo;
        return str;
    }

    public String toString(){
        return "CPF: " + this.cpf + "\n"; 
    }
}
