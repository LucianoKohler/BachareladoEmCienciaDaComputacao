#include <stdio.h>
#include <math.h>

void calcula_hora(int totalMinutos, int *ph, int *pm){
    *ph = totalMinutos/60;
    *pm = totalMinutos - *ph*60;
}

int main()
{
    int minutos;
    int ph = 0, pm = 0;
    
    printf("Insira a qtd de minutos passados: ");
    scanf("%d", &minutos);
    
    
    calcula_hora(minutos, &ph, &pm);
    
    printf("Horário: %dh%dm", ph, pm);
    return 0;
}