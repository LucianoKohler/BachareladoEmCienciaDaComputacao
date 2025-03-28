package dados;

public class Reserva {
  private int numReserva;
  private String dataVoo;
  private String horaVoo;
  private float preco;
  private String classeVoo;
  private boolean idaEVolta = false; // Para fins de exercício, manti como sempre falso
  private int poltrona;
  private Reserva Volta = null;
  private Cidade origem;
  private Cidade destino;
  
  // Construtor
  public Reserva(int numReserva, String dataVoo, String horaVoo, float preco, String classeVoo, int poltrona) {
    this.numReserva = numReserva;
    this.dataVoo = dataVoo;
    this.horaVoo = horaVoo;
    this.preco = preco;
    this.classeVoo = classeVoo;
    this.poltrona = poltrona;

    // Cortados por enquanto
    // this.origem = origem;
    // this.destino = destino;
  }
  // Gets e Sets
  public int getNumReserva() {
    return numReserva;
  }
  public void setNumReserva(int numReserva) {
    this.numReserva = numReserva;
  }

  public String getDataVoo() {
    return dataVoo;
  }
  public void setDataVoo(String dataVoo) {
    this.dataVoo = dataVoo;
  }
  
  public String getHoraVoo() {
    return horaVoo;
  }
  public void setHoraVoo(String horaVoo) {
    this.horaVoo = horaVoo;
  }
  
  public float getPreco() {
    return preco;
  }
  public void setPreco(float preco) {
    this.preco = preco;
  }

  public String getClasseVoo() {
    return classeVoo;
  }
  public void setClasseVoo(String classeVoo) {
    this.classeVoo = classeVoo;
  }

  public boolean getIdaEVolta() {
    return idaEVolta;
  }
  public void setIdaEVolta (boolean idaEVolta){
    this.idaEVolta = idaEVolta;
  }

  public int getPoltrona() {
    return poltrona;
  }
  public void setPoltrona(int poltrona) {
    this.poltrona = poltrona;
  }

  public Reserva getVolta() {
    return Volta;
  }
  public void setVolta(Reserva volta) {
    Volta = volta;
  }

  public Cidade getOrigem() {
    return origem;
  }
  public void setOrigem(Cidade origem) {
    this.origem = origem;
  }

  public Cidade getDestino() {
    return destino;
  }
  public void setDestino(Cidade destino) {
    this.destino = destino;
  }

  public String toString(){
    String str = "";
    str+= "Número da reserva: " + numReserva + "\n";
    str+= "Data do voo: " + dataVoo + "\n";
    str+= "Horário do voo: " + horaVoo + "\n";
    str+= "Valor da passagem: " + preco + "\n";
    str+= "Classe do voo: " + classeVoo + "\n";
    str+= "Número da poltrona: " + poltrona + "\n";
    return str;

    // volta..., origem, destino
  }
}
