#include <stdio.h>

int contem(char s[], char c){
    for(int i = 0; s[i] != '\0'; i++){
        if(s[i] == c){
            return 1;
        }
    }
    
    return 0;
}

int main()
{
    char s[100];
    char c;
    
    printf("Insira a string S e o caractere C: ");
    scanf("%s %c", s, &c);
    
    int res = contem(s, c);
    
    if(res == 1){
        printf("%s contém a letra %c", s, c);
    }else{
        printf("%s não contém a letra %c", s, c);
    }
    
    return 0;
}
