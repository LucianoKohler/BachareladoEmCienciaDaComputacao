#include <stdio.h>

int compara(float a[], float b[], int n){
    for(int i = 0; i < n; i++){
        if(a[i] != b[i]){
            return 0;
        }
    }
    return 1;
}

int main()
{
    int n;
    printf("Escolha o tamanho do array: ");
    scanf("%d", &n);
    
    float a[n], b[n];
    
    // Entrada dos arrays
    
    printf("Digite os valores de A: ");
    
    for(int i = 0; i < n; i++){
        scanf("%f", &a[i]);
    }
    
   printf("Digite os valores de B: ");
    
    for(int i = 0; i < n; i++){
        scanf("%f", &b[i]);
    }
    
    // Saída dos arrays
    
    printf("\nArray A: ");
    
    for(int i = 0; i < n; i++){
        printf("%.3f, ", a[i]);
    }
    
    printf("\nArray B: ");
    
    for(int i = 0; i < n; i++){
        printf("%.3f, ", b[i]);
    }
    
    // Comparação dos arrays
    
    if(compara(a, b, n) == 0){
        printf("\nOs arrays NÃO são iguais.");
    }else{
        printf("\nOs arrays SÃO iguais.");
    }
    
    
    return 0;
}