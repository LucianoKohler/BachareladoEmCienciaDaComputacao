#include <stdio.h>

int main(){
    int n, a, b, sum=0;
    printf("Digite a quantidade de testes: ");
    scanf("%d", &n);
    while(n--){

      scanf("%d %d", &a, &b);
      if(a > b){
          int temp = a;
          a = b;
          b = temp;
      }

      if(a % 2 == 0){ a++; } else{ a+=2; }
      
      for(int i = a; i < b; i+=2){ sum+=i; }
      printf("%d\n", sum);
      sum = 0;
    }
    return 0;
}