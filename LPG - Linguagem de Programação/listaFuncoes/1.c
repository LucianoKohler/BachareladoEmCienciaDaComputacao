#include <stdio.h>

int tipo_triangulo(float x, float y, float z){
    if     (x >= y+z || y >= x+z || z >= x+y) return 0;
    else if(x == y && y == z)                 return 1;
    else if(x == y || y == z || z == x)       return 2;
    else                                      return 3;
}

int main()
{
    float x, y, z;
    printf("Insira X, Y e Z: ");
    scanf("%f %f %f", &x, &y, &z);
    
    int tipo = tipo_triangulo(x,y,z);
    printf("%d", tipo);
    
    return 0;
}

