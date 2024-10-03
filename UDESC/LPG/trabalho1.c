#include <stdio.h>
#include<unistd.h>

int criaConjunto(int cont){
    if(cont > 8){ // Maior que 8 pois cont sempre será o número de conjuntos + 1
        printf("Número máximo de conjuntos criados, exclua algum.\n");
    }else{
        printf("Um novo conjunto foi criado no espaço %d!\n", cont);
        cont++;
    }
    
    return cont;
}

int main()
{
    #define conjuntos 8
    #define tamanho_conjunto 10
    
    int escolha = 0;
    int cont = 0;
    int matriz[conjuntos][tamanho_conjunto] = {0};
    
    while(1){
    
        printf("Menu do gerenciador de Conjuntos:\n\n");

        printf("1. Criar novo conjunto vazio\n");
        printf("2. Inserir dados em um conjunto\n");
        printf("3. Remover um conjunto\n");
        printf("4. Fazer a união entre dois conjuntos\n");
        printf("5. Fazer a intersecção de dois conjuntos\n");
        printf("6. Mostrar um conjunto\n");
        printf("7. Mostrar todos os conjuntos\n");
        printf("8. Fazer busca por um valor\n");
        printf("9. Sair do programa\n");

        printf("\nSua escolha: ");
        scanf("%d", &escolha);
        
        printf("\033[H\033[J"); // Limpa a tela 

        
        switch(escolha){
            case 1:
                int cont = criaConjunto(cont);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                printf("Obrigado por usar nossos serviços!\n");
                return 0;
                break;
            default:
                printf("Valor inválido!\n");
                break;
        }
        
        sleep(1);
        printf("\033[H\033[J"); // Limpa a tela
    }
    
    return 0;
}
