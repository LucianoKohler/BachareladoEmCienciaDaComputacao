#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void mostra_vetores(int n_listas, int* p_qtdValores, int** p_listas){
    for(int i = 0; i < n_listas; i++){
        for(int j = 0; j < p_qtdValores[i]; j++){
            printf("%d ", p_listas[j][i]);
        }
        printf("\n");
    }
}
