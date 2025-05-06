#include <stdio.h>
#include <math.h>

double fat(int n){
    if(n == 0 || n == 1){
      return 1;
    }
    return n * fat(n-1);
}

int main(){
    int k, x; 
    double num = 0;
    
    printf("Digite o número de termos da série e X: ");
    scanf("%d %d", &k, &x);

    for(int i = 0; i < k; i++){
      num += pow(x, i) / fat(i);
    }

    printf("%f\n", num);
    return 0;
}