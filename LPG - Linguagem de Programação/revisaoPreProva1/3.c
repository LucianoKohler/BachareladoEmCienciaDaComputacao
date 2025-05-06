#include <stdio.h>
#define M 3
#define N 4

void menores_precos(int m, int n, float mat[M][N], float vet[N]){
    int menorSoma = 12398728;
    int menorSomaID = 0;
    for(int i = 0; i < m; i++){
        float soma = 0;
        
        for(int j = 0; j < n; j++){
            soma += mat[i][j];
        }

        if(soma < menorSoma){
            menorSoma = soma;
            menorSomaID = i;
        }
    }
    
    for(int i = 0; i < n; i++){
        vet[i] = mat[menorSomaID][i];
    }
};

int main()
{
    float mat[M][N] =
    {
        {30.00, 14.30, 91.00, 110.00},
        {32.50, 16.50, 89.00, 105.00},
        {35.80, 13.80, 80.00, 110.00},
    };
    
    float vet[N] = {0};
    
    menores_precos(M, N, mat, vet);
    
    for(int i = 0; i < N; i++){
        printf("%f, ", vet[i]);
    }
    
    return 0;
}
