package apresentacao;

import java.util.Scanner;

import negocio.ReservaPassagem;
import dados.Cidade;
import dados.Cliente;
import dados.Reserva;


public class Principal {
		Scanner scan = new Scanner(System.in);
		ReservaPassagem sistema = new ReservaPassagem();

  public int imprimeMenu() {
		
		System.out.println("Escolha uma opção:");
		System.out.println("1 - Fazer reserva");
		System.out.println("2 - Cadastrar cliente");
		System.out.println("3 - Cadastrar cidade");
		System.out.println("4 - Mostrar reservas");
		System.out.println("0 - Sair do programa");
		System.out.print("Sua escolha: ");
		int opcao = Integer.valueOf(scan.nextLine());
		return opcao;
	}

	public void fazerReserva() {
    // Encontrando o cliente para reservar na conta dele
		System.out.print("Insira o cpf do cliente que reservou, apenas numeros: ");
		String cpf = scan.nextLine();
		Cliente c = sistema.encontrarClientePeloCpf(cpf);
		if(c == null){
			System.out.println("Cliente nao encontrado, retornando...");
		}else{

			System.out.print("Insira o numero da reserva: ");
			int id = Integer.parseInt(scan.nextLine());
			System.out.print("Insira a data do voo: ");
			String data = scan.nextLine();
			System.out.print("Insira o horario do voo: ");
			String horario = scan.nextLine();
			System.out.print("Insira o preco da passagem: ");
			float preco = Float.parseFloat(scan.nextLine());
			System.out.print("Insira a classe do voo (A, B ou C): ");
			String classe = scan.nextLine();
			System.out.print("Insira o numero da poltrona, apenas numeros: ");
			int poltrona = Integer.parseInt(scan.nextLine());
			// System.out.print("Insira a cidade de origem: ");
			// String origem = "placeholder";
			// System.out.print("Insira a cidade de volta: ");
			// String volta = "placeholder";

			Reserva r = new Reserva(id, data, horario, preco, classe, poltrona);
			c.reservarIda(r);
			System.out.println("Reserva feita com sucesso!");
		}
	}

	public void cadastrarCliente() {
		System.out.print("Insira o cpf do cliente, apenas números: ");
		String cpf = scan.nextLine();
		System.out.print("Insira o nome do cliente: ");
		String nome = scan.nextLine();
		System.out.print("Insira o endereco do cliente: ");
		String endereco = scan.nextLine();
		System.out.print("Insira o telefone do cliente, apenas números: ");
		String telefone = scan.nextLine();
		Cliente c = new Cliente(cpf, nome, endereco, telefone);

		sistema.cadastrarCliente(c);
		System.out.println("Cliente cadastrado com sucesso!");
	}

	public void cadastrarCidade() {
		System.out.print("Insira o nome da cidade: ");
		String nome = scan.nextLine();
		System.out.print("Insira o estado: ");
		String estado = scan.nextLine();
		Cidade c = new Cidade(nome, estado);

		sistema.cadastrarCidade(c);
		System.out.println("Cidade cadastrada com sucesso!");
	}

	public void mostrarReservas() {	
		System.out.print("Insira o cpf do cliente para mostrar as reservas, apenas números: ");
		String cpf = scan.nextLine();
			sistema.mostrarReservas(cpf);
		}

	public static void main(String[] args) {
		int opcao = -1;
		
		Principal main = new Principal();
		opcao = main.imprimeMenu();
		while(opcao != 0 ) {
			System.out.print("\033[H\033[2J"); // Para limpar o terminal
 			switch (opcao) {
				case 1:
					main.fazerReserva();
					break;
				case 2:
					main.cadastrarCliente();
					break;
				case 3:
					main.cadastrarCidade();
					break;
				case 4:
					main.mostrarReservas();
					break;
				default: 
					System.out.println("Opção Inválida!\n");
			}
			opcao = main.imprimeMenu();
		}
  }
}