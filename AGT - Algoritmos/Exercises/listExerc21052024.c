#include <stdio.h>

void tabuada(){
    int num;
    scanf("%d", &num);
    for(int i = 1; i <= num; i++){
        printf("%d x %d = %d\n", i, num, i*num);
    }
}

void sequencia(){
    for(int i = 1; i <= 10; i++){
        int qtd = i;
        while(qtd--){
            printf("%d\n", i);
        }
    }
}

void relogio(){
    for(int i = 0; i < 24; i++){
        for(int j = 0; j < 60; j+=15){
            printf("%d:%d\n", i, j);
        }
    }
}

void divisores(){
    int num = 1;
    while(num > 0){
        
        scanf("%d", &num);
        for(int i = 1; i <= num; i++){
            if(num%i == 0) printf("%d ", i);
        }
        
        printf("\n");
    }
}

void perfeitos(){
    int nums=0;
    
    int i = 1;
    while(nums !=4){
    
        int sum = 0;
        for(int j = i-1; j > 0; j--){
            if(i%j == 0) sum+= j;
        }
        
        if(sum == i){
            nums++;
            printf("%d ", i);
        }
        
        i++;
    }
}

void primos() {
    int num;
    scanf("%d", &num);

    int i = 2;

    while (num > 0) {
        int ePrimo = 1; 
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                ePrimo = 0; 
                break;
            }
        }
        if (ePrimo == 1) {
            printf("%d ", i);
            num--;
        }
        i++;
    }
}

void tuttifunc(){ // Chamei assim pois ela faz várias coisas
    int num;
    scanf("%d", &num);
    
    if(num%2==0){
        int divisores = 0;
        
        int i = num;
        while(i > 0){
            if(num%i==0) divisores++;
            i--;
        }
        
        printf("%d tem %d divisores", num, divisores);
    }else{
        if(num < 10){
            int fat = 1;
            for(int i = 1; i <= num; i++){
                fat = fat*i;
            }
            
            printf("O fatorial de %d é %d", num, fat);
        }else{
            int soma = 0;
            for(int i = 1; i <= num; i++){
                soma+=i;
            }
            
            printf("A soma de 1 até %d é %d", num, soma);
        }
    }
}

int main()
{
    
    // Descomente a função que você quer que seja rodada!
    // tabuada();
    // sequencia();
    // relogio();
    // divisores();
    // perfeitos();
    // primos();
    // tuttifunc();

    return 0;
}
