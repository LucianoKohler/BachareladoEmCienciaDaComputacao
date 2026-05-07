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

void coloreGuloso(int grafo[N][N]) {
    int resultado[N]; // cor de cada vértice
    int disponivel[N]; // cores que podemos usar 
    // (considera o pior caso, onde cada vértice possui uma cor diferente)

    // primeiro vértice tem a "cor 0", e os outros começam não pintados
    resultado[0] = 0;
    for (int i = 0; i < N; i++) {
        resultado[i] = -1; 
    }

    // começa com todas as cores disponíveis
    for (int i = 0; i < N; i++) {
        disponivel[i] = 1;
    }

    // começa a colorir os N-1 vértices restantes
    for (int u = 1; u < N; u++) {
        // marca as cors dos vizinhos como indisponíveis
        for (int v = 0; v < N; v++) {
            // se tiver uma aresta para esse vértice, e o vertice
            // possuir alguma cor x, diz que aquela cor x não está disponível 
            if (grafo[u][v] && resultado[v] != -1) {
                disponivel[resultado[v]] = 0;
            }
        }

        // procura a primeira cor disponível
        int cor;
        for (cor = 0; cor < N; cor++) if (disponivel[cor]) break;

        resultado[u] = cor; // pinta o vértice com a cor encontrada

        // reinicia a tabela de disponibilidade para a coloração do
        // próximo vértice
        for (int v = 0; v < N; v++) {
            // reverte o que fizemos no primeiro for (colocando as
            // cores dos vizinhos como disponíveis)
            if (grafo[u][v] && resultado[v] != -1) {
                disponivel[resultado[v]] = 1;
            }
        }
    }

    // encontra o número total de cores utilizadas
    int maxCores = 0;
    for (int i = 0; i < N; i++) {
        if (resultado[i] > maxCores) maxCores = resultado[i];
        printf("Vértice %d -> Cor %d\n", i, resultado[i] + 1);
    }
    // maxCores + 1 pois começamos com a "cor 0"
    printf("\nTotal de cores utilizadas: %d\n", maxCores + 1);
}


int main(){

    setlocale(LC_ALL, "pt_BR.UTF-8");

    readFile("./grafo.csv");

    clock_t inicio, fim;

    inicio = clock();
    coloreGuloso(matrizAdj);
    fim = clock();

    double tempoGastoGuloso = (double)(fim - inicio) / CLOCKS_PER_SEC;

    printf("O algoritmo guloso executou em %.8f segundo(s)\n", tempoGastoGuloso);

    return 0;
}