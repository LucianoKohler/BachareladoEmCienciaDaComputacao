#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "arq.h"

void imprimeMenu(){
  
  printf("Escolha uma operacao:\n");
  printf("0. Sair do programa\n");
  printf("1. Printar fila\n");
  printf("2. \n");
  printf("3. \n");
  printf("4. \n");
  printf("5. \n");
  printf("Sua escolha: ");
}

int main(){
  descritor* fila = criarFila();

  printf("SISTEMA DE FILA DE PRIORIDADADE COM PRIORIDADE E REFERENCIA MOVEL\n");
  printf("Gostaria de inicializar a fila com os 10000 registros do arquivo .csv? (1 - Sim, 0 - Nao): ");
  int lerCsv;
  scanf("%d", &lerCsv);

  if(lerCsv == 1){
      lerCSV("../dataset_v1.csv", fila);
  }


  // Loop principal
  int escolha = -1;
  while(escolha != 0){
    imprimeMenu();
    scanf("%d", &escolha);
    switch (escolha)
    {
    case 1:
      printarFila(fila);
      break;
    
    default:
      break;
    }
  }

  return 0;
}