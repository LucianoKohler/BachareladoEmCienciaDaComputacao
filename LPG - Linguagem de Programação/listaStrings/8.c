#include <stdio.h>
#include <string.h>
#include <math.h>

int eh_data(char s[]){
    
    if(strlen(s) != 10) return 0; // Se for menor/maior
    
    for(int i = 0; s[i] != '\0'; i++){
        
        if (i == 2 || i == 5){ // Ver se é barra
            if(s[i] != '/') return 0;
        
        }else{ // Ver se é número
            if(!(s[i] >= 48 && s[i] <= 57)) return 0;
        }
    }
    
    return 1;
}

int pega_dia(char s[]){
    int dia = 0;
    dia += (s[0]-48)*10;
    dia += (s[1]-48)*1;
    
    return dia;
}

int pega_mes(char s[]){
    int mes = 0;
    mes += (s[3]-48)*10;
    mes += (s[4]-48)*1;
    
    return mes;
}

int pega_ano(char s[]){
    int ano = 0;
    ano += (s[6]-48)*1000;
    ano += (s[7]-48)*100;
    ano += (s[8]-48)*10;
    ano += (s[9]-48)*1;
    
    return ano;
}

int main()
{
    char s[100];
    
    printf("Insira a data no formato DD/MM/AAAA: ");
    scanf("%s", s);
    
    int verificacao = eh_data(s);
    
    if(verificacao == 1){
        printf("%s é uma data válida\n\n", s);
        int dias = pega_dia(s);
        int meses = pega_mes(s);
        int anos = pega_ano(s);
        printf("Hoje é dia %d do %d de %d", dias, meses, anos);
    }else{
        printf("%s não é uma data válida\n\n", s);
    }
    

    
    return 0;
}
