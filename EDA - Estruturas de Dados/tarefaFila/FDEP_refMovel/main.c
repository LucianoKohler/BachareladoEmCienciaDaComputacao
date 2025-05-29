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
  printf("4. Ver a cauda da fila\n");
  printf("5. Ver a frente da fila\n");
  printf("6. Remove elemento da fila\n");
  printf("7. Inverte a fila\n");
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
      aluno novoAluno;

      printf("Insira o nome do novo aluno: ");
      scanf("%s", novoAluno.nome);

      printf("Insira a matricula do novo aluno: ");
      scanf("%d", &novoAluno.matricula);

      printf("Insira o ranking do novo aluno: ");
      scanf("%d", &novoAluno.ranking);

      printf("Insira o curso do novo aluno: ");
      scanf("%s", novoAluno.curso);

      inserirFilaPrioridade(fila, novoAluno);
      printf("Aluno inserido");
      break;
        
    case 3:
      reinicia(fila);
      printf("Fila reiniciada\n");
      break;
        
    case 4: {
      aluno* a = NULL;
      int sucesso = buscaNaCauda(a, fila);
      sucesso ? printf("Aluno na cauda: %s", a->nome) : printf("Aluno não encontrado");
      break;
    }
        
    case 5: {
      aluno* a = NULL;
      int sucesso = buscaNaFrente(a, fila);
      sucesso ? printf("Aluno na frente: %s", a->nome) : printf("Aluno não encontrado");
      break;
    }      
    case 6:
      
    break;
    
    case 7:
      inverte(fila);
      printf("Fila invertida\n");
    break;

    default:
      break;
    }
  }

  return 0;
}