#include <stdio.h>
#include <string.h>

int comparar(char s1[], char s2[]){
    int somaASCII = 0;
    
    int i = 0;
    while(s1[i] != '\0' && s2[i] != '\0'){
            int somaLetra = s1[i] - s2[i];
            somaASCII+=somaLetra;
            i++;
        }
        
             if(somaASCII > 0){ return 1;}
        else if(somaASCII < 0){ return -1;}
        else                  { return 0;}
    }

int main()
{
    char s1[100], s2[100];
    
    printf("Insira S1 e S2 para comparà-las: ");
    scanf("%s %s", s1, s2);
    
    
    int res = comparar(s1, s2);
    
    if(res < 0){
        printf("%s é menor que %s", s1, s2);
    }else if(res > 0){
        printf("%s é maior que %s", s1, s2);
    }else{
        printf("%s e %s são iguais", s1, s2);
    }
    
    return 0;
}
