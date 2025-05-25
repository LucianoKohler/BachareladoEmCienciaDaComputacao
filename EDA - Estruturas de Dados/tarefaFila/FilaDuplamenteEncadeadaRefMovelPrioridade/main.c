#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{ 
  char nome[30];
  int matricula; // Int pois durante o processo, matricula será usado como prioridade
  int ranking; // Prioridade
  char curso[30];
} aluno;

typedef struct noDados{
  struct noDados *atras;
  aluno info;
  struct noDados *defronte;
}noDados;

typedef struct descritor{
  int tamLista;
  int tamInfo; // sizeof(aluno)

  struct noDados *frente; // maior prioridade
  struct noDados *refMovel;
  struct noDados *cauda; // menor prioridade
  
}descritor;

descritor* criarFila(){
  descritor* d = malloc(sizeof(descritor));  
  d->cauda = NULL;
  d->frente = NULL;
  d->refMovel = NULL;
  d->tamLista = 0;
  d->tamInfo = sizeof(aluno);
  return d;
}

void inserirFilaPrioridade(descritor* fila, aluno a){
  noDados* novoNodo = malloc(sizeof(noDados));
  novoNodo->info = a;
  novoNodo->atras = NULL;
  novoNodo->defronte = NULL;

  if(fila->frente == NULL){ // Se ela estiver vazia
    fila->frente = novoNodo;
    fila->cauda = novoNodo;
  }else{
    noDados* atual = fila->frente; // maior prioridade
    while(atual != NULL && atual->info.ranking <= a.ranking){
      atual = atual->defronte; // aumenta prioridade
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
}

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

      inserirFilaPrioridade(fila, a);
    }
    fclose(arquivo);
  }

void printarFila(descritor* fila){
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

int main(){
  descritor* fila = criarFila();
  lerCSV("dataset_v1.csv", fila);
  printarFila(fila);



  return 0;
}