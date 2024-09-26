#include <stdio.h>
#include <string.h>

void trim(char s[]){
    char strCorrigida[100];
    
    // Achando o inicio da string
    int start = 0;
    while(s[start] == ' '){
        start++;
    }
    
    // Achando o fim da string
    int end = start;
    while(s[end] != '\0'){
        end++;
    }
    
    // Formando a string
    int j = 0;
    for(int i = start; i <= end; i++){
        strCorrigida[j] = s[i];
        j++;
    }
    
    strcpy(s, strCorrigida);
    
    }

int main()
{
    char s[100];
    
    printf("Insira a string para fazer o corte: ");
    scanf("%s", s);
    
    trim(s);
    
    printf("String corrigida: _%s_", s);

    return 0;
}
