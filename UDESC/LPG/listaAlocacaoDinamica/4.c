#include <stdio.h>
#include <stdlib.h> 
#include <string.h>

int* positivos(int* v, int n, int* tamPos){
    // Achando o tamanho do vetor
    for(int i = 0; i < n; i++){
        if(v[i] > 0){
            (*tamPos)++;
        }
    }
    
    int* vPos = malloc(*tamPos * sizeof(int));
    
    int IPos = 0;
    for(int i = 0; i < n; i++){
        if(v[i] > 0){
            vPos[IPos] = v[i];
            IPos++;
        }
    }
    
    return vPos;
}

int* negativos(int* v, int n, int* tamNeg){
    // Achando o tamanho do vetor
    for(int i = 0; i < n; i++){
        if(v[i] < 0){
            (*tamNeg)++;
        }
    }
    
    int* vNeg = malloc(*tamNeg * sizeof(int));

    int INeg = 0;
    for(int i = 0; i < n; i++){
        if(v[i] < 0){
            vNeg[INeg] = v[i];
            INeg++;
        }
    }
    
    return vNeg;
}

int main()
{
    #define n 10 // Mude-me
    int v[n] = {-1, 2, 3, -4, -5, 6, 7, -8, -9, -10}; // Mude-me
    int tamPos = 0, tamNeg = 0;
    
    printf("Valores no vetor: ");
    
    for(int i = 0; i < n; i++){
        printf("%d ", v[i]);
    }
    
    int* valoresPositivos = positivos(v, n, &tamPos);
    int* valoresNegativos = negativos(v, n, &tamNeg);
    
    printf("\nValores positivos: ");
    
    for(int i = 0; i < tamPos; i++){
        printf("%d ", valoresPositivos[i]);
    }
    
    printf("\nValores negativos: ");
    
    for(int i = 0; i < tamNeg; i++){
        printf("%d ", valoresNegativos[i]);
    }
    
    free(valoresPositivos); // Retirar se for usar o ponteiro para algo
    free(valoresNegativos); 
    return 0;
}
