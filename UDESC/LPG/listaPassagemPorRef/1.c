#include <stdio.h>

void inc_dec(int* a, int* b){
    (*a)++;    
    (*b)--;    
}

int main()
{
    int a = 12, b = 12; // Mude-me
    
    printf("a = %d, b = %d\n", a, b);
    
    inc_dec(&a, &b);
    
    printf("a = %d, b = %d\n", a, b);
    return 0;
}