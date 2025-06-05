#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct noDados {
  char informacao[50];
  struct noDados* prox;
} noDados;

typedef struct {
  noDados* topoEsquerda;
  noDados* topoDireita;
  int tamVetor;
} descritor;

/* Funções */
descritor* criaPilha(int tamVetor);
void reiniciarPilha(descritor* pilha);
void destruirPilha(descritor* pilha);
void empilha(descritor* pilha, char* lado, char* nomeNovaTag);
void desempilha(descritor* pilha, char* lado);
char* buscaNoTopo(descritor* pilha, char* lado);
int checaSeCheia(descritor* pilha, char* lado);
int pilhaVazia(descritor* pilha, char* lado);
int tamanhoPilha(descritor* pilha, char* lado);

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

/*********** INSERE NA PILHA **************/
void empilha(descritor* pilha, char* lado, char* nomeNovaTag){
  noDados* novoNodo = (noDados*) malloc(sizeof(noDados));
  strcpy(novoNodo->informacao, nomeNovaTag);
  novoNodo->prox = NULL;

  if(!checaSeCheia(pilha, lado)){
      if(strcmp("esquerda", lado) == 0){ // Inserir na esquerda
        novoNodo->prox = pilha->topoEsquerda;
      pilha->topoEsquerda = novoNodo;
    }else if(strcmp("direita", lado) == 0){ // Inserir na direita
      novoNodo->prox = pilha->topoDireita;
      pilha->topoDireita = novoNodo;
    }else{
      printf("Erro, insercao nao especificada (inserir na direita ou esquerda)");
      free(novoNodo);
    }
  }else{
    printf("Erro: a pilha esta cheia\n");
  }
}

/*********** REMOVE DA PILHA **************/
void desempilha(descritor* pilha, char* lado){
  if(strcmp("esquerda", lado) == 0){ // Deletar na esquerda
    if(!pilhaVazia(pilha, "esquerda")){
      noDados* temp = pilha->topoEsquerda;
      pilha->topoEsquerda = pilha->topoEsquerda->prox;
      free(temp);
    }else{
      printf("Erro: Remocao invalida");
    }
  }else if(strcmp("direita", lado) == 0){ // Deletar na direita
    if(!pilhaVazia(pilha, "direita")){
      noDados* temp = pilha->topoDireita;
      pilha->topoDireita = pilha->topoDireita->prox;
      free(temp);
    }else{
      printf("Erro: Remocao invalida");
    }
  }else{
    printf("Erro: Lado para deletar nao reconhecido: %s\n", lado);
  }
}

/*********** BUSCA NO TOPO DA PILHA **************/
char* buscaNoTopo(descritor* pilha, char* lado){
  if     (strcmp(lado, "esquerda") == 0) { return pilha->topoEsquerda->informacao; }
  else if(strcmp(lado, "direita") == 0)  { return pilha->topoDireita->informacao; }
  else                                   { return "Lado nao reconhecido\n"; }
}

/*********** CHECA SE PILHA CHEIA **************/
int checaSeCheia(descritor* pilha, char* lado){
  noDados* nodo = NULL;
       if(strcmp("esquerda", lado) == 0){ nodo = pilha->topoEsquerda; }
  else if(strcmp("direita", lado)  == 0){ nodo = pilha->topoDireita;  }
  else{ printf("Erro: Lado nao reconhecido: %s\n", lado); return -1; }
  
  int qtdNodos = 0;
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
  if(strcmp("esquerda", lado) == 0){ // Checa se a pilha esquerda está vazia
    if(pilha->topoEsquerda == NULL){ return 1; }
    return 0;
  }else if(strcmp("direita", lado) == 0){ // Checa se a pilha direita está vazia
    if(pilha->topoDireita == NULL){ return 1; }
    return 0;
  }else{
    printf("Erro: lado da pilha nao identificado: %s", lado);
    return -1;
  }
}

/*********** CHECA TAMANHO DA PILHA **************/
int tamanhoPilha(descritor* pilha, char* lado){
  noDados* nodo = NULL;
       if(strcmp(lado, "direita") == 0) {nodo = pilha->topoDireita; }
  else if(strcmp(lado, "esquerda") == 0){nodo = pilha->topoEsquerda; }
  else                                  {printf("Erro: lado nao reconhecido: %s\n", lado); return -1; }

  int qtdNodos = 0;
  while(nodo != NULL){
    nodo = nodo->prox;
    qtdNodos++;
  }

  return qtdNodos;
}
