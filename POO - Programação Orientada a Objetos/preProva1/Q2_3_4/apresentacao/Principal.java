package apresentacao;

import java.util.Scanner;

import dados.*;
import negocio.GerenciadorFormasGeometricas;

public class Principal {
  Scanner s = new Scanner(System.in);
  GerenciadorFormasGeometricas GFG = new GerenciadorFormasGeometricas();

  public void adicionarForma(FormaGeometrica forma){
    GFG.adicionarForma(forma);
  }
  
  public void imprimirGeometria(FormaGeometrica f){
    System.out.println("Área da forma: " + f.calcularArea());
    System.out.println("Perímetro da forma: " + f.calcularPerimetro());
  }
  
  public static void main(String[] args){
    Principal p = new Principal();
    p.adicionarForma(new Quadrado(1, 2, 3, 3, 3, 3, 3));
    p.adicionarForma(new Retangulo(7, 6, 10, 10, 5, 5, 5, 10));

    p.imprimirGeometria(p.GFG.getFormas().get(0));
    p.imprimirGeometria(p.GFG.getFormas().get(1));
  }
}
