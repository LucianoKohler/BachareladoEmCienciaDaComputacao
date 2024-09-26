#include <stdio.h>

int qtd_ocorrencias(char s[], char c){
    int ocorrencias = 0;
    for(int i = 0; s[i] != '\0'; i++){
        if(s[i] == c){
            ocorrencias++;
        }
    }
    
    return ocorrencias;
}

int main()
{
    char s[100];
    char c;
    
    printf("Insira a string S e o caractere C: ");
    scanf("%s %c", s, &c);
    
    int qtd = qtd_ocorrencias(s, c);

    printf("%c ocorre %d vezes na string %s", c, qtd, s);

    
    return 0;
}
