#include <stdio.h>
#include <string.h>

void inverter(char s[]){
    char strInvertida[100];
    
    int j = 0;
    for(int i = strlen(s)-1; i >= 0; i--){
        strInvertida[j] = s[i];
        j++;
    }
    
    strInvertida[j] = '\0';
    
    strcpy(s, strInvertida);
}

int main()
{
    char s[100];
    
    printf("Insira a string para fazer a inversao: ");
    scanf("%s", s);
    
    inverter(s);
    
    printf("String invertida: %s", s);
    

    
    return 0;
}
