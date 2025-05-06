#include <stdio.h>

void concatena(char s1[100], char s2[100]){
    
    // Tamanho s1
    
    int s1Size = 0;
    for(int i = 0; s1[i] != '\0'; i++){
        s1Size++;
    }

    // Tamanho s2
    
    int s2Size = 0;
    for(int i = 0; s2[i] != '\0'; i++){
        s2Size++;
    }

    for(int i = 0; i < s2Size; i++){
        s1[s1Size + i] = s2[i];
    }
} 


int main()
{
    char s1[100], s2[100];
    
    printf("Insira as strings S1 e S2: ");
    scanf("%s %s", s1, s2);
    
    concatena(s1, s2);

    printf("String concatenada: %s", s1);

    
    return 0;
}
