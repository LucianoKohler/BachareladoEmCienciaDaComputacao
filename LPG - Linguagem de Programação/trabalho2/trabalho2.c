// Feito por: Emanuel, Luciano e Uriel

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Marca{
    char nomeMarca[20], pais[20];
};

struct Data{
    int dia, mes, ano;
};

struct Smartphone{
    char nomeModelo[20], processador[20], cor[10];
    int memoria;
    struct Data dataCadastro;
    struct Marca dadosMarca;
};

void leTexto(struct Smartphone **listaStructs, int *nItems);

void insereDados(struct Smartphone *p, struct Smartphone **listaStructs, int *nItems, int *qtdRemov);

void mostraTodosProdutos(struct Smartphone **listaStructs, int *nItems);

void removeProduto(struct Smartphone **listaStructs, int *nItems, int *qtdRemov);

void relatorio(struct Smartphone **listaStructs, int *nItems, int *qtdRemov);

void cadastrar(struct Smartphone **listaStructs, int *nItems, int *qtdRemov);

void consultaProduto (struct Smartphone **listaStructs, int *nItems);

int main()
{
    int nItems = 0, qtdRemov = 0;
    
    struct Smartphone *listaStructs = NULL;
    FILE *f = fopen("db.txt", "rt");
    if(f != NULL){
        leTexto(&listaStructs, &nItems);
    }
    

    while(1){
        int opcao;
        struct Smartphone celular;
        printf("(1) Inserir novos registros \n");
        printf("(2) Remover registros existentes \n");
        printf("(3) Exibir todo o conteúdo do cadastro\n");
        printf("(4) Consultar registros pelo campo chave\n");
        printf("(5) Sair do programa e gerar relatório em .txt\n\n");

        printf("Escolha uma opção: ");
        scanf("%d", &opcao);
        
        printf("\033[H\033[J"); // Limpa a tela
        
        switch (opcao){
            case 1:
                insereDados(&celular, &listaStructs, &nItems, &qtdRemov);
                break;
            case 2:
                removeProduto(&listaStructs, &nItems, &qtdRemov);
                break;
            case 3:
                mostraTodosProdutos(&listaStructs, &nItems);
                break;
            case 4:
                consultaProduto(&listaStructs, &nItems);
                break;
            case 5:
                cadastrar(&listaStructs, &nItems, &qtdRemov);
                relatorio(&listaStructs, &nItems, &qtdRemov);
                free(listaStructs);
                return 0;
                break;
        }
        printf("\nPressione ENTER para continuar...");
        int c;
        while ((c = getchar()) != '\n' && c != EOF); // Limpa o buffer pra remover os \n se houverem
        while (getchar() != '\n');

        printf("\033[H\033[J"); // Limpa a tela


    }
}

void leTexto(struct Smartphone **listaStructs, int *nItems){
    FILE *db = fopen("db.txt", "rt");
    fscanf(db, "%d\n", nItems);
    
    *listaStructs = realloc(*listaStructs, (*nItems)*sizeof(struct Smartphone));
    
    for (int i = 0; i < *nItems; i++) {
        // Lê os dados do smartphone
        fscanf(db, " %[^\n]", (*listaStructs)[i].nomeModelo);
        fscanf(db, "%d",      &((*listaStructs)[i].memoria));
        fscanf(db, " %[^\n]", (*listaStructs)[i].processador);
        fscanf(db, " %[^\n]", (*listaStructs)[i].cor);
        fscanf(db, " %[^\n]", (*listaStructs)[i].dadosMarca.nomeMarca);
        fscanf(db, " %[^\n]", (*listaStructs)[i].dadosMarca.pais);
        fscanf(db, "%d %d %d", &((*listaStructs)[i].dataCadastro.dia), 
                               &((*listaStructs)[i].dataCadastro.mes), 
                               &((*listaStructs)[i].dataCadastro.ano));
    }
}

void insereDados(struct Smartphone *p, struct Smartphone **listaStructs, int *nItems, int *qtdRemov){
    int jaFoiCadastrado = 0;
    (*nItems)++;

    printf("Produtos após este cadastramento: %d\n\n", *nItems - (*qtdRemov) );

    *listaStructs = realloc(*listaStructs, (*nItems)*sizeof(struct Smartphone));
    
    //Perguntar o modelo
    while(1){
        
        jaFoiCadastrado = 0;
        printf("Insira o nome do modelo: ");
        scanf(" %[^\n]", (*p).nomeModelo);
        
        //O modelo ja foi cadastrado?
        for(int i = 0;i<(*nItems);i++){
            if(strcmp((*listaStructs)[i].nomeModelo, (*p).nomeModelo) == 0){
                printf("Esse Modelo ja foi cadastrado.\n");
                jaFoiCadastrado = 1;
                break;
            }
        }
        if(jaFoiCadastrado == 0){
            break;
        }
        
    }
    //
    
    printf("Insira o valor da memória (GB): ");
    scanf("%d", &(*p).memoria);

    printf("Insira o modelo do processador: ");
    scanf(" %[^\n]", (*p).processador);

    printf("Insira a cor do dispositivo: ");
    scanf(" %[^\n]", (*p).cor);

    printf("Insira o nome da marca: ");
    scanf(" %[^\n]", (*p).dadosMarca.nomeMarca);

    printf("Insira o país de origem: ");
    scanf(" %[^\n]", (*p).dadosMarca.pais);

    printf("Insira o dia, mes e ano (separe cada um por espaço): ");
    scanf("%d%d%d", &(*p).dataCadastro.dia, &(*p).dataCadastro.mes, &(*p).dataCadastro.ano);

    (*listaStructs)[(*nItems)-1] = *p; //salva a struct com os valores no vetor

    printf("Produto cadastrado com sucesso.\n");
}

void mostraTodosProdutos(struct Smartphone **listaStructs, int *nItems){
    printf("------------------------------------------------------------------------------------------------------------\n");
    printf("%-14s %-8s %-17s %-10s %-17s %-10s %s\n", "Modelo", "Memoria", "Processador", "Cor", "Marca", "País", "Data do Cadastro");
    printf("-------------- -------- ----------------- ---------- ----------------- --------- ---------------------------");
    int itemIndex = 0;
    for(int i = 0; i < *nItems; i++){
        //se foi removido, pula para o próximo.
        if(strcmp((*listaStructs)[i].nomeModelo, "-1") == 0){
            itemIndex++;
            continue;
        }
        printf("\n%-14s %-8d %-17s %-10s %-17s %-9s %02d/%02d/%04d",
        (*listaStructs)[itemIndex].nomeModelo,
        (*listaStructs)[itemIndex].memoria,
        (*listaStructs)[itemIndex].processador,
        (*listaStructs)[itemIndex].cor,
        (*listaStructs)[itemIndex].dadosMarca.nomeMarca,
        (*listaStructs)[itemIndex].dadosMarca.pais,
        (*listaStructs)[itemIndex].dataCadastro.dia,
        (*listaStructs)[itemIndex].dataCadastro.mes,
        (*listaStructs)[itemIndex].dataCadastro.ano);
        itemIndex++;
    }
    printf("\n");
}

void removeProduto(struct Smartphone **listaStructs, int *nItems, int *qtdRemov){
    
    char remover[20];
    int cont = 0;
    printf("Digite o modelo do produto que deseja excluir: ");
    scanf(" %[^\n]", remover);
    
    for(int i = 0;i<(*nItems);i++){
        if(strcmp((*listaStructs)[i].nomeModelo, remover) == 0){
            strcpy((*listaStructs)[i].nomeModelo, "-1");
        }else{
            cont++;
        }
    }
    if(cont == (*nItems)){
        printf("Não há nenhum cadastro com esse Modelo de smartphone.");
    }else{
        printf("Produto removido com sucesso!");
        (*qtdRemov)++;
    }
}

void consultaProduto (struct Smartphone **listaStructs, int *nItems){
    char consultar[20];
    int cont = 0;
    printf("Digite o modelo do produto que deseja consultar: ");
    scanf(" %[^\n]", consultar);
    
    for(int i = 0;i<(*nItems);i++){
        if(strcmp((*listaStructs)[i].nomeModelo, consultar) == 0){
            printf("------------------------------------------------------------------------------------------------------------\n");
            printf("%-14s %-8s %-17s %-10s %-17s %-10s %s\n", "Modelo", "Memoria", "Processador", "Cor", "Marca", "País", "Data do Cadastro");
            printf("-------------- -------- ----------------- ---------- ----------------- --------- ---------------------------");    
            
            printf("\n%-14s %-8d %-17s %-10s %-17s %-9s %02d/%02d/%04d",
                (*listaStructs)[i].nomeModelo,
                (*listaStructs)[i].memoria,
                (*listaStructs)[i].processador,
                (*listaStructs)[i].cor,
                (*listaStructs)[i].dadosMarca.nomeMarca,
                (*listaStructs)[i].dadosMarca.pais,
                (*listaStructs)[i].dataCadastro.dia,
                (*listaStructs)[i].dataCadastro.mes,
                (*listaStructs)[i].dataCadastro.ano);
                break;
        }else{
            cont++;
        }
    }
    if(cont == (*nItems)){
        printf("Não há nenhum cadastro com esse Modelo de smartphone.");
    }
    
}

void relatorio(struct Smartphone **listaStructs, int *nItems, int *qtdRemov){
    FILE *relatorio = fopen("relatorio.txt", "wt");
    
    fprintf(relatorio, "%d\n", (*nItems) - (*qtdRemov));
    fprintf(relatorio, "-------------------------------------------------------------------------------------------------------------------------------------------------\n");
    fprintf(relatorio, "%-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", "Modelo", "Memoria", "Processador", "Cor", "Marca", "País", "Data do Cadastro");
    fprintf(relatorio, "-------------------- -------------------- -------------------- -------------------- -------------------- ------------------- --------------------\n");
    for (int i = 0; i < (*nItems); i++) {           
        if(strcmp((*listaStructs)[i].nomeModelo, "-1") == 0){
            continue;
        }
        fprintf(relatorio, "%-20s %-20d %-20s %-20s %-20s %-20s %02d/%02d/%04d\n",
                   (*listaStructs)[i].nomeModelo,
                   (*listaStructs)[i].memoria,
                   (*listaStructs)[i].processador,
                   (*listaStructs)[i].cor,
                   (*listaStructs)[i].dadosMarca.nomeMarca,
                   (*listaStructs)[i].dadosMarca.pais,
                   (*listaStructs)[i].dataCadastro.dia,
                   (*listaStructs)[i].dataCadastro.mes,
                   (*listaStructs)[i].dataCadastro.ano);
    }
    fclose(relatorio);
}

void cadastrar(struct Smartphone **listaStructs, int *nItems, int *qtdRemov){
    FILE *db = fopen("db.txt", "wt");
    
    fprintf(db, "%d\n", (*nItems) - (*qtdRemov));
    for (int i = 0; i < (*nItems); i++) {           
        if(strcmp((*listaStructs)[i].nomeModelo, "-1") == 0){
            continue;
        }
        fprintf(db, "%s\n%d\n%s\n%s\n%s\n%s\n%d\n%d\n%d\n",
                   (*listaStructs)[i].nomeModelo,
                   (*listaStructs)[i].memoria,
                   (*listaStructs)[i].processador,
                   (*listaStructs)[i].cor,
                   (*listaStructs)[i].dadosMarca.nomeMarca,
                   (*listaStructs)[i].dadosMarca.pais,
                   (*listaStructs)[i].dataCadastro.dia,
                   (*listaStructs)[i].dataCadastro.mes,
                   (*listaStructs)[i].dataCadastro.ano);
    }
    fclose(db);
}