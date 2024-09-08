#include <stdio.h>

float soma_iterativa(int n){
  float sum = 0;
  for(int i = 1; i <= n; i++){
    sum += 1.0/i;
  }
  return sum;
}

float soma_recursiva(int n){
  if(n == 1) return 1;
  return 1.0/n + soma_recursiva(n - 1);
}

int main()
{
    int n;
    printf("Insira N: ");
    scanf("%d", &n);
    
    printf("Soma iterativa: %f", soma_iterativa(n));
    printf("\nSoma recursiva: %f", soma_recursiva(n));

    return 0;
}

