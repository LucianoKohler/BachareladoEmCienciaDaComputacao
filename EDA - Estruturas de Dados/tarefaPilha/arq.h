#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
  char nomeTag[50];
  noDados* prox;
} noDados;

typedef struct {
  noDados* topoEsquerda;
  noDados* topoDireita;
  int tamVetor;
} descritor;

/* Funções */

/*********** CRIA PILHA **************/
descritor* criaPilha(int tamVetor){
    descritor* pilha = (descritor*)malloc(sizeof(descritor));
    pilha->topoEsquerda = NULL;
    pilha->topoDireita = NULL;
    pilha->tamVetor = tamVetor;
    return pilha;
}

/*********** REINICIA PILHA **************/
void reiniciarPilha(descritor* pilha){
    noDados* atual = pilha->topoEsquerda;
    // Limpando a pilha esquerda
    while (atual != NULL) {
        noDados* temp = atual;
        atual = atual->prox;
        free(temp);
    }
    pilha->topoEsquerda = NULL;

    // Limpando a pilha direita
    atual = pilha->topoDireita;
    while (atual != NULL) {
        noDados* temp = atual;
        atual = atual->prox;
        free(temp);
    }
    pilha->topoDireita = NULL;
}

/*********** DESTROI PILHA **************/
void destruirPilha(descritor* pilha){
        reiniciarPilha(pilha);
        free(pilha);
}

/*********** CHECA SE PILHA CHEIA **************/
int checaSeCheia(descritor* pilha, char* lado){
  noDados* nodo = NULL;
       if(strcmp("ESQUERDA", lado) == 0){ nodo = pilha->topoEsquerda; }
  else if(strcmp("DIREITA", lado)  == 0){ nodo = pilha->topoDireita;  }
  else{ printf("Erro: Lado não reconhecido: %s", lado); return -1; }
  
  int qtdNodos = 1;
  while(nodo != NULL){
      nodo = nodo->prox;
      qtdNodos++;
  }

  if(qtdNodos >= pilha->tamVetor){
    return 1;
  }
  return 0;
}

/*********** CHECA SE PILHA VAZIA **************/
int pilhaVazia(descritor* pilha, char* lado){
  if(strcmp("ESQUERDA", lado) == 0){ // Checa se a pilha esquerda está vazia
    if(pilha->topoEsquerda == NULL){ return 1; }
    return 0;
  }else if(strcmp("DIREITA", lado) == 0){ // Checa se a pilha direita está vazia
    if(pilha->topoDireita == NULL){ return 1; }
    return 0;
  }else{
    printf("Erro: lado da pilha não identificado: %s", lado);
    return -1;
  }
}

/*********** REMOVE DA PILHA **************/
void removeDaPilha(descritor* pilha, char* lado){
  if(strcmp("ESQUERDA", lado) == 0){ // Deletar na esquerda
    if(!pilhaVazia(pilha, "ESQUERDA")){
      noDados* temp = pilha->topoEsquerda;
      pilha->topoEsquerda = pilha->topoEsquerda->prox;
      free(temp);
    }else{
      printf("Erro: Remoção inválida");
    }
  }else if(strcmp("DIREITA", lado) == 0){ // Deletar na direita
    if(!pilhaVazia(pilha, "DIREITA")){
      noDados* temp = pilha->topoDireita;
      pilha->topoDireita = pilha->topoDireita->prox;
      free(temp);
    }else{
      printf("Erro: Remoção inválida");
    }
  }else{
    printf("Erro: Lado para deletar não reconhecido: %s", lado);
  }
}

/*********** INSERE NA PILHA **************/
void insereNaPilha(descritor* pilha, char* lado, char* nomeNovaTag){
  noDados* novoNodo = (noDados*) malloc(sizeof(noDados));
  strcpy(novoNodo->nomeTag, nomeNovaTag);
  novoNodo->prox = NULL;

  if(checaSeCheia(pilha, lado)){}
  // aqui
  if(strcmp("ESQUERDA", lado) == 0){ // Inserir na esquerda
    novoNodo->prox = pilha->topoEsquerda;
    pilha->topoEsquerda = novoNodo;
  }else if(strcmp("DIREITA", lado) == 0){ // Inserir na direita
    novoNodo->prox = pilha->topoDireita;
    pilha->topoDireita = novoNodo;
  }else{
    printf("Erro, inserção não especificada (inserir na direita ou esquerda)");
    free(novoNodo);
  }
}

