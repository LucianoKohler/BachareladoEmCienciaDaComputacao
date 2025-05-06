#include <stdio.h>

int maior_valor(int x, int y, int z){
    if     (x >= y && x >= z) return x;
    else if(y >= x && y >= z) return y;
    else                      return z;
}

int main()
{
    int x, y, z;
    printf("Insira X, Y e Z: ");
    scanf("%d %d %d", &x, &y, &z);
    
    int maior = maior_valor(x, y, z);
    printf("O maior é %d", maior);
    
    return 0;
}

