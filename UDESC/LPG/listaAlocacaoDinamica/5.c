#include <stdio.h>
#include <stdlib.h> 
#include <string.h>

// Função auxiliar para não repetir valores no nosso conjunto união
int buscaSequencial(int chave, int arr[], int tamArr){
    for(int i = 0; i < tamArr; i++){
        if(arr[i] == chave){ 
            return 1;
        }
    }
    
    return 0;
}


int *uniao( int *v1, int n1, int *v2, int n2, int *p3 ){
    *p3 = n1;

    for(int i = 0; i < n2; i++){
        if(!buscaSequencial(v2[i], v1, n1)){
            (*p3)++;
        }
    }
    
    int* vUnido = malloc(*p3 * sizeof(int));
    int IVUnido = 0;
    
    for(int i = 0; i < n1; i++){
        vUnido[IVUnido] = v1[i];
        IVUnido++;
    }
    
    for(int i = 0; i < n2; i++){
        if(!buscaSequencial(v2[i], v1, n1)){
            vUnido[IVUnido] = v2[i];
            IVUnido++;
        }
    }
    
    return vUnido;
}

int main()
{
    int p3 = 0;
    
    int n1, n2;
    printf("Insira a capacidade do vetor 1: ");
    scanf("%d", &n1);
    int v1[n1];
    printf("Insira os valores do vetor de capacidade %d: ", n1);
    for(int i = 0; i < n1; i++){
        scanf("%d", &v1[i]);
    }
    
    printf("\nInsira a capacidade do vetor 2: ");
    scanf("%d", &n2);
    int v2[n2];
    printf("Insira os valores do vetor de capacidade %d: ", n1);
    for(int i = 0; i < n2; i++){
        scanf("%d", &v2[i]);
    }
    
    int* vUnido = uniao(v1, n1, v2, n2, &p3);

    printf("Vetor união: ");
    for(int i = 0; i < p3; i++){
        printf("%d ", vUnido[i]);
    }

    free(vUnido); // Retirar se for usar o ponteiro para algo

    return 0;
}
