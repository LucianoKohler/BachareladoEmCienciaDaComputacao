#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *tira_letra(char *s, char c){
    // Achando o tamanho da nova str
    int novaStrTam = strlen(s);
    for(int i = 0; i < strlen(s); i++){
        if(s[i] == c){
            novaStrTam--;
        }
    }
    
    char *novaStr = malloc((novaStrTam+1)*sizeof(char));
    
    
    int j = 0;
    for(int i = 0; i < strlen(s); i++){
        if(s[i] != c){
            novaStr[j] = s[i];
            j++;
        }
    }
    
    novaStr[j] = '\0';
    
    return novaStr;
}

int main()
{
    char palavra[10] = "paralela";
    char c = 'a';
    
    char *novaS = tira_letra(palavra, c);
    
    printf("%s", novaS);
    
	return 0;
}
