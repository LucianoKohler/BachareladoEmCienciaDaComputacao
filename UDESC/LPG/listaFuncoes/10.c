#include <stdio.h>

int fat(int n){
  if(n == 0) return 1;
  return n * fat(n - 1);
}

float soma_iterativa(int n){
  float sum = 0;
  for(int i = 0; i <= n; i++){
    sum += 1.0/fat(i);
  }
  return sum;
}

float soma_recursiva(int n){
  if(n == 0) return 1;
  return 1.0/fat(n) + soma_recursiva(n - 1);
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

