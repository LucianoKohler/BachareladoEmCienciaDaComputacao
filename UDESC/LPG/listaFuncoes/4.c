#include <stdio.h>

int soma_impares(int x, int y){
    int sum=0;
    
    // deixar x sempre menor que y
    if(y < x){
      int temp = x;
      x = y;
      y = temp;
    }

    // se for par, começar ímpar
    if(x % 2 == 0){x++;}

    // x+2 para não contar o primeiro valor
    for(int i = x+2; i < y; i += 2){
      sum+=i;
    }
    return sum;
}

int main()
{
    int x, y;
    printf("Insira X e Y: ");
    scanf("%d %d", &x, &y);
    
    printf("Soma dos ímpares entre %d e %d: %d", x, y, soma_impares(x, y));

    return 0;
}

