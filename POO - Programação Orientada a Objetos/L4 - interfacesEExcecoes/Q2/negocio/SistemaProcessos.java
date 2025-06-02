package negocio;

import dados.*;
import exceptions.pilhaCheiaException;
import exceptions.processoSemJuizException;

import java.util.ArrayList;
import java.util.Collections;

public class SistemaProcessos {
  private int trackerProcesso = 0;
  private ArrayList<Processo> processos;  
  private ArrayList<Juiz> juizes;

  public SistemaProcessos(){
    processos = new ArrayList<Processo>();
    juizes = new ArrayList<Juiz>();
  }

  public void cadastrarProcesso(Processo p){
    p.setId(trackerProcesso);
    processos.add(p);
    trackerProcesso++;
  }

  public void cadastrarJuiz(Juiz juiz){
    juizes.add(juiz);
  }

  public void distribuirProcessos() throws processoSemJuizException {

    // Aleatoriezando os processos a serem distribuídos
    Collections.shuffle(processos);
    int somatorioLimiteJuizes = 5*juizes.size();
    int juizDaVez = 0;

    for(int i = 0; i < processos.size(); i++){ // Insere processos mas para quando um processo não puder ser inserido em qualquer um dos juízes
      
      System.out.println("Distribindo o processo n°" + (i+1) + ": ");

      try{
        System.out.println("Distribuindo para o juiz " + juizes.get(juizDaVez).getNome());
        juizes.get(juizDaVez).cadastrarProcesso(processos.get(i));
      } catch (pilhaCheiaException e){
        System.out.println(e.getMessage());
      }

      System.out.println();
      // Indo para o próximo juiz, voltando para o primeiro se o "depois do último" foi o escolhido
      juizDaVez++;
      if(juizDaVez == juizes.size()){
        juizDaVez = 0;
      }
    }

    if(somatorioLimiteJuizes < processos.size()){
      System.out.println("Número de processos sem distribuição: " + (processos.size() - somatorioLimiteJuizes));
      throw new processoSemJuizException();
    }
  }
}
