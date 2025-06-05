#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct noDados {
  char nomeTag[50];
  struct noDados* prox;
} noDados;

typedef struct {
  noDados* topo;
  int tamVetor;
} descritor;

/* Funções */
descritor* criaPilha(int tamVetor);
void reiniciarPilha(descritor* pilha);
void destruirPilha(descritor* pilha);
void empilha(descritor* pilha, char* nomeNovaTag);
void desempilha(descritor* pilha);
char* buscaNoTopo(descritor* pilha);
int checaSeCheia(descritor* pilha);
int pilhaVazia(descritor* pilha);
int tamanhoPilha(descritor* pilha);
void lerHTML(char* nomeArquivo, descritor* pilha);

// Usado somente na leitura do HTML
// Auxiliar que remove espaços desnecessários e quebras de linhas em cada linha 
void formataLinha(char* linha){
    char* inicio = linha;

  // Avança até o primeiro caractere que não é " "
  // isspace reconhece " ", "\t", "\n", entre outros
  while (isspace((unsigned char)*inicio)) {
      inicio++;
  }

  // Move a string sem espaços para o início da string
  memmove(linha, inicio, strlen(inicio) + 1);

  // Remove espaços no final
  int len = strlen(linha);
  while (len > 0 && isspace((unsigned char)linha[len - 1])) {
      linha[len - 1] = '\0';
      len--;
  }
}

void lerHTML(char* nomeArquivo, descritor* pilha){
  FILE *arquivo = fopen(nomeArquivo, "r");
  char linha[128]; // Linha
  while(fgets(linha, sizeof(linha), arquivo)){
    formataLinha(linha);
    if(linha[0] == '\n' || linha[0] == '\0'){ continue; } // Se for uma linha vazia, pula
    if(linha[0] != '<'){ continue; } // Se não for uma tag, pula

    // Se a tag for única (não possuir abertura e fechamento)
    if(strstr(linha, "!DOCTYPE") != NULL ||
       strstr(linha, "input")    != NULL ||
       strstr(linha, "frame")    != NULL ||
       strstr(linha, "br")       != NULL ||
       strstr(linha, "img")      != NULL){
      continue;
    }
    if(linha[1] == '/') { // Se for uma tag de fechamento
      
      // Checando se a tag fechada fecha o elemento corretamente
      char* abertura = buscaNoTopo(pilha) +1; // Corta o "<"
      char* fechamento = linha + 2; // Corta o "</"

      if(strcmp(abertura, fechamento) == 0) { // Se forem iguais
        desempilha(pilha);
      }else{
        printf("Erro de compilacao: esperado <\\%s, recebido <\\%s", abertura, fechamento);
        fclose(arquivo);
        return;
      }
    }else{ // Se estiver abrindo uma nova tag
      empilha(pilha, linha);
    }
  }
  printf("Nenhum erro de compilacao encontrado, o HTML esta estuturado corretamente.");
  fclose(arquivo);
}

/*********** CRIA PILHA **************/
descritor* criaPilha(int tamVetor){
    descritor* pilha = (descritor*)malloc(sizeof(descritor));
    pilha->topo = NULL;
    pilha->tamVetor = tamVetor;
    return pilha;
}

/*********** REINICIA PILHA **************/
void reiniciarPilha(descritor* pilha){
    noDados* atual = pilha->topo;
    // Limpando a pilha esquerda
    while (atual != NULL) {
        noDados* temp = atual;
        atual = atual->prox;
        free(temp);
    }
    pilha->topo = NULL;
}

/*********** DESTROI PILHA **************/
void destruirPilha(descritor* pilha){
        reiniciarPilha(pilha);
        free(pilha);
}

/*********** INSERE NA PILHA **************/
void empilha(descritor* pilha, char* nomeNovaTag){
  noDados* novoNodo = (noDados*) malloc(sizeof(noDados));
  strcpy(novoNodo->nomeTag, nomeNovaTag);
  novoNodo->prox = NULL;

  if(!checaSeCheia(pilha)){
    novoNodo->prox = pilha->topo;
    pilha->topo = novoNodo;
  }else{
    printf("Erro: a pilha esta cheia");
    free(novoNodo);
  }
}

/*********** REMOVE DA PILHA **************/
void desempilha(descritor* pilha){
  if(!pilhaVazia(pilha)){
    noDados* temp = pilha->topo;
    pilha->topo = pilha->topo->prox;
    free(temp);
  }else{
      printf("Erro: A pilha esta vazia");
  }
}

/*********** BUSCA NO TOPO DA PILHA **************/
char* buscaNoTopo(descritor* pilha){
  return pilha->topo->nomeTag;
}

/*********** CHECA SE PILHA CHEIA **************/
int checaSeCheia(descritor* pilha){
  noDados* nodo = pilha->topo;
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
int pilhaVazia(descritor* pilha){
   return pilha->topo == NULL ? 1 : 0;
}

/*********** CHECA TAMANHO DA PILHA **************/
int tamanhoPilha(descritor* pilha){
  noDados* nodo = pilha->topo;

  int qtdNodos = 0;
  while(nodo != NULL){
    nodo = nodo->prox;
    qtdNodos++;
  }

  return qtdNodos;
}
