#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h> // Para aleatorizar a fila
#include "arq.h"

int main(){
  srand(time(NULL));
  descritor* fila = criarFila();
  lerCSV("../dataset_v1.csv", fila); // Enchendo a fila com os 10000 registros para ajudar na criação das bases
  embaralhaFila(fila); // Deixa ela aleatória para que a simulação seja mais fiel a um cenário real

  printf("RELATORIO DE DESEMPENHO DA FILA DE PRIORIDADE COM/SEM REFERENCIA MOVEL\n");
  printf(" BASE | TIPO INSERCAO | ITERACOES | ITERACOES MEDIAS |\n");
  for(int i = 500; i <=9000; i+=500){
    int numIter = 0;
    descritor *novaFila = criarFila();
    
    geraBase(1, i, &numIter, fila, novaFila);
    printf(" %4d |    COM REF:   | %9d | %16.2f |\n", i, numIter, (numIter / (1.0 * i)));
    
    reinicia(novaFila);
    numIter = 0;
    
    geraBase(0, i, &numIter, fila, novaFila);
    printf("      |    SEM REF:   | %9d | %16.2f |\n", numIter, (numIter / (1.0 * i)));
    printf("------------------------------------------------------\n");

  }
  return 0;
}