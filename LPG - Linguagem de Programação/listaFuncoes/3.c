#include <stdio.h>

int eh_numero(char c){
    if (c >= 48 && c <= 57) return 1;
    return 0;
}

int main()
{
    char c;
    printf("Insira um caractere: ");
    scanf("%c", &c);
    
    int maior = eh_numero(c);
    printf("É número? %s", maior ? "Sim" : "Não");
    
    return 0;
}

