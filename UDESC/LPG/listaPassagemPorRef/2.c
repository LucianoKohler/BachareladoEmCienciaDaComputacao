#include <stdio.h>

void troca_valor(float *x, float *y){
    
    float temp = *x;
    *x = *y;
    *y = temp;
}

int main()
{
    float x = 11.4, y = 99; // Mude-me
    
    printf("x = %f, y = %f\n", x, y);
    
    troca_valor(&x, &y);
    
    printf("x = %f, y = %f\n", x, y);
    return 0;
}