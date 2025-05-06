#include <stdio.h>
#include <stdlib.h> 
#include <string.h>

float* maioresMedia(float* v, int n, int* tamV){
    
    // Fazendo a média
    float media = 0;
    for(int i = 0; i < n; i++){
        media += v[i];
    }
    
    media/=n;
    
    // Encontrando o tamanho do novo vetor
    for(int i = 0; i < n; i++){
        if(v[i] > media){
           (*tamV)++; 
        }
    }
    
    float* novoV = malloc(*tamV*sizeof(float));
    int j = 0;
    
    for(int i = 0; i < n; i++){
        if(v[i] > media){
           novoV[j] = v[i];
           j++;
        }
    }
    
    return novoV;
}

int main()
{
    #define n 10 // Mude-me
    float v[n] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Mude-me
    int tamV = 0; // Para o novo vetor a se criar
    
    printf("Valores no vetor: ");
    
    for(int i = 0; i < n; i++){
        printf("%.2f ", v[i]);
    }
    
    float* Vmaiores = maioresMedia(v, n, &tamV);
    printf("\nValores maiores que a média: ");
    
    for(int i = 0; i < tamV; i++){
        printf("%.2f ", Vmaiores[i]);
    }
    
    free(Vmaiores); // Retirar se for usar o ponteiro para algo
    
    return 0;
}
