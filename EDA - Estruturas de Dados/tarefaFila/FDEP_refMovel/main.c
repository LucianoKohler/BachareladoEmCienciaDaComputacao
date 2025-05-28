#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "arq.h"

void imprimeMenu(){
  
  printf("=========================\n");
  printf("Escolha uma operacao:\n");
  printf("0. Sair do programa\n");
  printf("1. Printar fila\n");
  printf("2. Inserir na fila\n");
  printf("3. Reiniciar fila\n");
  printf("4. Buscar na fila\n");
  printf("5. Remove elemento da fila\n");
  printf("6. Inverte a fila\n");
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
    printf("=========================\n");

    switch (escolha)
    {
    case 1:
      printarFila(fila);
      break;
    
    case 2:
      break;
        
    case 3:
      reinicia(fila);
      printf("Fila reiniciada\n");
      break;
        
    case 4:
      break;
        
    case 5:
      break;
        
    case 6:
      inverte(fila);
      printf("Fila invertida\n");
      break;
        
    default:
      break;
    }
  }

  return 0;
}