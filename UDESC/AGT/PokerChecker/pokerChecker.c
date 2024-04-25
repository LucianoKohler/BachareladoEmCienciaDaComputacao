/* *** INSTRUÇÕES:
Colocar as cartas no formato X 0 onde:
X é a primeira letra do naipe da cartas em caixa alta
0 é um valor inteiro de 1 a 12
*/

#include <stdio.h>

int main()
{
    /* Um array para números (1 a 12) e um para o naipe:
    'C' = Copas
    'O' = Ouros
    'E' = Espadas
    'P' = Paus */
    char naipe[5];
    int valor[5];
    
printf(" _____      _                _____ _               _             \n");
printf("|  __ -    | |              - ____| |             | |            \n");
printf("| |__) |__ | | _____ _ __  | |    | |__   ___  ___| | _____ _ __ \n");
printf("|  ___/ _ -| |/ / _ - '__| | |    | '_ - - _ -/ __| |/ / _ - '__|\n");
printf("| |  | (_) |   <  __/ |    | |____| | | |  __/ (__|   <  __/ |   \n");
printf("|_|   -___-|_|-_-___|_|     -_____|_| |_|-___|-___|_|-_-___|_|   \n\n");

    // Entrando com as 5 cartas
    for(int i = 0; i < 5; i++){
        
        // Recebendo a carta
        printf("Insira a %d° carta: ", i+1);
        scanf(" %c %d", &naipe[i], &valor[i]);
        
        // Se o naipe for minúsculo, transformando em maiúsculo
        if(naipe[i] >= 97 && naipe[i] <= 122){naipe[i] -= 32;}
        
        // Se o naipe for inválido
        if(naipe[i] != 'E' && naipe[i] != 'O' && naipe[i] != 'P' && naipe[i] != 'C'){
            printf("Naipe inválido! Escreva E, O, P ou C\n\n");
            i--;
        
        // Se o valor for inválido
        }else if(valor[i] <= 0 || valor[i] >= 13){
            printf("Valor inválido, insira números entre 1 e 13\n\n");
            i--;
        }
    }
    
    // ordenar números
    int i, keyValor, j;
    char keyNaipe;
    for (i = 1; i < 5; i++) {
        keyValor = valor[i];
        keyNaipe = naipe[i];
        j = i - 1;
        while (j >= 0 && valor[j] > keyValor) {
            valor[j + 1] = valor[j];
            naipe[j + 1] = naipe[j];
            j = j - 1;
        }
        valor[j + 1] = keyValor;
        naipe[j + 1] = keyNaipe;
    }
    
    // Mega IF para decidir qual é a mão
    if( // 1. Royal Flush
        (
        naipe[0] == naipe[1] &&
        naipe[1] == naipe[2] &&
        naipe[2] == naipe[3] &&
        naipe[3] == naipe[4]
        ) && 
        (
        valor[0] == 1 &&
        valor[1] == 9 &&
        valor[2] == 10 &&
        valor[3] == 11 &&
        valor[4] == 12
        )
    ){
        printf("UM ROYAL FLUSH!");
    }else 
    if( // 2. Straight Flush
        (
        naipe[0] == naipe[1] &&
        naipe[1] == naipe[2] &&
        naipe[2] == naipe[3] &&
        naipe[3] == naipe[4]
        ) &&
        (
        valor[0] == valor[1]-1 &&
        valor[1] == valor[2]-1 &&
        valor[2] == valor[3]-1 &&
        valor[3] == valor[4]-1
        )
    ){
        printf("Um Straight Flush!");
    }else
    if( // 3. Four of a Kind
        (
            valor[0] == valor[1] &&
            valor[1] == valor[2] &&
            valor[2] == valor[3] 
        ) ||
        (
            valor[0] == valor[2] &&
            valor[2] == valor[3] &&
            valor[3] == valor[4] 
        )
    ){
        printf("Four of a Kind, uma quadra.");
    }else
    if( // 4. Full House
    (
        valor[0] == valor[1] &&
        valor[1] == valor[2] &&
        valor[3] == valor[4]
    ) ||
    (
        valor[0] == valor[1] &&
        valor[2] == valor[3] &&
        valor[3] == valor[4] 
    )
    ){
        printf("Um clássico Full House!");
    }else
    if( // 5. Flush
        naipe[0] == naipe[1] &&
        naipe[1] == naipe[2] &&
        naipe[2] == naipe[3] &&
        naipe[3] == naipe[4] 
    ){
        printf("Todas as cartas do mesmo naipe, um Flush.");
    }else
    if( // 6. Straight
        valor[0] == valor[1]-1 &&
        valor[1] == valor[2]-1 &&
        valor[2] == valor[3]-1 &&
        valor[3] == valor[4]-1 
    ){
        printf("Straight, do inglês: Uma sequência!");
    }else
    if( // 7. Three of a Kind
        (
            valor[0] == valor[1] &&
            valor[1] == valor[2]
        ) ||
        (
            valor[1] == valor[2] &&
            valor[2] == valor[3]
        ) ||
        (
            valor[2] == valor[3] &&
            valor[3] == valor[4]
        )
    ){
        printf("Three of a Kind, uma trinca.");
    }else
    if( // 8. Two Pairs
        (valor[0] == valor[1] && valor[2] == valor[3]) ||
        (valor[1] == valor[2] && valor[3] == valor[4]) ||
        (valor[0] == valor[1] && valor[3] == valor[4])
    ){
        printf("Dois pares, ou como chamam, Two Pairs");   
    }else
    if( // 9. Pair
        (valor[0] == valor[1]) ||
        (valor[1] == valor[2]) ||
        (valor[2] == valor[3]) ||
        (valor[3] == valor[4])
    ){
        printf("Apenas um par, quase a pior mão!");
    }else{ // 10. High Hand
        printf("A pior mão do jogo, carta alta: %d %c", valor[4], naipe[4]);
    }
    
    return 0;
}