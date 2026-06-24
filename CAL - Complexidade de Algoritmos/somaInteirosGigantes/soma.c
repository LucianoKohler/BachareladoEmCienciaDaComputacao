#include <stdio.h>
#include <string.h>
/*
Approach:
1. Transformar os números (strings) em valores numéricos em um array de short (que salva cada caractere)
2. Somar os valores slot a slot (que nem matemática normal)
3. Transformar o vetor novo somado em uma string novamente
*/
void imprimirSoma(char* n1, char* n2){
    short num1[100], num2[100]; 
    short res[100]; 
    int tam1 = strlen(n1);
    int tam2 = strlen(n2);

    for(int i = 0; i < 100; i++) { num1[i] = 0; num2[i] = 0; }


    // Passo 1: Colocar o número (strings) no vetor (lembrar de inverter os slots para somar corretamente)
    for(int i = 0; i < tam1; i++){ num1[100 - 1 - i] = n1[tam1 - 1 - i] - '0'; } 
    for(int i = 0; i < tam2; i++){ num2[100 - 1 - i] = n2[tam2 - 1 - i] - '0'; }



    // Passo 2: Calcular a soma dos valores
    int carry = 0;
    for(int i = 100 - 1; i >= 0; i--){
        int resultado = num1[i] + num2[i] + carry;
        if(resultado >= 10){
            carry = 1;
            resultado -= 10;
        } else {
            carry = 0;
        }
        res[i] = resultado;
    }



    // Passo 3: Transformando a soma de volta pra string (pra printar)
    int inicioNumero = -1;
    for(int i = 0; i < 100; i++){
        if (res[i] != 0 && inicioNumero == -1) inicioNumero = i;
    }

    // Se não achar o início do número
    if (inicioNumero == -1) {
        printf("0\n");
        return;
    }

    for(int i = inicioNumero; i < 100; i++){
        printf("%d", res[i]);
    }
    printf("\n");
}

int main(int argc, char *argv[]){
    // Verificação de argumentos (pra ver se recebe via argumento ou pelo terminal na hora)
    if(argc > 3 || argc == 2){
        printf("Parametros incorretamente passados! Passe somente os dois numeros a se somar ou nenhum parametro para passar via terminal");
        return 0;
    }else if(argc == 3){
        printf("Soma: ");
        imprimirSoma(argv[1], argv[2]);
        return 0;
    }else{ // Receber os valores via terminal
        char n1[100], n2[100]; 
        
        printf("Insira o primeiro valor a se somar: ");
        scanf("%s", n1);
        
        printf("Insira o segundo valor a se somar: ");
        scanf("%s", n2);
    
        printf("Soma: ");
        imprimirSoma(n1, n2);
        return 0;
    }
}