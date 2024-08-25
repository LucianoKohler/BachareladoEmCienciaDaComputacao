#include <stdio.h>

int main(){
    int start, end;
    printf("Digite o horário de inicio e de fim da jogatina: ");
    scanf("%d %d", &start, &end);

    if(start == end){      printf("O JOGO DUROU 24 HORA(S)\n");
    }else if(start < end){ printf("O JOGO DUROU %d HORA(S)\n", end - start);
    }else{                 printf("O JOGO DUROU %d HORA(S)\n", 24 - start + end);
    }
    
    return 0;
}