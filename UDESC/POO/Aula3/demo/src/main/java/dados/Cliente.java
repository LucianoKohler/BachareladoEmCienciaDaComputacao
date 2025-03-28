package dados;

public class Cliente {
  private String cpf;
  private String nome;
  private String endereco;
  private String telefone;

  private Reserva reservas[] = new Reserva[10];
  private int quantReservas;

  // Construtor
  public Cliente(String cpf, String nome, String endereco, String telefone){
    this.cpf = cpf;
    this.nome = nome;
    this.endereco = endereco;
    this.telefone = telefone;
  }

  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }
  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getTelefone() {
    return telefone;
  }
  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public int getQuantReservas() {
    return quantReservas;
  }

  public Reserva[] getReservas() {
    return reservas;
  }

  public void reservarIda(Reserva reserva){
    if(quantReservas < 10) {
      reservas[quantReservas] = reserva;
      quantReservas++;
    }else{
      System.out.println("Lista de reservas cheia!");
    }
  }

  public void reservarVolta(Reserva ida, Reserva volta) {
    if(quantReservas < 10) {
      reservas[quantReservas] = volta;
      quantReservas++;
      ida.setVolta(volta);
    }else{
      System.out.println("Lista de reservas cheia!");
    }
  }

  public String toString() {
    String str = "";
    str+= "CPF: " + cpf + "\n";
    str+= "Nome: " + nome + "\n";
    str+= "endereco: " + endereco + "\n";
    str+= "Telefone: " + telefone + "\n";
    return str;
  }

  public boolean equals(Object o) {
    if(o instanceof Cliente){
      Cliente c = (Cliente) o;
      if(c.getNome().equals(nome)){
        return true;
      }
    }
    return false;
  }
}
