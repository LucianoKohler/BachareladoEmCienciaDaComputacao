#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h> // Para aleatorizar a fila
#include "arq.h"

int main(){
  srand(time(NULL));
  descritor* bancoDeAlunos = criarFila();
  lerCSV("../dataset_v1.csv", bancoDeAlunos); // Enchendo a fila com os 10000 registros para ajudar na criação das bases
  embaralhaFila(bancoDeAlunos); // Deixa ela aleatória para que a simulação seja mais fiel a um cenário real

  printf("RELATORIO DE DESEMPENHO DA FILA DE PRIORIDADE COM/SEM REFERENCIA MOVEL\n");
  printf("| BASE | TIPO INSERCAO | ITERACOES | ITERACOES MEDIAS | MELHORIA (%%) |\n");
  for(int i = 500; i <=9000; i+=500){
    int numIterComRef = 0;
    int numIterSemRef = 0;
    descritor *novaFila = criarFila();
    
    geraBase(1, i, &numIterComRef, bancoDeAlunos, novaFila);
    printf("| %4d |    COM REF:   | %9d | %16.2f |              | \n", i, numIterComRef, (numIterComRef / (1.0 * i)));
    
    reinicia(novaFila);
    
    geraBase(0, i, &numIterSemRef, bancoDeAlunos, novaFila);
    double melhoria = 100.0 * (numIterSemRef - numIterComRef) / numIterSemRef;

    printf("|      |    SEM REF:   | %9d | %16.2f |    %4.2f%%    |\n", numIterSemRef, (numIterSemRef / (1.0 * i)), melhoria);
    printf("----------------------------------------------------------------------\n");

  }
  return 0;
}