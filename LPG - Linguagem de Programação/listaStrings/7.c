#include <stdio.h>
#include <string.h>
#include <math.h>

int numero(char s[]){
    int size = strlen(s);
    int num = 0;
    
    for(int i = 0; s[i] != '\0'; i++){
        num += (s[i]-48)*pow(10, size-i-1);
    }
    
    return num;
}

int main()
{
    char s[100];
    
    printf("Insira a string numerica S: ");
    scanf("%s", s);

    int num = numero(s);
    
    printf("%d e numerico, e %d + 1 = %d", num, num, num+1);
    
    return 0;
}
