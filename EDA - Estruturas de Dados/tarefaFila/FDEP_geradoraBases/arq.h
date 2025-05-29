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
void lerCSV(const char *nomeArquivo, descritor *fila);
void geraBase(int usarRefMovel, int valorBase, int *numIter, descritor *fila, descritor *novafila);
void printarFila(descritor* fila);
//======================FILA==========================
descritor * criarFila();
void inserirFilaPrioridadeREFMOVEL(descritor* fila, aluno a, int* numIter);
void inserirFilaPrioridadeSEMREFMOVEL(descritor* fila, aluno a, int* numIter);
void embaralhaFila(descritor* fila);
int tamanhoDaFila(descritor *fila);
int reinicia(descritor *fila);
descritor * destroi(descritor *fila);
int buscaNaCauda(aluno *alunoEncontrado,  descritor *fila);
int buscaNaFrente(aluno *alunoEncontrado,  descritor *fila);
int remove_(int *matriculaDoAlvo,  descritor *fila); // usar refMovel somente se a prioridade for pela matricula
int testaVazia( descritor *fila);
