#include <stdio.h>
#include <unistd.h>
#define conjuntos 8
#define tamanho_conjunto 10

/* NOTAS IMPORTANTES

 - Um conjunto não deve ter valores repetidos
 - Como é necessário conferir se o valor digitado não existe no conjunto, fiz a op. 2 diferente do enunciado
 - Perguntar ao professor se o conjunto escolhido deve ser ZERADO na segunda operação
 
*/

// Operação suporte para validar se o conjunto escolhido é válido
int validaOperacao(int cont, int arrEscolhido){
    if(arrEscolhido > conjuntos-1){
        printf("Este valor ultrapassa o valor máximo de conjuntos!\n");
        return 0;
    }
    if(arrEscolhido > cont-1){
        printf("Este array ainda não foi criado, crie-o!\n");
        return 0;
    }
    
    return 1;
}


// Operação suporte para insereConjunto() e buscaValor()
int buscaSequencial(int chave, int arr[]){
    for(int i = 0; i < tamanho_conjunto; i++){
        if(arr[i] == chave){
            return 1;
        }
    }
    
    return 0;
}

// OPERAÇÕES PRINCIPAIS

// OPERAÇÃO NÚMERO 1
int criaConjunto(int cont){
    if(cont > 8){ // Maior que 8 pois cont sempre será o número de conjuntos + 1
        printf("Número máximo de conjuntos criados, exclua algum.\n");
    }else{
        printf("Um novo conjunto foi criado no espaço %d!\n", cont);
        cont++;
    }
    
    return cont;
}

// OPERAÇÃO NÚMERO 2
void insereConjunto(int cont, int arr[]){
    int valor;
    int i = 0;
    
    while(1){
        
        if(i == 10){
            printf("Valor máximo de números atingido\n");
            break;
        }
        
        printf("Insira o valor do %d° valor: ", i+1);
        scanf("%d", &valor);
       
        // Tratando com erros
        if(valor == 0){
            printf("Nulo digitado\n");
            break;
            
        }else if(buscaSequencial(valor, arr)){
            printf("\nEste valor já está contido no conjunto.\n\n");
            
        }else{
            arr[i] = valor;
            i++;
        }
    }
}

// OPERAÇÃO NÚMERO 6
void mostraConjunto(int arrEscolhido, int arr[]){
    printf("Conjunto %d: ", arrEscolhido);
    
    if(arr[0] == 0){
        printf("Conjunto vazio");
    }else{
        for(int i = 0; i < tamanho_conjunto; i++){
            if(arr[i] == 0) break;
            printf("%d, ", arr[i]);
        }
    }
    printf("\n");
}

// OPERAÇÃO NÚMERO 7
void mostraTodosConjuntos(int cont, int matriz[][10]){
    if(cont == 0){
        printf("Nenhum conjunto criado, crie algum!");
    }else{
        for(int i = 0; i < cont; i++){
            mostraConjunto(i, matriz[i]);
        }
    }
}

// OPERAÇÃO NÚMERO 8
void buscaValor(int chave, int cont, int matriz[][10]){
    if(cont == 0){
        printf("Nenhum conjunto criado, crie algum!");
    }else if(chave == 0){
        printf("0 é um valor inválido par busca!\n");
    }else{
        printf("\nConjuntos que contém o valor %d:\n\n", chave);
        for(int i = 0; i < cont; i++){
            if(buscaSequencial(chave, matriz[i])){
                printf("Conjunto %d\n", i);
            }
        }
    }
}

int main()
{
    
    int escolha = 0;
    int cont = 0;
    int matriz[conjuntos][tamanho_conjunto] = {0};
    int arrEscolhido; // Variável muito usada em operações que usam um conjunto específico
    
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
                printf("Insira o índice do array para fazer a operacão (0 a 7): ");
                scanf("%d", &arrEscolhido);
                
                if(validaOperacao(cont, arrEscolhido)){
                    insereConjunto(cont, matriz[arrEscolhido]);
                    break;
                }else{
                    break;
                }
                
                
                
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
                
                
                
            case 6:
                printf("Insira o índice do array para fazer a operacão (0 a 7): ");
                scanf("%d", &arrEscolhido);
                if(validaOperacao(cont, arrEscolhido)){
                    mostraConjunto(cont, matriz[arrEscolhido]);
                    break;
                }else{
                    break;
                }
                
                
                
            case 7:
                mostraTodosConjuntos(cont, matriz);
                break;
            
            
            
            case 8:
                int chave;
                printf("Insira o valor a ser procurado: ");
                scanf("%d", &chave);
                buscaValor(chave, cont, matriz);
                break;
                
                
                
            case 9:
                printf("Obrigado por usar nossos serviços!\n");
                return 0;
                break;
                
                
                
            default:
                printf("Valor inválido!\n");
                break;
        }
        
        printf("\nPressione ESPACO e ENTER para continuar...");
        while(getchar() != ' ');
        
        printf("\033[H\033[J"); // Limpa a tela
    }
    
    return 0;
}
