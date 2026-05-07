#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <locale.h>

#define N 20

int matrizAdj[N][N];

void readFile(char* fileName){
    FILE* f = fopen(fileName, "r");
    char line[100];

    int row = 0;
    while(fgets(line, sizeof(line), f)){
        char *token = strtok(line, ",");
        int col = 0;
        while(token != NULL){
            matrizAdj[row][col] = atoi(token);
            token = strtok(NULL, ",");
            col++;
        }
        row++;
    }
}

void printMatrix(){
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
            printf("%d ", matrizAdj[i][j]);
        }
        printf("\n");
    }
}

int corLivre(int grafo[N][N], int cores[], int vertice, int cor) {
    // verifica cada vértice do grafo
    for (int i = 0; i < N; i++) {
        // verifica se o vértice sendo analisado tem adjacência com o vértice que queremos colorir, e, caso possua, se ele tem a mesma cor que queremos colorir o vértice
        if (grafo[vertice][i] && cores[i] == cor) {
            return 0;
        }
    }
    return 1;
}


int coloreGrafo(int grafo[N][N], int cores[], int vertice, int m) {
    // verifica se já passamos por todos os vértices do grafo
    if (vertice == N) {
        return 1;  
    }

    // tenta colorir com alguma cor de 1 até 'm'
    // (sendo 'm' o máximo de cores que podemos ter)
    for (int cor = 1; cor <= m; cor++) {
        // verifica se a cor que estamos pode ser 
        //utilizada para pintar o vértice que estamos
        if (corLivre(grafo, cores, vertice, cor)) {
            cores[vertice] = cor;
            // caso verdadeiro, aplica a recursividade
            if (coloreGrafo(grafo, cores, vertice + 1, m)) {
                return 1;
            }
            // se aplicou a recursividade, e não conseguiu pintar
            // o grafo inteiro, tenta com uma outra cor (cor + 1)
            cores[vertice] = 0; 
        }
    }
    // retorna 0 se não foi possível colorir com m cores
    return 0;  
}


int main(){

    setlocale(LC_ALL, "pt_BR.UTF-8");

    readFile("./grafo.csv");
    
    // se cores[i] == 0, significa que o nodo 'i' não foi pintado.
    // se cores[i] == a, significa que ele foi pintado com a cor 'a'
    clock_t inicio, fim;

    inicio = clock();

    int m, cores[N] = {0};

    for (m = 1; m <= N; m++) {
        // "limpa" as cores da tentativa de pintar com m-1 cores
        for (int i = 0; i < N; i++) {
            cores[i] = 0; 
        }
        // se ele conseguir colorir o grafo, muda resultado para 1 e sai do loop
        if (coloreGrafo(matrizAdj, cores, 0, m)) {
            break;
        }
    }

    fim = clock();

    double tempoGastoBacktracking = (double)(fim - inicio) / CLOCKS_PER_SEC;

    printf("Distribuição de cores por vértice:\n");
    for (int i = 0; i < N; i++) {
        printf("Vértice %d -> Cor %d\n", i, cores[i]);
    }

    printf("É possível pintar o grafo dado com %d cor(es)!\n", m);
    printf("O algoritmo de backtracking executou em %.8f segundo(s)\n", tempoGastoBacktracking);

    return 0;
}