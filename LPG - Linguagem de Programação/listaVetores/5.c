#include <stdio.h>

int busca_seq_rec(int v[], int n, int chave, int indices[]){

    if (n == 0) { return 0; }

    int results = busca_seq_rec(v, n - 1, chave, indices);

    if (v[n - 1] == chave) {
        indices[results] = n - 1;
        return results + 1;
    }

    return results; 

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
    
    busca_seq_rec(arr, n, chave, indices);
    
    // Saída de dados
    
    printf("indices = { ");
    
    for(int i = 0; i < n; i++){
        printf("%d, ", indices[i]);
    }
    
    printf("}");

    return 0;
}
