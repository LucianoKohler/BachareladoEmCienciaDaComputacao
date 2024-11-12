
#include <stdio.h>
#include <stdlib.h> 
#include <string.h>

char *repetidor( char *s, int n ){
    int tam = strlen(s);
    char* strProlongada = malloc(n * tam + 1);
    
    int Istr = 0;
    int i;
    for(i = 0; i < n*tam; i++){
        strProlongada[i] = s[Istr];
        Istr++;
        if(Istr == strlen(s)) Istr = 0;
    }
    
    strProlongada[i] = '\0';
    return strProlongada;
}

int main()
{
    int n = 4; // Mude-me
    char s[] = "AmoC";// Mude-me
    
    
    char* strProlongada = repetidor(s, n);
    
    printf("String inicial: %s", s);
    printf("\nString prolongada: %s", strProlongada);
    
    free(strProlongada); // Retirar se for usar o ponteiro para algo
    
    return 0;
}
