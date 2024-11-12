#include <stdio.h>
#define M 3
#define N 4

void maior_valor_na_linha(int calabreso[M][N], int amostradinho[N]){
    int maior_valor = calabreso[0][0];
    int maior_valorID = 0;
    for(int i = 0; i < M; i++){
        for(int j = 0; j < N; j++){
            if(calabreso[i][j] > maior_valor){
                maior_valor = calabreso[M][N];
                maior_valorID = i;
            }
        }
    }
    
    for(int j = 0; j < N; j++){
        amostradinho[j] = calabreso[maior_valorID][j];
    }
}

int main()
{
    int mat[M][N] = {
        {1, 2, 3, 5},
        {-2, -5,-7, 9},
        {99, 2 , 9, 0},
    };
    
    int arr[N] = {0};
    
    maior_valor_na_linha(mat, arr);
    
    for(int i = 0; i < N; i++){
        printf("%d, ", arr[i]);
    }
    
    return 0;
}
