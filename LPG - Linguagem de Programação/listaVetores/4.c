#include <stdio.h>

void busca_todos(int v[], int n, int chave, int indices[]){
    int ocorrencias = 0;
    
    for(int i = 0; i < n; i++){
        if(v[i] == chave){
            indices[ocorrencias] = i;
            ocorrencias++;
        }
    }
}

int main()
{
    // Entrada de dados
    
    int n;
    printf("Insira o tamanho do array: ");
    scanf("%d", &n);
    
    int arr[n];
    printf("Insira os valores do array: ");
    for(int i = 0; i < n; i++){
        scanf("%d", &arr[i]);
    }
    
    int indices[n];
    for(int i = 0; i < n; i++){indices[i] = -1;}

    int chave;
    printf("Insira a chave: ");
    scanf("%d", &chave);
    
    busca_todos(arr, n, chave, indices);
    
    // Saída de dados
    
    printf("indices = { ");
    
    for(int i = 0; i < n; i++){
        printf("%d, ", indices[i]);
    }
    
    printf("}");

    return 0;
}
