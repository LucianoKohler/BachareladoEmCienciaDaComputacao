#include <stdio.h>
#include <stdlib.h> 

float *clone( float *v, int n ){
    float* clone = malloc(n * sizeof(float));
    for(int i = 0; i < n; i++){
        clone[i] = v[i];
    }
    
    return clone;
}

int main()
{
    #define n 4 // Mude-me
    float v[n] = {1, 2, 3, 4}; // Mude-me
    
    float* vetorClonado = clone(v, n);
    
    printf("Vetor clonado: ");
    for(int i = 0; i < n; i++){
        printf("%.2f ", vetorClonado[i]);
    }
    
    printf("No endereço de memória %p", vetorClonado);
    
    free(vetorClonado); // Retirar se for usar o ponteiro para algo
    
    return 0;
}
