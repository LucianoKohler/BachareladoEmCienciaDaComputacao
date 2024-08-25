#include <stdio.h>

int main(){
    int n, a, pos=0, neg=0, even=0, odd=0;
    printf("Digite a quantidade de números: ");
    scanf("%d", &n);
    while(n--){
        scanf("%d", &a);
        if(a > 0)      pos++;
        if(a < 0)      neg++;
        if(a % 2 == 0) even++;
        if(a % 2 != 0) odd++;
    }

    printf("%d valor(es) par(es)\n", even);
    printf("%d valor(es) impar(es)\n", odd);
    printf("%d valor(es) positivo(s)\n", pos);
    printf("%d valor(es) negativo(s)\n", neg);
    
    return 0;
}