#include <stdio.h>

void min_matriz(float mat[3][4], float *pMin, int *pI, int *pJ){
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 4; j++){
            if (mat[i][j] < *pMin){
                *pMin = mat[i][j];
                *pI = i;
                *pJ = j;
            }
        }
    }}

int main()
{
    float mat[3][4] = {{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    float pMin = 999999;
    int pI = 0;
    int pJ = 0;
    
    printf("Insira os valores neste formato:\n");
    printf("a b c d\n");
    printf("a b c d\n");
    printf("a b c d\n");
    printf("Valores: ");
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 4; j++){
            scanf("%f", &mat[i][j]);
        }
    }
    
    printf("Matriz criada: \n");
    for(int i = 0; i < 3; i++){
        for(int j = 0; j < 4; j++){
            printf("%.2f ", mat[i][j]);
        }
        printf("\n");
    }
    
    min_matriz(mat, &pMin, &pI, &pJ);
    
    printf("\nMenor valor encontrado: %.2f na %d° linha e na %d° coluna (em contagem de programador)", pMin, pI, pJ);

    return 0;
}
