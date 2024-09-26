#include <stdio.h>
#include <string.h>

int eh_palindromo(char s[]){
    int size = strlen(s);

    for (int i = 0; i < size / 2; i++) {
        if (s[i] != s[size-i-1]) return 0;
    }
    return 1;
}

int main()
{
    char s[100];
    
    printf("Insira a string S: ");
    scanf("%s", s);

    int res = eh_palindromo(s);
    
    if(res == 1){
        printf("%s é um palíndromo.", s);
    }else{
        printf("%s não é um palíndromo.", s);
    }
    
    return 0;
}
