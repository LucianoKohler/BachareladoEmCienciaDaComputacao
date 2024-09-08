#include <stdio.h>

// N termos
// múltiplos de K
// A partir de X

int soma_especial(int n, int k, int x){
  int sum = 0;
  while(x++){
    if(x % k == 0){
      sum += x;
      n--;
    } 
    if(n == 0) break;
  }
  return sum;
}

int main()
{
    int n, k, x;
    printf("Insira N, K e X: ");
    scanf("%d %d %d", &n, &k, &x);

    printf("Soma dos %d primeiros múltiplos de %d a partir de %d: %d", n, k, x, soma_especial(n, k, x));
    return 0;
}

