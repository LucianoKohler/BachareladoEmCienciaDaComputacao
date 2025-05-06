#include <stdio.h>

void max_matriz(float mat[5][5], float *pMax, int *pI, int *pJ){
    for(int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
            if(mat[i][j] > *pMax){
                *pI = i;
                *pJ = j;
                *pMax = mat[i][j];
            }
        }
    }
    
    printf("O maior valor é %f, na linha %d e coluna %d", *pMax, *pI, *pJ);
}

int main()
{
    float mat[5][5] = {
        {1, 2, 3, 4, 5},
        {6, 7, 8, 2, 3},
        {11, 20, 10, 8, 9},
        {1, 2, 1, 1, 2},
        {9, 9, 8, 8, 2},
    };
    
    float pMax = 0;
    int pI = 0, pJ = 0;
    max_matriz(mat, &pMax, &pI, &pJ);
	

	return 0;
}
