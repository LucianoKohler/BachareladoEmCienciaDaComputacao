#include <stdio.h>
#include <math.h>

double fat(int n){
    if(n == 0 || n == 1){
      return 1;
    }
    return n * fat(n-1);
}

int main(){
    int k; 
    double num = 0;
    scanf("%d", &k);

    for(int i = 0; i < k; i++){
      num += 1 / fat(i);
    }

    printf("%f\n", num);
    return 0;
}