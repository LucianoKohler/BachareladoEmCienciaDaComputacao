#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define ll long long

#ifndef HEADER_H
#define HEADER_H

typedef struct estatisticasConjunto{
    ll iterAddAVL;
    ll iterAddB1;
    ll iterAddB5;
    ll iterAddB10;
    ll iterAddRB;
    ll iterRemovAVL;
    ll iterRemovB1;
    ll iterRemovB5;
    ll iterRemovB10;
    ll iterRemovRB;
} estatisticasConjunto;

// AVL
typedef struct noAVL {
    struct noAVL* pai;
    struct noAVL* esquerda;
    struct noAVL* direita;
    int valor;
    int altura;
} NoAVL;

typedef struct arvoreAVL {
    struct noAVL* raiz;
} ArvoreAVL;

// Red-Black
typedef enum coloracao { VERMELHO, PRETO } COR;

typedef struct noRB {
    struct noRB* pai;
    struct noRB* esquerda;
    struct noRB* direita;
    COR cor;
    int valor;
} NoRB;

typedef struct arvoreRB {
    struct noRB* raiz;
    struct noRB* nulo; 
} ArvoreRB;

// B
typedef struct noB {
int total;
int* valores;
struct noB** filhos;
struct noB* pai;
} NoB;

typedef struct arvoreB {
NoB* raiz;
int ordem;
} ArvoreB;

// Declarações
// Red-Black
ArvoreRB* criarRB();
int vaziaRB(ArvoreRB *arvoreRB);
NoRB *criarNoRB(ArvoreRB *arvoreRB, NoRB *pai, int valor);
NoRB* adicionarNoRB(ArvoreRB* arvoreRB, NoRB* noRB, int valor, ll *contador);
NoRB* adicionarRB(ArvoreRB *arvoreRB, int valor, ll *contador);
NoRB* localizarRB(ArvoreRB* arvoreRB, int valor, ll *contador);
void balancearAdRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador);
void rotacionarEsquerdaRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador);
void rotacionarDireitaRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador);
void removerNoRB(NoRB *noRB);
NoRB *sucessorDireitaRB(ArvoreRB *arvoreRB,NoRB *noRB, ll *contador);
NoRB *sucessorEsquerdaRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador);
void transplanteRB(ArvoreRB *arvoreRB, NoRB *u, NoRB *v, ll *contador);
void balancearRemRB( ArvoreRB *arvoreRB, NoRB *noRB, ll *contador);
NoRB *removerRB(ArvoreRB *arvoreRB, int valor, ll *contador);

// AVL
ArvoreAVL* criarAVL();
int vaziaAVL(ArvoreAVL* arvoreAVL);
NoAVL *adicionarAVL(ArvoreAVL *arvoreAVL, int valor, ll *contador);
NoAVL* adicionarNoAVL(NoAVL* noavlNoAVL, int valor, ll *contador);
NoAVL *criarNoAVL(NoAVL *pai, int valor);
NoAVL* localizarAVL(NoAVL* noavlNoAVL, int valor, ll *contador);
void balanceamentoAVL(ArvoreAVL *arvore, NoAVL *no, ll *contador);
int alturaAVL(NoAVL *noAVL, ll *contador);
int fbAVL(NoAVL *noAVL, ll *contador);
NoAVL *rseAVL(ArvoreAVL *arvoreAVL, NoAVL *noAVL, ll *contador);
NoAVL *rsdAVL(ArvoreAVL *arvoreAVL, NoAVL *noAVL, ll *contador);
NoAVL *rdeAVL(ArvoreAVL *arvoreAVL, NoAVL *noAVL, ll *contador);
NoAVL *rddAVL(ArvoreAVL *arvoreAVL, NoAVL *noAVL, ll *contador);
void removerNoAVL( NoAVL *rem );
NoAVL *removerAVL( ArvoreAVL *arvoreAVL, int valor, ll *contador);
NoAVL *maiorEsquerdaAVL( NoAVL *noAVL, ll *contador);
NoAVL *maiorDireitaAVL( NoAVL *noAVL, ll *contador);

// B
ArvoreB *criarB(int ordem);
NoB *criaNoB(ArvoreB *arvore);
int localizaValorB(ArvoreB *arvore, NoB* raiz, int valor, ll *contador);
int pesquisaBinariaB(NoB *no, int valor, ll *contador);
NoB *localizaNoB(ArvoreB *arvore, NoB *raiz, int valor, ll *contador);
void adicionaValorNoB(NoB *no, NoB *direita, int valor, ll *contador);
int transbordoB(ArvoreB *arvore, NoB *no);
NoB *divideNoB(ArvoreB *arvore, NoB *no, ll *contador);
void adicionaValorB(ArvoreB *arvore, int valor, ll *contador);
void adicionaValorRecursivoB(ArvoreB *arvore, NoB *no, NoB *novo, int valor, ll *contador);
int remocaoValorB(ArvoreB *arvore, NoB *raiz, int valor, ll *contador);
int sucessorValorB(ArvoreB *arvore, NoB *no, int index, ll *contador);
NoB *sucessorNoB(ArvoreB *arvore, NoB *no, int index, ll *contador);
int antecessorValorB(ArvoreB *arvore, NoB *no, int index, ll *contador);
NoB *antecessorNoB(ArvoreB *arvore, NoB *no, int index, ll *contador);
NoB *irmaoMaior(NoB *no, int index, ll *contador);
void merge(NoB *resultado, NoB *excluido, int valor, ll *contador);
void mergeEspelhado(NoB *resultado, NoB *excluido, int valorPai, ll *contador);
void redistribuicaoB(ArvoreB *arvore, NoB *no, int indice, ll *contador);

#endif
