#include <stdio.h>

int soma_iterativa(int n){
  int sum = 0;
  for(int i = 1; i <= n; i++){
    sum += i;
  }
  return sum;
}

int soma_recursiva(int n){
  if(n == 1) return 1;
  return n + soma_recursiva(n - 1);
}

int main()
{
    int n;
    printf("Insira N: ");
    scanf("%d", &n);
    
    printf("Soma iterativa: %d", soma_iterativa(n));
    printf("\nSoma recursiva: %d", soma_recursiva(n));

    return 0;
}

