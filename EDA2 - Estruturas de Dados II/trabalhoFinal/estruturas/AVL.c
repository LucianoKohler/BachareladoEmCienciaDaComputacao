#include "../header.h"

ArvoreAVL *criarAVL()
{
    ArvoreAVL *arvoreAVL = malloc(sizeof(ArvoreAVL));
    arvoreAVL->raiz = NULL;

    return arvoreAVL;
}

int vaziaAVL(ArvoreAVL *arvoreAVL)
{
    return arvoreAVL->raiz == NULL;
}

NoAVL *criarNoAVL(NoAVL *pai, int valor)
{
    NoAVL *noAVL = malloc(sizeof(NoAVL));
    noAVL->valor = valor;
    noAVL->pai = pai;
    noAVL->esquerda = NULL;
    noAVL->direita = NULL;
    noAVL->altura = 1;
    return noAVL;
}

int alturaAVL(NoAVL *noAVL, ll *contador)
{

    if (noAVL == NULL)
    {
        return 0;
    }
    return noAVL->altura;
}

int fbAVL(NoAVL *noAVL, ll *contador)
{

    if (noAVL == NULL) return 0;
    return alturaAVL(noAVL->esquerda, contador) - alturaAVL(noAVL->direita, contador);
}

NoAVL *adicionarNoAVL(NoAVL *noAVL, int valor, ll *contador)
{
    (*contador)++;

    if (valor > noAVL->valor)
    {

        if (noAVL->direita == NULL)
        {
            NoAVL *novo = criarNoAVL(noAVL, valor);
            noAVL->direita = novo;
            return novo;
        }
        else
        {
            return adicionarNoAVL(noAVL->direita, valor, contador);
        }
    }
    else
    {

        if (noAVL->esquerda == NULL)
        {
            NoAVL *novo = criarNoAVL(noAVL, valor);
            noAVL->esquerda = novo;
            return novo;
        }
        else
        {
            return adicionarNoAVL(noAVL->esquerda, valor, contador);
        }
    }
}

NoAVL *adicionarAVL(ArvoreAVL *arvoreAVL, int valor, ll *contador)
{

    if (vaziaAVL(arvoreAVL))
    {
        arvoreAVL->raiz = criarNoAVL(NULL, valor);
        return arvoreAVL->raiz;
    }
    else
    {
        NoAVL *noAVL = adicionarNoAVL(arvoreAVL->raiz, valor, contador);
        balanceamentoAVL(arvoreAVL, noAVL->pai, contador);
        return noAVL;
    }
}

NoAVL *localizarAVL(NoAVL *noAVL, int valor, ll *contador)
{

    if (noAVL == NULL) return NULL;

    if (noAVL->valor == valor)
    {
        return noAVL;
    }
    else
    {

        if (valor < noAVL->valor)
        {

            if (noAVL->esquerda != NULL)
            {
                return localizarAVL(noAVL->esquerda, valor, contador);
            }
        }
        else
        {

            if (noAVL->direita != NULL)
            {
                return localizarAVL(noAVL->direita, valor, contador);
            }
        }
    }

    return NULL;
}

static void atualizaAltura(NoAVL *no, ll *contador) {
    if (!no) return;

    int he = alturaAVL(no->esquerda, contador);
    int hd = alturaAVL(no->direita, contador);
    no->altura = (he > hd) ? he+1 : hd+1;
}

NoAVL *rseAVL(ArvoreAVL *arvoreAVL, NoAVL *noAVL, ll *contador)
{
    NoAVL *pai = noAVL->pai;
    NoAVL *direita = noAVL->direita;

    noAVL->direita = direita->esquerda;
    if (direita->esquerda != NULL)
    {
        direita->esquerda->pai = noAVL;
    }

    direita->esquerda = noAVL;
    noAVL->pai = direita;

    direita->pai = pai;
    if (pai == NULL)
    {
        arvoreAVL->raiz = direita;
    }
    else
    {

        if (pai->esquerda == noAVL)
            pai->esquerda = direita;
        else
            pai->direita = direita;
    }

    atualizaAltura(noAVL, contador);
    atualizaAltura(direita, contador);

    return direita;
}

NoAVL *rsdAVL(ArvoreAVL *arvoreAVL, NoAVL *noAVL, ll *contador)
{
    NoAVL *pai = noAVL->pai;
    NoAVL *esquerda = noAVL->esquerda;

    noAVL->esquerda = esquerda->direita;
    if (esquerda->direita != NULL)
    {
        esquerda->direita->pai = noAVL;
    }

    esquerda->direita = noAVL;
    noAVL->pai = esquerda;

    esquerda->pai = pai;
    if (pai == NULL)
    {
        arvoreAVL->raiz = esquerda;
    }
    else
    {

        if (pai->esquerda == noAVL)
            pai->esquerda = esquerda;
        else
            pai->direita = esquerda;
    }

    atualizaAltura(noAVL, contador);
    atualizaAltura(esquerda, contador);

    return esquerda;
}

NoAVL *rdeAVL(ArvoreAVL *arvoreAVL, NoAVL *noAVL, ll *contador)
{

    noAVL->direita = rsdAVL(arvoreAVL, noAVL->direita, contador);
    if (noAVL->direita != NULL) noAVL->direita->pai = noAVL;
    return rseAVL(arvoreAVL, noAVL, contador);
}

NoAVL *rddAVL(ArvoreAVL *arvoreAVL, NoAVL *noAVL, ll *contador)
{

    noAVL->esquerda = rseAVL(arvoreAVL, noAVL->esquerda, contador);
    if (noAVL->esquerda != NULL) noAVL->esquerda->pai = noAVL;
    return rsdAVL(arvoreAVL, noAVL, contador);
}

void balanceamentoAVL(ArvoreAVL *arvore, NoAVL *no, ll *contador)
{

    while (no != NULL)
    {
        atualizaAltura(no, contador);
        int fator = fbAVL(no, contador);
        (*contador)++;

        if (fator > 1)
        { 
            (*contador)++;

            if (fbAVL(no->esquerda, contador) >= 0)
            {
                rsdAVL(arvore, no, contador);
            }
            else
            {
                rddAVL(arvore, no, contador);
            }
        }
        else if (fator < -1)
        { 
            (*contador)++;

            if (fbAVL(no->direita, contador) <= 0)
            {
                rseAVL(arvore, no, contador);
            }
            else
            {
                rdeAVL(arvore, no, contador);
            }
        }
        no = no->pai;

    }
}

NoAVL *maiorDireitaAVL(NoAVL *noAVL, ll *contador)
{
    if (noAVL == NULL) return NULL;
    NoAVL *ret = noAVL->direita;

    while (ret != NULL && ret->esquerda != NULL)
    {
        ret = ret->esquerda;

    }
    return ret;
}

NoAVL *maiorEsquerdaAVL(NoAVL *noAVL, ll *contador)
{
    if (noAVL == NULL) return NULL;
    NoAVL *ret = noAVL->esquerda;

    while (ret != NULL && ret->direita != NULL)
    {
        ret = ret->direita;

    }
    return ret;
}

void removerNoAVL(NoAVL *rem)
{
    if (rem != NULL)
    {
        free(rem);
    }
}

NoAVL *removerAVL(ArvoreAVL *arvoreAVL, int valor, ll *contador)
{

    if (arvoreAVL == NULL || arvoreAVL->raiz == NULL)
    {
        return NULL;
    }

    NoAVL *rem = localizarAVL(arvoreAVL->raiz, valor, contador);

    if (rem == NULL)
    {

        return arvoreAVL->raiz;
    }

    NoAVL *nodeParaBalancear = NULL; 

    if (rem->esquerda != NULL && rem->direita != NULL)
    {
        (*contador)++;

        NoAVL *sucessor = rem->direita;

        while (sucessor->esquerda != NULL)
        {
            sucessor = sucessor->esquerda;

        }

        rem->valor = sucessor->valor;

        rem = sucessor;

    }

    NoAVL *sub = (rem->esquerda != NULL) ? rem->esquerda : rem->direita;
    NoAVL *pai = rem->pai;

    if (sub != NULL)
    {

        sub->pai = pai;
    }

    if (pai == NULL)
    {

        arvoreAVL->raiz = sub;
    }
    else
    {
        if (pai->esquerda == rem)
        {
            pai->esquerda = sub;
        }
        else
        {
            pai->direita = sub;
        }
    }

    nodeParaBalancear = pai;

    removerNoAVL(rem);

    balanceamentoAVL(arvoreAVL, nodeParaBalancear, contador);

    return arvoreAVL->raiz;
}