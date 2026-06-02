#include<bits/stdc++.h>
using namespace std;
const int nodos = 500;
int adj[nodos+1][nodos+1];
int cor[nodos+1];

// Carrega o txt no vetor adj
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

// Para colorir: Passa por todos os vizinhos e escolhe a menor cor não presente nos vizinhos
int colore(int nodo) {
    vector<int> coresUsadas(nodos + 2, 0);

    for(int i = 0; i < nodos; i++) {
        if(adj[nodo][i] == 1 && cor[i] != -1) {
            coresUsadas[cor[i]] = 1;
        }
    }

    for(int i = 1; i <= nodos; i++) {
        if(coresUsadas[i] == 0) return i;
    }
    return 1;
}

int main(){
    carregaGrafo("grafo.txt");
    fill(cor, cor + nodos + 1, -1); // Enche cor[] de -1

    // Cálculo de graus dos nodos para usar como desempate no algoritmo 
    vector<int> graus(nodos, 0);
    for(int i = 0; i < nodos; i++){
        for(int j = 0; j < nodos; j++){
            if(adj[i][j] == 1) graus[i]++;
        }
    }

    // Loop principal pra pintar todos os nodos
    for(int qtdNodosPintados = 0; qtdNodosPintados < nodos; qtdNodosPintados++) {
        int nodoEscolhido = -1;
        int qtdCoresNodo = -1;
        int grauMaximo = -1;
        int indiceNodo = -1;

        // Encontra o próximo nó ideal com base na saturação atual
        for(int i = 0; i < nodos; i++) {
            if(cor[i] != -1) continue; // Ignora os já coloridos

            // Calcula o grau de cores do nodo (quantas cores tem ao redor dele)
            set<int> coresVizinhos; // set para não dar problema com duplicatas
            for(int j = 0; j < nodos; j++) {
                if(adj[i][j] == 1 && cor[j] != -1) {
                    coresVizinhos.insert(cor[j]);
                }
            }
            int coresAtual = coresVizinhos.size();

            // Se o grau for maior, escolhe ele pra pintar, 
            // e se for igual, ve qual o maior grau como desempate
            // E SE AMBOS FOREM IGUAIS, usamos o indice como desempate definitivog
            if(coresAtual > qtdCoresNodo) {
                qtdCoresNodo = coresAtual;
                grauMaximo = graus[i];
                nodoEscolhido = i;
                indiceNodo = i;
            } else if(coresAtual == qtdCoresNodo && graus[i] != grauMaximo) {
                if(graus[i] > grauMaximo) {
                    grauMaximo = graus[i];
                    nodoEscolhido = i;
                    indiceNodo = i;
                }
            } else if(coresAtual == qtdCoresNodo && graus[i] == grauMaximo) {
                if(i > indiceNodo){
                    nodoEscolhido = i;
                }
            }
        }

        // Pinta o nó escolhido com a menor cor livre
        if(nodoEscolhido != -1) {
            cor[nodoEscolhido] = colore(nodoEscolhido);
        }
    }

    // Output
    int qtdCores = 0;
    FILE *f = fopen("output.txt", "w");
    
    for(int i = 0; i < nodos; i++){
        string s = to_string(i) + " " + to_string(cor[i]) + "\n";
        fwrite(s.data(), sizeof(char), s.size(), f);
        cout << "Nodo " << i << " tem cor " << cor[i] << "\n";
        if(cor[i] > qtdCores) qtdCores = cor[i];
    }

    cout << "Total de cores utilizadas: " << qtdCores << "\n";
    cout << "Output gerado no arquivo 'output.txt' \n";
    return 0;
}