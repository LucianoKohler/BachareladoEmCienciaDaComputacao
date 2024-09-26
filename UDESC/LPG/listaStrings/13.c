#include <stdio.h>
#include <string.h>

void substring(char str[], int ini, int n, char sub[]){
    
    int j = 0;
    for(int i = ini; i < strlen(str); i++){
        if(str[i] != '\0'){
            sub[j] = str[i];
            j++;
        }
        
        n--;
        if(n == 0){break;}
    }
}

int main()
{
    char str[100], sub[100];
    int ini, n;
    
    printf("Insira somente a String: ");
    scanf("%[^\n]", str);
    printf("Insira o index de início e a quantidade de caracteres: ");
    scanf("%d %d", &ini, &n);
    
    substring(str, ini, n, sub);
    
    printf("String recortada: %s", sub);
    
    return 0;
}
