#include <stdio.h>
#include <math.h>

int main(){
    int k; 
    float num=0;
    scanf("%d", &k);
    for(float i = 0; i < k; i++){
        num += (pow(-1, i)*4)/(i*2 +1);
    }

    printf("%f\n", num);
    return 0;
}