/* Escreva um programa C que receba um inteiro como parametro na linha de comando, e mostre quantos bits estao ligados. */
// 20 = 0b10100

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]){
    if(argc <= 1){ printf("Erro: nenhum argumento passado\n"); return 0; }

    int num = atoi(argv[1]);
    int qtd1 = 0;

    while(num > 0){
        if(num%2 == 1){
            qtd1++;        
        }
        num = num >> 1;
    }
    printf("O numero %s tem %d bits ligados\n", argv[1], qtd1);
    return 0;
}