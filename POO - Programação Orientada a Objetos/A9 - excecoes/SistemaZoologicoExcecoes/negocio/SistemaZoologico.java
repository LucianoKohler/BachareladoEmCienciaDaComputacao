package negocio;

import exceptions.EspacoIndisponivelException;
import dados.*;

public class SistemaZoologico {
  private Animal[] animais = new Animal[50];
  private int qtdAnimais = 0;
  private Viveiro[] viveiros = new Viveiro[10];
  private int qtdViveiros = 0;

  public Viveiro[] getViveiros() {
    return viveiros;
  }
  public int getQtdViveiros() {
    return qtdViveiros;
  }
  
  public Animal[] getAnimais() {
    return animais;
  }
  public int getQtdAnimais() {
    return qtdAnimais;
  }

  public void cadastrarViveiro(Viveiro viveiro){
    if(qtdViveiros < 10){
      viveiros[qtdViveiros] = viveiro;
      qtdViveiros++;
      System.out.println("Viveiro cadastrado com sucesso.");
    }else{
      System.out.println("Quantidade máxima de viveiros atingida.");
    }
  }

  public void cadastrarAnimal(Animal animal){
    if(qtdAnimais < 50){
      animais[qtdAnimais] = animal;
      qtdAnimais++;
      System.out.println("Animal cadastrado com sucesso.");
    }else{
      System.out.println("Quantidade máxima de animais atingida.");
    }
  }

  public void alocarAnimal(Viveiro viveiro, Animal animal) throws EspacoIndisponivelException{
    if(viveiro instanceof Aquario && animal instanceof Peixe){
      viveiro.adicionarAnimal(animal);
    }else if(!(viveiro instanceof Aquario) && !(animal instanceof Peixe)){
      viveiro.adicionarAnimal(animal);
    }else{
      throw new EspacoIndisponivelException();
    }
  }

  public Viveiro[] getSoViveiros(){
    int qtdViveiros = 0;
    for(int i = 0; i < this.qtdViveiros; i++){
      if(!(viveiros[i] instanceof Aquario)){
        qtdViveiros++;
      }
    }

    Viveiro[] viveiros = new Viveiro[qtdViveiros];
    int j = 0;

    for(int i = 0; i < this.qtdViveiros; i++){
      if(!(this.viveiros[i] instanceof Aquario)){
        viveiros[j] = this.viveiros[i];
        j++;
      }
    }
    return viveiros;
  }

  public Aquario[] getSoAquario(){
    int qtdAquarios = 0;
    for(int i = 0; i < this.qtdViveiros; i++){
      if(viveiros[i] instanceof Aquario){
        qtdAquarios++;
      }
    }
    Aquario[] aquarios = new Aquario[qtdAquarios];
    int j = 0;

    for(int i = 0; i < this.qtdViveiros; i++){
      if(this.viveiros[i] instanceof Aquario){
        aquarios[j] = (Aquario) this.viveiros[i];
        j++;
      }
    }
    return aquarios;
  }
}
