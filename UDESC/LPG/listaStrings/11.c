#include <stdio.h>
#include <string.h>

void remover(char s[], char c){
    char strCorrigida[100];
    
    int j = 0;
    for(int i = 0; i < strlen(s); i++){
        if(s[i] != c){
            strCorrigida[j] = s[i];
            j++;
        }
    }
    
    strcpy(s, strCorrigida);
    
    }

int main()
{
    char s[100];
    char c;
    
    printf("Insira a string e um caracter para removê-lo da string: ");
    scanf("%s %c", s, &c);
    
    remover(s, c);
    
    printf("String sem %c: %s", c, s);
    
    return 0;
}
