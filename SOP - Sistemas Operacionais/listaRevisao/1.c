/* Escreva um programa C que imprima o seu nome e os parametros de linha de comando, um por linha, na ordem inversa. */

#include <stdio.h>

int main(int argc, char* argv[]){
    if(argc <= 1){ printf("Erro: nenhum argumento passado\n"); return 0; }

    for(int i = argc-1; i >= 1; i--){
        printf("%s\n", argv[i]);
    }
    
    printf("Luciano\n");
    return 0;
}