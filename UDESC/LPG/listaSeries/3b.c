#include <stdio.h>
#include <math.h>

int main(){
    int k; 
    float num=3; // Igual a três de início
    scanf("%d", &k);

    for(float i = 1; i < k; i++){
        num += (pow(-1, i+1)*4)/((i+1)*(i+2)*(i+3));
    }


    printf("%f\n", num);
    return 0;
}