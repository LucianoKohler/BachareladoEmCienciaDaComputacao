#include <bits/stdc++.h>
using namespace std;

const int mx = 100;      // Tamanho máximo do grid
const int max_k = 10;    // Com 10 chaves, precisamos salvar 2^10 = 1024 estados possíveis

struct Move { int row, col, mask; char dir; };
struct State { int row, col, mask; };

int gridRow, gridCol;
int startRow, startCol;
int numKeys = 0; // Quantidade total de chaves (K)

vector<vector<char>> grid(mx, vector<char>(mx, '_'));
vector<vector<int>> keyId(mx, vector<int>(mx, -1)); // Mapeia as posições das chaves para IDs (0 até K-1)

// O controle de visitados agora possui 3 dimensões: [linha][coluna][estado_das_chaves]
int distHero[mx][mx][1 << max_k];
Move parentMove[mx][mx][1 << max_k];

struct Direction { int xDir, yDir; char dir; };
Direction directions[4] = {
    { 1, 0, 'R'},
    { 0, 1, 'D'},
    {-1, 0, 'L'},
    {0, -1, 'U'}
};

bool validPosition(int row, int col) {
    if(row < 0 || row >= gridRow || col < 0 || col >= gridCol) return false;
    if(grid[row][col] == '#') return false;
    return true;
}

// Nova condição de vitória: Estar na borda E possuir TODAS as chaves
bool winCondition(int row, int col, int mask) {
    bool onBorder = (row == 0 || row == gridRow - 1 || col == 0 || col == gridCol - 1);
    bool allKeysCollected = (mask == ((1 << numKeys) - 1));
    return onBorder && allKeysCollected;
}

void solveTSPGrid() {
    // 1. Inicializa o array de distâncias com "infinito"
    for(int i = 0; i < gridRow; i++) {
        for(int j = 0; j < gridCol; j++) {
            for(int m = 0; m < (1 << numKeys); m++) {
                distHero[i][j][m] = 1e9;
            }
        }
    }

    queue<State> heroQ;
    
    // 2. O estado inicial tem máscara 0 (nenhuma chave), a menos que o jogador nasça em cima de uma
    int initialMask = 0;
    if (keyId[startRow][startCol] != -1) {
        initialMask |= (1 << keyId[startRow][startCol]);
    }

    heroQ.push({startRow, startCol, initialMask});
    distHero[startRow][startCol][initialMask] = 0;

    // 3. Execução da BFS com Bitmask
    while(!heroQ.empty()) {
        auto [row, col, mask] = heroQ.front();
        heroQ.pop();

        if(winCondition(row, col, mask)) {
            string path = "";
            int currRow = row, currCol = col, currMask = mask;
            
            // Reconstrução do caminho adaptada para a máscara de bits
            while(currRow != startRow || currCol != startCol || currMask != initialMask) {
                Move m = parentMove[currRow][currCol][currMask];
                path += m.dir;
                currRow = m.row;
                currCol = m.col;
                currMask = m.mask;
            }
            reverse(path.begin(), path.end());
            
            cout << "YES\n";
            cout << path.length() << "\n";
            cout << path << "\n";
            return;
        }

        for(auto [xDir, yDir, dir] : directions) {
            int newRow = row + yDir;
            int newCol = col + xDir;

            if(validPosition(newRow, newCol)) {
                int newMask = mask;
                
                // Se a nova célula tem uma chave, atualizamos a máscara usando o operador bitwise OR
                if (keyId[newRow][newCol] != -1) {
                    newMask |= (1 << keyId[newRow][newCol]);
                }

                // Se esse estado específico ainda não foi visitado, nós entramos nele
                if(distHero[newRow][newCol][newMask] == 1e9) {
                    distHero[newRow][newCol][newMask] = distHero[row][col][mask] + 1;
                    parentMove[newRow][newCol][newMask] = {row, col, mask, dir};
                    heroQ.push({newRow, newCol, newMask});
                }
            }
        }
    }

    cout << "NO\n"; // Caminho impossível
}

int main() {
    cin >> gridRow >> gridCol;
    
    for(int i = 0; i < gridRow; i++) {
        for(int j = 0; j < gridCol; j++) {
            cin >> grid[i][j];
            if(grid[i][j] == 'A') {
                startRow = i;
                startCol = j;
            } 
            // Agora procuramos pelas letras 'K' que representam as chaves
            else if (grid[i][j] == 'K') { 
                keyId[i][j] = numKeys; // Associa a chave atual a um ID (0, 1, 2...)
                numKeys++;
            }
        }
    }

    solveTSPGrid();    return 0;
}