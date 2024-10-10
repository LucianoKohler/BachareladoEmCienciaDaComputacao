#include <stdio.h>

int main()
{    
    int num = 12;
    int* p = &num;
    int** pp = &p;
    
    printf("%d\n", *p); // 12
    
    // Mudando o valor de num
    *p = 0;
    p[0] = 0;
    *(p+0) = 0;
    
    printf("%d\n", *p); // 12
    
    printf("%p\n", *pp); // Endereço da memória de p
    
    // Escaneando valores

    scanf("%d", p);
    printf("%d\n", *p); // 12
    
    return 0;
}
