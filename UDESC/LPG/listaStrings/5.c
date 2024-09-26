#include <stdio.h>
#include <string.h>

void maiuscula(char s[]){
    for(int i = 0; s[i] != '\0'; i++){
        if(s[i] >= 97 && s[i] <= 122){
            s[i]-=32;
        }
    }
}

int main()
{
    char s[100];
    
    printf("Insira a string S: ");
    scanf("%s", s);

    maiuscula(s);
    
    printf("String maiúscula: %s", s);
    
    return 0;
}
