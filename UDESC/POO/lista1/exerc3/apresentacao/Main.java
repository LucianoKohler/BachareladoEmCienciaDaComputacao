package apresentacao;

import dados.Imobiliaria;
import dados.Imovel;

public class Main {
  public static void main(String[] args){
    // Criando uma nova imobiliaria: 
    Imobiliaria jonasImobiliaria = new Imobiliaria("Jonas Imobiliaria");

    // Criando 5 imoveis:
    Imovel i1 = new Imovel(20, 15, 100000);   // area = 300m²
    Imovel i2 = new Imovel(30, 30, 400000);   // area = 900m²
    Imovel i3 = new Imovel(40, 20, 300000);   // area = 800m²
    Imovel i4 = new Imovel(15, 30, 200000);   // area = 450m²
    Imovel i5 = new Imovel(100, 100, 500000); // area = 10000m²

    jonasImobiliaria.adicionarImovel(i1);
    jonasImobiliaria.adicionarImovel(i2);
    jonasImobiliaria.adicionarImovel(i3);
    jonasImobiliaria.adicionarImovel(i4);
    jonasImobiliaria.adicionarImovel(i5);

    // Saída de dados:
    System.out.println(jonasImobiliaria.toString() + "\n");
  

    System.out.println("======================================\n");
    
    // Filtrando por apenas imoveis acima de Xm²
    float area = 500; // ****** Mude-me ******
    System.out.printf("MOSTRANDO IMOVEIS ACIMA DE %.2fM², ORDENADOS POR PRECO:\n", area);
    System.out.println(jonasImobiliaria.filtrarPorArea(area));
  }
}
