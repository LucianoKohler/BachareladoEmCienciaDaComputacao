#include <stdio.h>

int eh_primo(int n){
    if(n < 2) return 0;
    for(int i = 2; i < n; i++){
        if(n % i == 0) return 0;
    }
    return 1;
}

int soma_primos(int a[], int n){
    int soma = 0;
    for(int i = 0; i < n; i++){
        if(eh_primo(a[i])){
            soma+=a[i];

        }
    }
    return soma;
}

int main()
{
    int n;
    printf("Escolha o tamanho do array: ");
    scanf("%d", &n);
    
    int a[n];
    
    // Entrada do array
    
    printf("Digite os valores de A: ");
    
    for(int i = 0; i < n; i++){
        scanf("%d", &a[i]);
    }
    
    // Saída dos arrays
    
    printf("\nArray A: ");
    
    for(int i = 0; i < n; i++){
        printf("%d, ", a[i]);
    }
    
    // Chamada da Função
    int soma = soma_primos(a, n);   
    printf("Soma dos primos dentro de A: %d", soma);
    
    return 0;
}