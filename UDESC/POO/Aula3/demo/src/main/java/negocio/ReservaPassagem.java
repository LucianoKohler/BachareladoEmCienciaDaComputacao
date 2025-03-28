package negocio;

import dados.Cidade;
import dados.Cliente;
import dados.Reserva;

public class ReservaPassagem {

  private Cidade listaDeCidades[] = new Cidade[20];
  private int quantCidades;
  private Cliente listaDeClientes[] = new Cliente[20];
  private int quantClientes;

  public void cadastrarCidade(Cidade cidade){
    if(quantCidades < listaDeCidades.length){
      listaDeCidades[quantCidades] = cidade;
      quantCidades++;
    }else{
      System.out.println("Numero maximo de cidades atingido");
    }
  }

  public void cadastrarCliente(Cliente cliente){
    if(quantClientes < listaDeClientes.length){
      listaDeClientes[quantClientes] = cliente;
      quantClientes++;
    }else{
      System.out.println("Numero maximo de clientes atingido");
    }
  }

  public Cliente encontrarClientePeloCpf(String cpf){
    for(int i = 0; i < quantClientes; i++){
      if(listaDeClientes[i] != null && listaDeClientes[i].getCpf().equals(cpf)){
        return listaDeClientes[i];
      }
    }
    return null;
  }

  // Reservar ida
  public void reservarIda(Cliente cliente, Reserva reserva) {
    cliente.reservarIda(reserva);
  }

  // Reservar volta
  public void reservarVolta(Cliente cliente, Reserva ida, Reserva volta){
    cliente.reservarVolta(ida, volta);
  }
  
  // Mostrar reservas
  public void mostrarReservas(String cpfCliente) {
    Cliente c = encontrarClientePeloCpf(cpfCliente);
    if(c != null){
        Reserva[] reservas = c.getReservas();
        System.out.println("Reservas do cliente " + c.getNome() + ":");
        for(int j = 0; j < reservas.length; j++){
          if (reservas[j] != null) {
            System.out.println(reservas[j].toString() + "\n");
          }
        }
      }else{
        System.out.println("Cliente não encontrado, retornando...");
      }

  }

  public int getQuantCidades() {
    return quantCidades;
  }
  public int getQuantClientes() {
    return quantClientes;
  }

  public Cliente[] mostrarClientes(){
    return listaDeClientes;
  }

  public Cidade[] mostrarCidade(){
    return listaDeCidades;
  }
}
