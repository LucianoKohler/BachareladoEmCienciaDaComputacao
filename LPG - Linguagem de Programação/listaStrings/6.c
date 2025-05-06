#include <stdio.h>
#include <string.h>

int eh_numerico(char s[]){
    for(int i = 0; s[i] != '\0'; i++){
        if(!(s[i] >= 48 && s[i] <= 57)){
            return 0;
        }
    }
    
    return 1;
}

int main()
{
    char s[100];
    
    printf("Insira a string S: ");
    scanf("%s", s);

    int res = eh_numerico(s);
    
    if(res == 1){
        printf("%s é somente numerico.", s);
    }else{
        printf("%s não é somente numerico.", s);
    }
    
    return 0;
}
