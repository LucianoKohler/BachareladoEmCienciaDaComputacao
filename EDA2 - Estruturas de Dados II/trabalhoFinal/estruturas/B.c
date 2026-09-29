#include "../header.h"

ArvoreB *criarB(int ordem)
{
    ArvoreB *a = malloc(sizeof(ArvoreB));
    a->ordem = ordem;
    a->raiz = criaNoB(a);
    a->raiz->pai = NULL;
    return a;
}

NoB *criaNoB(ArvoreB *arvore)
{
    int max = arvore->ordem * 2;          
    NoB *no = malloc(sizeof(NoB));
    no->pai = NULL;

    no->valores = malloc(sizeof(int) * (max + 1));
    no->filhos = malloc(sizeof(NoB *) * (max + 2));
    no->total = 0;
    for (int i = 0; i < max + 2; i++)
        no->filhos[i] = NULL;
    return no;
}

void percorreArvoreB(NoB *no, void (*visita)(int valor))
{
    if (no != NULL)
    {
        for (int i = 0; i < no->total; i++)
        {
            percorreArvoreB(no->filhos[i], visita);
            visita(no->valores[i]);
        }
        percorreArvoreB(no->filhos[no->total], visita);
    }
}

int localizaValorB(ArvoreB *arvore, NoB *raiz, int valor, ll *contador)
{
    NoB *no = raiz;

    while (no != NULL)
    {
        int i = pesquisaBinariaB(no, valor, contador);

        if (i < no->total && no->valores[i] == valor)
        {
            return 1; 
        }
        else
        {
            no = no->filhos[i];
        }

    }
    return 0; 
}

int pesquisaBinariaB(NoB *no, int valor, ll *contador)
{
    (*contador)++;
    int inicio = 0, fim = no->total - 1, meio;

    while (inicio <= fim)
    {
        meio = (inicio + fim) / 2;

        if (no->valores[meio] == valor)
        {
            return meio; 
        }
        else if (no->valores[meio] > valor)
        {

            fim = meio - 1;
        }
        else
        {

            inicio = meio + 1;
        }

    }
    return inicio; 
}

NoB *localizaNoB(ArvoreB *arvore, NoB *raiz, int valor, ll *contador)
{
    NoB *no = raiz;

    while (no != NULL)
    {
        (*contador)++;
        int i = pesquisaBinariaB(no, valor, contador);

        if (no->filhos[i] == NULL)
            return no;
        else
            no = no->filhos[i];

    }
    return NULL; 
}

void adicionaValorNoB(NoB *no, NoB *direita, int valor, ll *contador)
{
    int i = pesquisaBinariaB(no, valor, contador);

    for (int j = no->total - 1; j >= i; j--)
    {
        no->valores[j + 1] = no->valores[j];
        no->filhos[j + 2] = no->filhos[j + 1];

    }

    no->valores[i] = valor;
    no->filhos[i + 1] = direita;
    if (direita != NULL)
        direita->pai = no;
    no->total++;
}

int transbordoB(ArvoreB *arvore, NoB *no)
{
    return no->total > arvore->ordem * 2;
}

NoB *divideNoB(ArvoreB *arvore, NoB *no, ll *contador)
{
    (*contador)++;
    int t = arvore->ordem;
    int old_total = no->total;
    int meio = t; 
    NoB *novo = criaNoB(arvore);
    novo->pai = no->pai;

    int k = 0;
    for (int i = meio + 1; i < old_total; i++)
    {
        novo->valores[k] = no->valores[i];
        novo->total++;

        k++;
    }

    int fk = 0;
    for (int i = meio + 1; i <= old_total; i++)
    {
        novo->filhos[fk] = no->filhos[i];
        if (novo->filhos[fk] != NULL)
            novo->filhos[fk]->pai = novo;

        fk++;
    }

    no->total = meio;

    return novo;
}

void adicionaValorB(ArvoreB *arvore, int valor, ll *contador)
{
    if (arvore == NULL) return;
    NoB *no = localizaNoB(arvore, arvore->raiz, valor, contador);
    if (no == NULL)
    {

        no = arvore->raiz;
    }
    adicionaValorRecursivoB(arvore, no, NULL, valor, contador);
}

void adicionaValorRecursivoB(ArvoreB *arvore, NoB *no, NoB *novo, int valor, ll *contador)
{

    if (no == NULL) return;

    if (novo != NULL)
    {

        adicionaValorNoB(no, novo, valor, contador);
    }
    else
    {

        adicionaValorNoB(no, NULL, valor, contador);
    }

    if (transbordoB(arvore, no))
    {

        int promovido = no->valores[arvore->ordem];
        NoB *novoNo = divideNoB(arvore, no, contador);

        if (no->pai == NULL)
        {

            NoB *raiz = criaNoB(arvore);
            raiz->filhos[0] = no;
            no->pai = raiz;
            novoNo->pai = raiz;
            arvore->raiz = raiz;
            raiz->total = 0;
            adicionaValorNoB(raiz, novoNo, promovido, contador);
        }
        else
        {

            adicionaValorRecursivoB(arvore, no->pai, novoNo, promovido, contador);
        }
    }
}

int sucessorValorB(ArvoreB *arvore, NoB *no, int index, ll *contador)
{
    NoB *aux = no->filhos[index + 1];

    while (aux != NULL && aux->filhos[0] != NULL)
    {
        aux = aux->filhos[0]; 

    }
    return aux->valores[0]; 
}

NoB *sucessorNoB(ArvoreB *arvore, NoB *no, int index, ll *contador)
{
    NoB *aux = no->filhos[index + 1];

    while (aux != NULL && aux->filhos[0] != NULL)
    {
        aux = aux->filhos[0];

    }
    return aux; 
}

int antecessorValorB(ArvoreB *arvore, NoB *no, int index, ll *contador)
{
    NoB *aux = no->filhos[index];

    while (aux != NULL && aux->filhos[aux->total] != NULL)
    {
        aux = aux->filhos[aux->total]; 

    }
    return aux->valores[aux->total - 1]; 
}

NoB *antecessorNoB(ArvoreB *arvore, NoB *no, int index, ll *contador)
{
    NoB *aux = no->filhos[index];

    while (aux != NULL && aux->filhos[aux->total] != NULL)
    {
        aux = aux->filhos[aux->total];

    }
    return aux; 
}

NoB *irmaoMaior(NoB *no, int index, ll *contador)
{

    if (index == 0)
    {
        return no->filhos[index + 1];
    }
    else if (index == no->total)
    {

        return no->filhos[index - 1];
    }
    else
    {

        NoB *esq = no->filhos[index - 1];
        NoB *dir = no->filhos[index + 1];
        if (esq != NULL && dir != NULL)
        {
            if (esq->total >= dir->total)
                return esq;
            else
                return dir;
        }
        else if (esq != NULL)
            return esq;
        else
            return dir;
    }
}

void merge(NoB *resultado, NoB *excluido, int valorPai, ll *contador)
{

    resultado->valores[resultado->total] = valorPai;
    resultado->total++;

    for (int i = 0; i < excluido->total; i++)
    {
        resultado->valores[resultado->total] = excluido->valores[i];
        resultado->total++;

    }

    for (int i = 0; i <= excluido->total; i++)
    {

        if (excluido->filhos[i] != NULL)
        {

            int pos = resultado->total - excluido->total + i;
            resultado->filhos[pos] = excluido->filhos[i];
            resultado->filhos[pos]->pai = resultado;

        }
    }
}

void mergeEspelhado(NoB *resultado, NoB *excluido, int valorPai, ll *contador)
{

    for (int i = resultado->total - 1; i >= 0; i--)
    {
        resultado->valores[i + excluido->total + 1] = resultado->valores[i];

    }

    for (int i = 0; i < excluido->total; i++)
    {
        resultado->valores[i] = excluido->valores[i];

    }

    resultado->valores[excluido->total] = valorPai;
    resultado->total += excluido->total + 1;

    for (int i = resultado->total; i >= excluido->total + 1; i--)
    {
        resultado->filhos[i] = resultado->filhos[i - excluido->total - 1];

    }

    for (int i = 0; i <= excluido->total; i++)
    {

        if (excluido->filhos[i] != NULL)
        {
            resultado->filhos[i] = excluido->filhos[i];
            resultado->filhos[i]->pai = resultado;

        }
    }
}

int remocaoValorB(ArvoreB *arvore, NoB *no, int valor, ll *contador)
{

    if (no == NULL || arvore == NULL)
    {
        return 0; 
    }
    int indice = pesquisaBinariaB(no, valor, contador);

    if (indice < no->total && no->valores[indice] == valor)
    {
        if (no->filhos[0] == NULL)
        {

            for (int i = indice; i < no->total - 1; i++)
            {
                no->valores[i] = no->valores[i + 1];

            }
            no->total--;
            (*contador) += 2;
            if (no == arvore->raiz && no->total < arvore->ordem)
            {

                arvore->raiz = NULL;
            }
            return 1;
        }
        else
        {

            int ant = antecessorValorB(arvore, no, indice, contador);
            remocaoValorB(arvore, no->filhos[indice], ant, contador);

            int posLocalizado = pesquisaBinariaB(no, valor, contador);
            if (posLocalizado < no->total)
                no->valores[posLocalizado] = ant;
        }
    }
    else
    {
        if (no->filhos[0] == NULL)
        {

            return 0;
        }
        remocaoValorB(arvore, no->filhos[indice], valor, contador);

        return 3;
    }
    return 0;
}

void redistribuicaoB(ArvoreB *arvore, NoB *no, int indice, ll *contador)
{

    NoB *filhoAtual = no->filhos[indice];
    if (filhoAtual == NULL) return;
    if (filhoAtual->total < arvore->ordem)
    {
        NoB *irmao = irmaoMaior(no, indice, contador); 

        int valorPai;
        if (irmao == no->filhos[indice - 1])
            valorPai = no->valores[indice - 1];
        else
            valorPai = no->valores[indice];

        if (irmao != NULL && irmao->total > arvore->ordem) 
        {

            if (irmao == no->filhos[indice + 1]) 
            {
                filhoAtual->valores[filhoAtual->total] = valorPai;
                filhoAtual->total++;
                no->valores[indice] = irmao->valores[0];

                filhoAtual->filhos[filhoAtual->total] = irmao->filhos[0];

                if (irmao->filhos[0] != NULL)
                    irmao->filhos[0]->pai = filhoAtual;

                for (int i = 0; i < irmao->total - 1; i++)
                {

                    irmao->valores[i] = irmao->valores[i + 1];
                    irmao->filhos[i] = irmao->filhos[i + 1];
                }
                irmao->filhos[irmao->total - 1] = irmao->filhos[irmao->total];
                irmao->total--;
            }
            else 
            {

                for (int i = filhoAtual->total; i > 0; i--)
                {
                    filhoAtual->valores[i] = filhoAtual->valores[i - 1];
                    filhoAtual->filhos[i + 1] = filhoAtual->filhos[i];

                }
                filhoAtual->filhos[1] = filhoAtual->filhos[0];
                filhoAtual->valores[0] = valorPai;
                filhoAtual->filhos[0] = irmao->filhos[irmao->total];

                if (irmao->filhos[irmao->total] != NULL)
                    irmao->filhos[irmao->total]->pai = filhoAtual;

                no->valores[indice - 1] = irmao->valores[irmao->total - 1];
                filhoAtual->total++;
                irmao->total--;
            }
        }
        else 
        {

            if (irmao == no->filhos[indice - 1]) 
            {
                mergeEspelhado(filhoAtual, irmao, valorPai, contador);

                for (int i = indice - 1; i < no->total - 1; i++)
                {
                    no->valores[i] = no->valores[i + 1];
                }
                for (int i = indice - 1; i < no->total; i++)
                {
                    no->filhos[i] = no->filhos[i + 1];
                }

                no->total--;
                free(irmao);
            }
            else 
            {
                merge(filhoAtual, irmao, valorPai, contador);

                for (int i = indice; i < no->total - 1; i++)
                {
                    no->valores[i] = no->valores[i + 1];

                }

                for (int i = indice + 1; i < no->total; i++)
                {
                    no->filhos[i] = no->filhos[i + 1];

                }

                no->total--;
                free(irmao);
            }

            (*contador) += 2;
            if (no->total == 0 && no->pai == NULL)
            {
                arvore->raiz = filhoAtual;
                filhoAtual->pai = NULL;
                free(no);
            }
        }
    }
}
