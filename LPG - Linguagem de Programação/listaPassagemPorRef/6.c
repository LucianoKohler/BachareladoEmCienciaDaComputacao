#include <stdio.h>
#include <math.h>

void max_vetor(float vet[], int tam, float *pMax, int *pIndice){
    for(int i = 0; i < tam; i++){
        if (vet[i] > *pMax){
            *pMax = vet[i];  
            *pIndice = i;
        } 
    }
}

int main()
{
    float v[] = {1, 9, 2, 0}; // Mude-me
    int tam = sizeof(v)/sizeof(float);
    int pIndice = 0;
    float pMax = 0;
    
    max_vetor(v, tam, &pMax, &pIndice);
    
    printf("Maior valor: %f no índice: %d", pMax, pIndice);
    return 0;
}