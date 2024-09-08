#include <stdio.h>

void fibo(int n){
  int a = 0, b = 1, c=0;
  for(int i = 2; i <= n; i++){
    c = a + b;
    a = b;
    b = c;
    printf("%d ", c);
  }
}

int main()
{
    int n;
    printf("Insira N: ");
    scanf("%d", &n);
    
    printf("%d primeiros termos de fibbonaci: ", n);
    fibo(n);

    return 0;
}

