package dados;

public class ContaSalario extends ContaBancaria {
    private long cnpjEmpresa;

    // Construtor
    public ContaSalario() { super(); } // Chama o construtor da classe pai (ContaBancaria)

    public void setCnpjEmpresa(long cnpj) {
        this.cnpjEmpresa = cnpj;
    }
    public long getCnpjEmpresa() {
      return cnpjEmpresa;
    }

    public boolean depositar(float valor, int cnpj){
        if(this.cnpjEmpresa == cnpj){
            this.saldo += valor;
            return true;
        } else {
            System.out.println("CNPJ diferente do cadastrado.");
            return false;
        }
    }

    @Override
    public String gerarExtrato() {
        return "\nConta salário: \n" + "CNPJ da empresa: " + this.getCnpjEmpresa() + "\n" + super.gerarExtrato();
    }

    @Override
    public String toString() {
        return "\nConta Salário: \n" + super.toString() + "CNPJ: " + this.getCnpjEmpresa() + "\n";
    }

}
