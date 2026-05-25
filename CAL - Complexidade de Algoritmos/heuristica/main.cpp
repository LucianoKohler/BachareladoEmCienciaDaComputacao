#include<bits/stdc++.h>
using namespace std;
const int qtdN = 500;
int adj[qtdN+1][qtdN+1];
int cor[qtdN+1];

void carregaGrafo(const char *fname){
    FILE *f;
    int i = 0;
    char line[1001];
    f = fopen(fname, "r");
    while(fgets(line, sizeof(line), f) != NULL){
        int j = 0;
        for(int k = 0; line[k] != '\0' && line[k] != '\n'; k++){
            char c = line[k];
            if(c == ' ') continue;
            if(c == '1') adj[i][j] = 1;
            if(c == '0') adj[i][j] = 0;
            j++;
        }
        i++;
    }
    fclose(f);
}

void pintaNodo(int nodo){
    int coresUsadas[501];
    for(int i = 0; i < 501; i++){
        coresUsadas[i] = 0;
    }

    for(int i = 0; i < qtdN; i++){
        if(i == nodo) continue;
        if(adj[nodo][i] == 1 && cor[i] != -1){
            int corDoVizinho = cor[i];
            coresUsadas[corDoVizinho] = 1;
        }
    }

    for(int i = 1; i <= qtdN; i++){
        if(coresUsadas[i] == 0){
            cor[nodo] = i;
            break;
        }
    }
}

int main(){
    fill(cor, cor + qtdN + 1, -1);
    carregaGrafo("grafo.txt");

    // grau, nodo
    vector<pair<int, int>>graus;

        // Ordenar nodos pelo grau
        for(int i = 0; i < qtdN; i++){
            int grau = 0;
            for(int j = 0; j < qtdN; j++){
                if(adj[i][j] == 1) grau++;
            }

            graus.push_back({grau, i});
        }

        // Sortando pelo grau mais alto do grafo
        sort(graus.begin(), graus.end(), greater<pair<int, int>>());

        for(auto [grau, nodo] : graus){
            pintaNodo(nodo);
        }

        for(int i = 0; i < qtdN; i++){
            cout << "Nodo " << i << " tem cor " << cor[i] << "\n"; 
        }

}