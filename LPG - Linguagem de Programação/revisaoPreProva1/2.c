#include <stdio.h>

int maximoProduto(int n, int v[]){
    int maiorValor;
    for(int i = 0; i < n-1; i++){
        if(i == 0){
            maiorValor = v[i]*v[i+1];
        }else{
            if(v[i]*v[i+1] > maiorValor){
                maiorValor = v[i]*v[i+1];
            }
        }
    }
    return maiorValor;
}

int main()
{
    int n;
    scanf("%d", &n);
    int vetor[n];
    
    for(int i = 0; i < n; i ++){
        scanf("%d", &vetor[i]);
    }
    int maior = maximoProduto(n, vetor);
    printf("Maior Valor: %d\n", maior);
    
    
    
    return 0;
}
