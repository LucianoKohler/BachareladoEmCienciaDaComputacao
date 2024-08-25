#include <stdio.h>

int main(){
    int a, b, sum=0;
    printf("Digite dois números inteiros: ");
    scanf("%d %d", &a, &b);
    if(a > b){
        int temp = a;
        a = b;
        b = temp;
    }

    if(a % 2 == 0){ a++; } else{ a+=2; }
    
    for(int i = a; i < b; i+=2){ sum+=i; }
    printf("%d\n", sum);

    return 0;
}