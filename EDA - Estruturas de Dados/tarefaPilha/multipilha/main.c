#include <stdio.h>
#include "arq.h"

void imprimeMenu(){
  
  printf("--------------------------------");
  printf("\nSistema de multipilhas:");
  printf("\n0. Sair do programa");
  printf("\n1. Inserir na pilha");
  printf("\n2. Remover o topo da pilha");
  printf("\n3. Ver o topo da pilha");
  printf("\n4. Checar quantos elementos tem na pilha");
  printf("\n5. Reiniciar pilha");
  printf("\nSua escolha: ");
}

int main(){
  descritor* pilha = criaPilha(5); // Cabe no máximo, 5 elementos em cada lado da multipilha
  int escolha = -1;
  
  while(escolha){
    imprimeMenu();
    scanf("%d", &escolha);
    printf("--------------------------------\n");
    char* lado;
    switch (escolha)
    {
    case 1:
      char* nomeTag;
      printf("Insira o elemento ser inserido (string): ");
      scanf("%s", nomeTag);
      printf("Insira o lado a se inserir na pilha (direita ou esquerda): ");
      scanf("%s", lado);
      empilha(pilha, lado, nomeTag);
      break;
    
    case 2:
      printf("Insira o lado a se desempilhar na pilha (direita ou esquerda): ");
      scanf("%s", lado);
      desempilha(pilha, lado);
      break;
    
    case 3:
      printf("Insira o lado a se espiar na pilha (direita ou esquerda): ");
      scanf("%s", lado);
      printf("Retorno: %s\n", buscaNoTopo(pilha, lado));
      break;
    
    case 4:
      printf("Insira o lado a se contar na pilha (direita ou esquerda): ");
      scanf("%s", lado);
      int resultado = tamanhoPilha(pilha, lado);
      if(resultado != -1){
        printf("A pilha desse lado tem %d elementos\n", tamanhoPilha(pilha, lado));
      } 
      break;
    
    case 5:
      reiniciarPilha(pilha);
      printf("Pilha reiniciada com sucesso\n");
      break;
    }
  }
  return 0;
}