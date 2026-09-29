#include "../header.h"

ArvoreRB *criarRB()
{
    ArvoreRB *arvoreRB = malloc(sizeof(ArvoreRB));
    arvoreRB->raiz = NULL;

    arvoreRB->nulo = malloc(sizeof(NoRB));
    arvoreRB->nulo->esquerda = arvoreRB->nulo;
    arvoreRB->nulo->direita = arvoreRB->nulo;
    arvoreRB->nulo->pai = arvoreRB->nulo;
    arvoreRB->nulo->cor = PRETO;
    arvoreRB->nulo->valor = 0;

    return arvoreRB;
}

int vaziaRB(ArvoreRB *arvoreRB)
{
    return arvoreRB->raiz == NULL || arvoreRB->raiz == arvoreRB->nulo;
}

NoRB *criarNoRB(ArvoreRB *arvoreRB, NoRB *pai, int valor)
{
    NoRB *noRB = malloc(sizeof(NoRB));
    noRB->pai = pai;
    noRB->valor = valor;
    noRB->direita = arvoreRB->nulo;
    noRB->esquerda = arvoreRB->nulo;
    noRB->cor = VERMELHO;
    return noRB;
}

NoRB *adicionarNoRB(ArvoreRB *arvoreRB, NoRB *noRB, int valor, ll *contador)
{
    (*contador)++; 

    if (valor > noRB->valor)
    {
        if (noRB->direita == arvoreRB->nulo)
        {
            noRB->direita = criarNoRB(arvoreRB, noRB, valor);
            noRB->direita->cor = VERMELHO;
            return noRB->direita;
        }
        else
        {
            return adicionarNoRB(arvoreRB, noRB->direita, valor, contador);
        }
    }
    else
    {
        if (noRB->esquerda == arvoreRB->nulo)
        {
            noRB->esquerda = criarNoRB(arvoreRB, noRB, valor);
            noRB->esquerda->cor = VERMELHO;
            return noRB->esquerda;
        }
        else
        {
            return adicionarNoRB(arvoreRB, noRB->esquerda, valor, contador);
        }
    }
}

NoRB *adicionarRB(ArvoreRB *arvoreRB, int valor, ll *contador)
{
    if (vaziaRB(arvoreRB))
    {
        arvoreRB->raiz = criarNoRB(arvoreRB, arvoreRB->nulo, valor);
        arvoreRB->raiz->cor = PRETO;
        return arvoreRB->raiz;
    }
    else
    {
        NoRB *noRB = adicionarNoRB(arvoreRB, arvoreRB->raiz, valor, contador);
        balancearAdRB(arvoreRB, noRB, contador);
        return noRB;
    }
}

NoRB *localizarRB(ArvoreRB *arvoreRB, int valor, ll *contador)
{
    if (!vaziaRB(arvoreRB))
    {
        NoRB *noRB = arvoreRB->raiz;
        while (noRB != arvoreRB->nulo)
        {
            (*contador)++; 
            if (noRB->valor == valor)
            {
                return noRB;
            }
            else
            {
                noRB = valor < noRB->valor ? noRB->esquerda : noRB->direita;
            }
        }
    }

    return NULL;
}

void balancearAdRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador)
{
    while (noRB->pai->cor == VERMELHO)
    {
        (*contador)++; 

        if (noRB->pai == noRB->pai->pai->esquerda)
        {
            NoRB *tio = noRB->pai->pai->direita;
            if (tio->cor == VERMELHO)
            {

                (*contador)++; 
                tio->cor = PRETO;
                noRB->pai->cor = PRETO;
                noRB->pai->pai->cor = VERMELHO;
                noRB = noRB->pai->pai;
            }
            else
            {
                if (noRB == noRB->pai->direita)
                {

                    (*contador)++; 
                    noRB = noRB->pai;
                    rotacionarEsquerdaRB(arvoreRB, noRB, contador); 
                }
                else
                {

                    (*contador)++; 
                    noRB->pai->cor = PRETO;
                    noRB->pai->pai->cor = VERMELHO;
                    rotacionarDireitaRB(arvoreRB, noRB->pai->pai, contador); 
                }
            }
        }
        else
        {
            NoRB *tio = noRB->pai->pai->esquerda;
            if (tio->cor == VERMELHO)
            {

                (*contador)++; 
                tio->cor = PRETO;
                noRB->pai->cor = PRETO;
                noRB->pai->pai->cor = VERMELHO;
                noRB = noRB->pai->pai;
            }
            else
            {
                if (noRB == noRB->pai->esquerda)
                {

                    (*contador)++; 
                    noRB = noRB->pai;
                    rotacionarDireitaRB(arvoreRB, noRB, contador); 
                }
                else
                {

                    (*contador)++; 
                    noRB->pai->cor = PRETO;
                    noRB->pai->pai->cor = VERMELHO;
                    rotacionarEsquerdaRB(arvoreRB, noRB->pai->pai, contador); 
                }
            }
        }
    }
    arvoreRB->raiz->cor = PRETO;
}

void rotacionarEsquerdaRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador)
{
    (*contador)++; 
    NoRB *direita = noRB->direita;
    noRB->direita = direita->esquerda;
    if (direita->esquerda != arvoreRB->nulo)
    {
        direita->esquerda->pai = noRB;
    }

    direita->pai = noRB->pai;
    if (noRB->pai == arvoreRB->nulo)
    {
        arvoreRB->raiz = direita;
    }
    else if (noRB == noRB->pai->esquerda)
    {
        noRB->pai->esquerda = direita;
    }
    else
    {
        noRB->pai->direita = direita;
    }

    direita->esquerda = noRB;
    noRB->pai = direita;
}

void rotacionarDireitaRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador)
{
    (*contador)++; 
    NoRB *esquerda = noRB->esquerda;
    noRB->esquerda = esquerda->direita;
    if (esquerda->direita != arvoreRB->nulo)
    {
        esquerda->direita->pai = noRB;
    }

    esquerda->pai = noRB->pai;
    if (noRB->pai == arvoreRB->nulo)
    {
        arvoreRB->raiz = esquerda;
    }
    else if (noRB == noRB->pai->esquerda)
    {
        noRB->pai->esquerda = esquerda;
    }
    else
    {
        noRB->pai->direita = esquerda;
    }

    esquerda->direita = noRB;
    noRB->pai = esquerda;
}

void removerNoRB(NoRB *noRB)
{
    if (noRB == NULL) return;
    noRB->direita = NULL;
    noRB->esquerda = NULL;
    noRB->pai = NULL;
    free(noRB);
}

NoRB *sucessorDireitaRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador)
{
    NoRB *ret = noRB->direita;
    while (ret->esquerda != arvoreRB->nulo)
    {
        ret = ret->esquerda;
    }
    return ret;
}

NoRB *sucessorEsquerdaRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador)
{
    NoRB *ret = noRB->esquerda;
    while (ret->direita != arvoreRB->nulo)
    {
        ret = ret->direita;
    }
    return ret;
}

void transplanteRB(ArvoreRB *arvoreRB, NoRB *u, NoRB *v, ll *contador)
{
    (*contador)++; 
    if (u->pai == arvoreRB->nulo)
    {
        arvoreRB->raiz = v;
    }
    else if (u == u->pai->esquerda)
    {
        u->pai->esquerda = v;
    }
    else
    {
        u->pai->direita = v;
    }
    v->pai = u->pai;
}

NoRB *removerRB(ArvoreRB *arvoreRB, int valor, ll *contador)
{
    NoRB *rem = localizarRB(arvoreRB, valor, contador);
    if (rem == NULL)
        return NULL;

    NoRB *suc = rem;
    COR corOriginal = suc->cor;
    NoRB *sub = arvoreRB->nulo;

    if (rem->esquerda == arvoreRB->nulo)
    {
        sub = rem->direita;
        transplanteRB(arvoreRB, rem, rem->direita, contador);
    }
    else if (rem->direita == arvoreRB->nulo)
    {
        sub = rem->esquerda;
        transplanteRB(arvoreRB, rem, rem->esquerda, contador);
    }
    else
    {
        suc = sucessorDireitaRB(arvoreRB, rem, NULL);
        corOriginal = suc->cor;
        sub = suc->direita;
        if (suc->pai == rem)
        {
            sub->pai = suc;
        }
        else
        {
            transplanteRB(arvoreRB, suc, suc->direita, contador);
            suc->direita = rem->direita;
            suc->direita->pai = suc;
        }
        transplanteRB(arvoreRB, rem, suc, contador);
        suc->esquerda = rem->esquerda;
        suc->esquerda->pai = suc;
        suc->cor = rem->cor;
    }

    if (corOriginal == PRETO)
    {
        balancearRemRB(arvoreRB, sub, contador);
    }

    removerNoRB(rem);
    return suc;
}

void balancearRemRB(ArvoreRB *arvoreRB, NoRB *noRB, ll *contador)
{
    while (noRB != arvoreRB->raiz && noRB->cor == PRETO)
    {
        (*contador)++; 

        if (noRB == noRB->pai->esquerda)
        {
            NoRB *irmao = noRB->pai->direita;

            if (irmao->cor == VERMELHO)
            {
                (*contador)++; 
                irmao->cor = PRETO;
                noRB->pai->cor = VERMELHO;
                rotacionarEsquerdaRB(arvoreRB, noRB->pai, contador);
                irmao = noRB->pai->direita;
            }

            if (irmao->esquerda->cor == PRETO && irmao->direita->cor == PRETO)
            {
                (*contador)++; 
                irmao->cor = VERMELHO;
                noRB = noRB->pai;
            }
            else
            {
                if (irmao->direita->cor == PRETO)
                {
                    (*contador)++; 
                    irmao->esquerda->cor = PRETO;
                    irmao->cor = VERMELHO;
                    rotacionarDireitaRB(arvoreRB, irmao, contador);
                    irmao = noRB->pai->direita;
                }

                (*contador)++; 
                irmao->cor = noRB->pai->cor;
                noRB->pai->cor = PRETO;
                irmao->direita->cor = PRETO;
                rotacionarEsquerdaRB(arvoreRB, noRB->pai, contador);
                noRB = arvoreRB->raiz;
            }
        }
        else
        {
            NoRB *irmao = noRB->pai->esquerda;

            if (irmao->cor == VERMELHO)
            {
                (*contador)++; 
                irmao->cor = PRETO;
                noRB->pai->cor = VERMELHO;
                rotacionarDireitaRB(arvoreRB, noRB->pai, contador);
                irmao = noRB->pai->esquerda;
            }

            if (irmao->esquerda->cor == PRETO && irmao->direita->cor == PRETO)
            {
                (*contador)++; 
                irmao->cor = VERMELHO;
                noRB = noRB->pai;
            }
            else
            {
                if (irmao->esquerda->cor == PRETO)
                {
                    (*contador)++; 
                    irmao->direita->cor = PRETO;
                    irmao->cor = VERMELHO;
                    rotacionarEsquerdaRB(arvoreRB, irmao, contador);
                    irmao = noRB->pai->esquerda;
                }

                (*contador)++; 
                irmao->cor = noRB->pai->cor;
                noRB->pai->cor = PRETO;
                irmao->esquerda->cor = PRETO;
                rotacionarDireitaRB(arvoreRB, noRB->pai, contador);
                noRB = arvoreRB->raiz;
            }
        }
    }
    noRB->cor = PRETO;
}