#include <stdio.h>
#include <stdlib.h>
#define N 61

// Evita pisar fora do grid ou em paredes
int isSafe(int maze[N][N], int x, int y) {
    return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 0);
}

int solveMazeUtil(int maze[N][N], int x, int y) {
    // Chegou no final (caso base)
    if (x == N-1 && y == N-1 && maze[x][y] == 0) {
        maze[x][y] = 2;
        return 1;
    }

    // Posso andar aqi (caso base)
    if (isSafe(maze, x, y) && maze[x][y] != 2) {
        maze[x][y] = 2;

        // Recursão pro backtracking
        if (solveMazeUtil(maze, x+1, y)) return 1;
        if (solveMazeUtil(maze, x-1, y)) return 1;
        if (solveMazeUtil(maze, x, y+1)) return 1;
        if (solveMazeUtil(maze, x, y-1)) return 1;

        // Se as funções acima não retornaram 1 em nenhum caso, 
        // O caminho tomado foi errado, então ele apaga o caminho
        maze[x][y] = 0;
        return 0;
    }

    return 0;
}

// Printa o labirinto à partir do 0, 1 e 2 do maze[][]
void printMaze(int maze[N][N]){
    printf("Caminho no labirinto:\n");
    printf("%c ",219);
    for (int j = 1; j < N+1; j++) {
        printf("%c",219);
    }
    printf("\n");
    for (int i = 0; i < N; i++) {
        printf("%c",219);
        for (int j = 0; j < N; j++) {
            if(maze[i][j] == 1){
                printf("%c",219);
            }else if(maze[i][j] == 2){
                printf("%c", 248);
            }else{
                printf(" ");
            }
        }
        printf("%c\n",219);
    }
    for (int j = 0; j < N; j++) {
        printf("%c",219);
    }
    printf(" %c\n",219);
}

// Verifica se ele é solucionável ou não e printa o caminho se sim
int solveMaze(int maze[N][N]) {

    if (!solveMazeUtil(maze, 0, 0)) return 0;

    printMaze(maze);
    return 1;
}

// Pega o texto do arquivo fname e transfere para um vetor
void loadMaze(int maze[N][N], char *fname){
    // Gerado à partir do dcode.fr/maze-generator, lembrar de passar N = 61 no site!!
    FILE *f;
    int i, lineN = 0;
    char line[N+10];
    f = fopen(fname, "r");
    while(fgets(line, sizeof(line), f) != NULL){
        i = 0;
        if(line[0]=='0' || line[0] == '1'){
            while(line[i] != '\0'){
                maze[lineN][i] = line[i] - '0';
                i++;
                if(i>=N) break;
            }
            lineN++;
            if(lineN>=N){ break;}
        }
    }

    printf("i: %d, LineN: %d\n", i, lineN);

}

int main() {
    /*
    0 = vazio
    1 = parede
    2 = caminho
    */

    int maze[N][N];

    loadMaze(maze, "lab.txt");

    if (solveMaze(maze)) {
        printf("Labirinto resolvido!\n");
    } else {
        printf("Nao foi possivel encontrar uma solucao.\n");
    }

    return 0;
}

