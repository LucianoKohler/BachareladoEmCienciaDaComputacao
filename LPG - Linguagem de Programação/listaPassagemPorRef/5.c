#include <stdio.h>
#include <math.h>

void max_min(int vet[], int tam, int *pMin, int *pMax){
    for(int i = 0; i < tam; i++){
        if (vet[i] > *pMax) *pMax = vet[i];
        if (vet[i] < *pMin) *pMin = vet[i];
    }
}

int main()
{
    int v[] = {12, 9, 2, 0}; // Mude-me
    int tam = sizeof(v)/sizeof(int);
    int pMin = 999999;
    int pMax = 0;
    
    max_min(v, tam, &pMin, &pMax);
    
    printf("Menor valor: %d, Maior valor: %d", pMin, pMax);
    return 0;
}