#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "arq.h"

/*************** CRIAR FILA ***************/

descritor* criarFila(){
  descritor* d = malloc(sizeof(descritor));  
  d->cauda = NULL;
  d->frente = NULL;
  d->refMovel = NULL;
  d->tamLista = 0;
  d->tamInfo = sizeof(aluno);
  return d;
}

/*** INSERE COM PRIORIADE USANDO REFMOVEL **********/

void inserirFilaPrioridadeREFMOVEL(descritor* fila, aluno a, int* numIter){
  noDados* novoNodo = malloc(sizeof(noDados));
  novoNodo->info = a;
  novoNodo->atras = NULL;
  novoNodo->defronte = NULL;

  if(fila->frente == NULL){ // Se ela estiver vazia
    fila->frente = novoNodo;
    fila->cauda = novoNodo;
  }else{
    noDados* atual = NULL;

    // Começa pela referência móvel, se fizer sentido
    if (fila->refMovel && fila->refMovel->info.ranking <= a.ranking) {
      atual = fila->refMovel;
    } else {
      atual = fila->frente;
    }

    while(atual != NULL && atual->info.ranking <= a.ranking){
      atual = atual->defronte; // aumenta prioridade
      if(numIter !=NULL){ (*numIter)++; }
    } // Ao sair do while, temos a posição para colocar o novo aluno

    if(atual == NULL){ // Temos que o novo aluno tem rank minimo, então inserimos na cauda
      novoNodo->atras = fila->cauda;
      fila->cauda->defronte = novoNodo;
      fila->cauda = novoNodo;
    }else if(atual == fila->frente){ // Se o while saiu já no primeiro caso, temos que o aluno tem rank máximo
      novoNodo->defronte = fila->frente;
      fila->frente->atras = novoNodo;
      fila->frente = novoNodo;
    }else{ // Se estamos nem no fim e nem no início, inserimos no meio
      novoNodo->atras = atual->atras;
      novoNodo->defronte = atual;
      atual->atras->defronte = novoNodo;
      atual->atras = novoNodo;
    }
  }
  fila->refMovel = novoNodo;
  fila->tamLista++;
}

void inserirFilaPrioridadeSEMREFMOVEL(descritor* fila, aluno a, int* numIter){
  noDados* novoNodo = malloc(sizeof(noDados));
  novoNodo->info = a;
  novoNodo->atras = NULL;
  novoNodo->defronte = NULL;

  if(fila->frente == NULL){ // Se ela estiver vazia
    fila->frente = novoNodo;
    fila->cauda = novoNodo;
  }else{
    noDados* atual = NULL;

    // Sem referência móvel, começamos pelo início sempre
    atual = fila->frente;

    while(atual != NULL && atual->info.ranking <= a.ranking){
      atual = atual->defronte; // aumenta prioridade
      (*numIter)++;
    } // Ao sair do while, temos a posição para colocar o novo aluno

    if(atual == NULL){ // Temos que o novo aluno tem rank minimo, então inserimos na cauda
      novoNodo->atras = fila->cauda;
      fila->cauda->defronte = novoNodo;
      fila->cauda = novoNodo;
    }else if(atual == fila->frente){ // Se o while saiu já no primeiro caso, temos que o aluno tem rank máximo
      novoNodo->defronte = fila->frente;
      fila->frente->atras = novoNodo;
      fila->frente = novoNodo;
    }else{ // Se estamos nem no fim e nem no início, inserimos no meio
      novoNodo->atras = atual->atras;
      novoNodo->defronte = atual;
      atual->atras->defronte = novoNodo;
      atual->atras = novoNodo;
    }
  }
  fila->refMovel = novoNodo;
  fila->tamLista++;
}


/****** RETORNA PRÓXIMO VALOR NA FILA ********/

int buscaNaCauda(aluno *alunoEncontrado,  descritor *fila){
  if(fila->cauda == NULL || fila->frente == NULL){ return 0; }
  memcpy(alunoEncontrado, &(fila->cauda->info), fila->tamInfo);
  return 1;
}

int buscaNaFrente(aluno *alunoEncontrado,  descritor *fila){
  if(fila->cauda == NULL || fila->frente == NULL){ return 0; }
  memcpy(alunoEncontrado, &(fila->frente->info), fila->tamInfo);
  return 1;
}

/************** REMOVE UM ALUNO ***************/

int remove_(int *matriculaDoAlvo,  descritor *fila){
  if (fila == NULL || fila->frente == NULL) return 0;
  noDados *aux = fila->frente;
  noDados *anterior = NULL;
  while (aux != NULL) {
    if (aux->info.matricula == *matriculaDoAlvo) {
      // Remove o nó
      if (anterior == NULL) { // Remover da frente
        fila->frente = aux->defronte;
        if (fila->frente != NULL) fila->frente->atras = NULL;
        else fila->cauda = NULL; // Lista ficou vazia
      } else {
        anterior->defronte = aux->defronte;
        if (aux->defronte != NULL)
          aux->defronte->atras = anterior;
        else
          fila->cauda = anterior; // Removendo da cauda
      }
      if (fila->refMovel == aux) fila->refMovel = NULL;
      free(aux);
      fila->tamLista--;
      printf("Aluno encontrado e removido.\n");
      return 1;
    }
    anterior = aux;
    aux = aux->defronte;
  }
  printf("Aluno não encontrado.\n");
  return 0;
}

/************** LE O ARQUIVO .CSV *************/

void lerCSV(const char *nomeArquivo, descritor *fila) {
  // Abrindo o arquivo
    FILE *arquivo = fopen(nomeArquivo, "r");
    if (!arquivo) {
        perror("Erro ao abrir o arquivo");
        return;
    }

    char linha[128]; // Recebe a linha e muda a cada while
    while(fgets(linha, sizeof(linha), arquivo)){
      linha[strcspn(linha, "\n")] = 0;  // Remove o '\n' no fim de cada linha
      aluno a;
      char *valor;

      valor = strtok(linha, ","); // Essa função pega a linha e "corta" ela onde tem vírgula, já pondo o \0
      if (valor) strncpy(a.nome, valor, sizeof(a.nome));
      
      valor = strtok(NULL, ","); // Ao usar NULL, ele usa o valor que tá na variável valor
      if(valor) a.matricula = atoi(valor); // atoi = ASCII to Integer
      
      valor = strtok(NULL, ",");
      if(valor) a.ranking = atoi(valor);

      valor = strtok(NULL, ",");
      if(valor) strncpy(a.curso, valor, sizeof(a.curso));

      inserirFilaPrioridadeREFMOVEL(fila, a, NULL);
    }
    fclose(arquivo);
  }

/************** EMBARALHANDO UMA FILA *************/

// Auxiliar para a função embaralhaFila()
void embaralhaVetor(aluno* vet, int n) {
    for (int i = n - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        aluno temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }
}

// Auxiliar para a função embaralhaFila()
void inserirNoFim(descritor* fila, aluno a) {
    noDados* novo = malloc(sizeof(noDados));
    if (!novo) return;

    novo->info = a;
    novo->atras = fila->cauda;
    novo->defronte = NULL;

    if (fila->cauda != NULL)
        fila->cauda->defronte = novo;
    else
        fila->frente = novo;

    fila->cauda = novo;
    fila->tamLista++;
}

void embaralhaFila(descritor* fila) {
    if (fila == NULL || fila->tamLista == 0) return;

    int n = fila->tamLista;
    aluno* vet = malloc(sizeof(aluno) * n);

    // Copia os alunos da fila para um vetor
    noDados* atual = fila->frente;
    for (int i = 0; i < n && atual != NULL; i++) {
        vet[i] = atual->info;
        atual = atual->defronte;
    }

    // Embaralha o vetor
    srand(time(NULL));
    embaralhaVetor(vet, n);

    // Reinicia a fila e reinsere embaralhado
    reinicia(fila);
    for (int i = 0; i < n; i++) {
        inserirNoFim(fila, vet[i]); 
    }

    free(vet);
}

/************** GERANDO UMA BASE *************/

void geraBase(int usarRefMovel, int valorBase, int *numIter, descritor *fila, descritor *novafila){
  /* O que faz cada parâmetro
  usarRefMovel: se 1, inserir usando refMovel, se 0, inserir sem usar refMovel
  valorBase: quantos elementos queremos na nova fila
  numIter: número de iterações na fila
  fila: fila que tem os 10000 
  novaFila: fila gerada a partir da base
  */
  noDados *aux = fila->frente;
  if(usarRefMovel){
    for(int i = 1; i <= valorBase; i++) {
      inserirFilaPrioridadeREFMOVEL(novafila, aux->info, numIter);
      aux = aux->defronte;
    }
  }else{
    for(int i = 1; i <= valorBase; i++) {
      inserirFilaPrioridadeSEMREFMOVEL(novafila, aux->info, numIter);
      aux = aux->defronte;
    }
  }

}

/************** TAMANHO DA FILA *************/

int tamanhoDaFila(struct descritor *p){
  return p->tamLista;
}

/************** REINICIA A FILA *************/

int reinicia(struct descritor *p)
{   int status=0;
    noDados *aux=NULL;

    if(p->frente != NULL && p->cauda != NULL) 
    {  
        aux = p->cauda->defronte;
        while(aux != NULL) 
        {
            free(p->cauda);
            p->cauda = aux;
            aux = aux->defronte;
        }
        
        free(p->cauda);
        p->frente = NULL;
        p->cauda = NULL;
        p->tamLista = 0;
        p->refMovel = NULL;
        status=1; 
    }
    return status;	
}

/************** TESTA SE VAZIA *************/

int testaVazia(struct descritor *p){
  if(p->cauda == NULL && p->frente == NULL){
    return 1;
  }else{
    return 0;
  }
}

/************* DESTROI A FILA **************/

descritor * destroi(descritor *p)
{
    reinicia(p);
    free(p);
    return NULL; // aterra o ponteiro externo, declarado na aplicação
}

/************** PRINTA A FILA **************/

void printarFila(descritor* fila){
  if(tamanhoDaFila(fila) == 0){
    printf("A fila esta vazia!\n");
    return;
  }
  
  int contador = 1;
  noDados *nodo = fila->frente;
  while(nodo != NULL){
    aluno a = nodo->info;
    printf("| Pessoa numero %5d", contador);
    printf(" | Nome: %-10s ", a.nome);
    printf(" | Matricula: %6d ", a.matricula);
    printf(" | Ranking: %3d ", a.ranking);
    printf(" | Curso: %-15s |\n", a.curso);
    contador++;
    nodo = nodo->defronte;
  }
}
