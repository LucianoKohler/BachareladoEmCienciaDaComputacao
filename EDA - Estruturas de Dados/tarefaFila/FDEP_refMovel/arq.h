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



//======================APLICACAO=====================
int compara(aluno * inf1, aluno *inf2); 
void lerCSV(const char *nomeArquivo, descritor *fila);
void printarFila(descritor* fila);
//======================FILA==========================
descritor * criarFila();
void inserirFilaPrioridade(descritor* fila, aluno a); // usar refMovel
int tamanhoDaFila(descritor *fila);
int reinicia( descritor *fila);
descritor * destroi( descritor *fila);
int buscaNaCauda(aluno *alunoEncontrado,  descritor *fila);
int buscaNaFrente(aluno *alunoEncontrado,  descritor *fila);
int remove_(int *matriculaDoAlvo,  descritor *fila); // usar refMovel somente se a prioridade for pela matricula
int testaVazia( descritor *fila);
int inverte(descritor *fila);

