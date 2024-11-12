#include <stdio.h>
#include <math.h>

void calcula_circulo(float raio, float *pPerimetro, float *pArea){
    *pPerimetro = 2*3.1415*raio;
    *pArea = 3.1415*raio*raio;
}

int main()
{
    float raio = 5; // Mude-me
    float pPerimetro;
    float pArea;
    
    calcula_circulo(raio, &pPerimetro, &pArea);
    
    printf("Raio: %f, Perimetro: %f, Área: %f\n", raio, pPerimetro, pArea);
    return 0;
}