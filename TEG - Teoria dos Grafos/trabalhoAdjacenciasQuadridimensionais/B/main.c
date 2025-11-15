#include <stdio.h>
#include <string.h>
#include <math.h>
#define TAM 150

typedef struct cluster{
  int qtdElementosNoCluster;
  float mediaCoordenadas[4]; // X, Y, Z, W
  int tipoCluster;
  int qtdAcertos; // Se o nodo tipo 1 está no cluster 1
  int qtdErros; // Se o nodo tipo 1 está no cluster 2 ou 3
}cluster;

typedef struct nodo{
  float x;
  float y;
  float z;
  float w;
  int tipo;
} nodo;

void dfs(int nodo, int adjacencia[TAM][TAM], int visitado[TAM], cluster* retorno);
void calculaAdjacencias(float limiar);

// 1. Declarando as variáveis
nodo nodos[TAM];
float dist[TAM][TAM];
char linha[30];
float distNorm[150][150];
int adjacencias[150][150];
cluster clusters[TAM];
int visitado[150] = {0};
float tol; // Varia de 0 (nada passa) a 1 (tudo passa)
float tolMenor = 0; // Limitante inferior
float tolMaior = 1; // Limitante superior

int main(){  

  // 1.1. Escolha do arquivo de entrada
  FILE *arquivo = NULL;
  while(1){
    printf("Escolha a amostra de dados a ser analisada: \n");
    printf("1. Amostra boa\n");
    printf("2. Amostra ruim (casos de proximidade)\n");
    printf("Sua escolha: ");
    
    int escolha;
    scanf("%d", &escolha);
    if(escolha == 1){
      arquivo = fopen("input/rotulada.csv", "rt");
      break;
    }else if(escolha == 2){
      arquivo = fopen("input/rotuladaRuim.csv", "rt");
      break;
    }else{
      printf("Escolha invalida\n");
    }
  }

  
  // 2. Lendo o arquivo e salvando as coordenadas na matriz de nodos
  if(arquivo == NULL){
    printf("Impossivel abrir o arquivo");
    return 1;
  }
  
  int i = 0;
  while(fgets(linha, sizeof(linha), arquivo) != NULL){ // Linha por linha
    sscanf(linha, "%f,%f,%f,%f,%*[^0-9]%d", &nodos[i].x, &nodos[i].y, &nodos[i].z, &nodos[i].w, &nodos[i].tipo);
    i++;
  }


  // 3. Calculando distâncias e valores máximos/mínimos
  float min = 1e10;
  float max = 0;
  for(int i = 0; i < TAM; i++){
    for(int j = 0; j < TAM; j++){
      dist[i][j] = sqrt( // Distância euclidiana no plano quadridimensional
          pow(nodos[i].x - nodos[j].x, 2.0)
        + pow(nodos[i].y - nodos[j].y, 2.0)
        + pow(nodos[i].z - nodos[j].z, 2.0) 
        + pow(nodos[i].w - nodos[j].w, 2.0));
        
             if(dist[i][j] > max) max = dist[i][j];
        else if(dist[i][j] < min) min = dist[i][j];
      }
  }
  
  fclose(arquivo);

  // 4. Calculando distâncias normalizadas
  for(int i = 0; i < TAM; i++){
    for(int j = 0; j < TAM; j++){
      distNorm[i][j] = (dist[i][j] - min)/(max - min);
    }
  }

  // 5. Iterando para encontrar o limiar correto para separar em 3 clusters
  int qtdClusters = 0;
  int iteracao = 1;
  while(iteracao < 1000){
    qtdClusters = 0;
    tol = (tolMenor + tolMaior) / 2.0;
    calculaAdjacencias(tol);
    memset(visitado, 0, sizeof(visitado)); // Reiniciando a matriz de visitados
    memset(clusters, 0, sizeof(clusters)); // Reiniciando os clusters
    
    printf("Iteracao numero %d, limiar: %f\n", iteracao, tol);
    for(int i = 0; i < TAM; i++){
      if(!visitado[i]){
        cluster retorno = {0};
        dfs(i, adjacencias, visitado, &retorno);
        for(int j = 0; j < 4; j++){
          retorno.mediaCoordenadas[j] /= retorno.qtdElementosNoCluster;
        }
        clusters[qtdClusters] = retorno;
        qtdClusters++;
      }
    }
    iteracao++;

    if(qtdClusters > 3){
      tolMenor = tol;
    }else if(qtdClusters < 3){
      tolMaior = tol;
    }else{
      printf("Limiar encontrado: %f", tol);
      break;
    }
  }

  // 6. Analisando os dados encontrados com o limiar e salvando-os num .txt
  FILE *clusterArq = fopen("output/clusters.txt", "wt");
  printf("\n\nANALISE DOS CLUSTERS: ");
  fprintf(clusterArq, "ANALISE DOS CLUSTERS: ");

  int somaAcertos = 0;
  for(int i = 0; i < TAM; i++){
    cluster atual = clusters[i];
    if(atual.tipoCluster == 0){ break; } // Não é cluster
    somaAcertos+= atual.qtdAcertos;
    printf("\nCluster num. %d:", i+1);
    fprintf(clusterArq, "\nCluster num. %d:", i+1);
    
    printf("\n\tQuantidade de elementos no cluster: %d", atual.qtdElementosNoCluster);
    printf("\n\tCentro de gravidade do cluster: (%.2f, %.2f, %.2f, %.2f)", 
      atual.mediaCoordenadas[0], atual.mediaCoordenadas[1],
      atual.mediaCoordenadas[2], atual.mediaCoordenadas[3]);
    fprintf(clusterArq, "\n\tQuantidade de elementos no cluster: %d", atual.qtdElementosNoCluster);
    fprintf(clusterArq, "\n\tCentro de gravidade do cluster: (%.2f, %.2f, %.2f, %.2f)", 
      atual.mediaCoordenadas[0], atual.mediaCoordenadas[1],
      atual.mediaCoordenadas[2], atual.mediaCoordenadas[3]);
      
      printf("\n\tTipo do cluster: Tipo %d", atual.tipoCluster);
      printf("\n\tQuantidade de acertos: %d", atual.qtdAcertos);
      printf("\n\tQuantidade de erros: %d", atual.qtdErros);
      printf("\n\tTaxa de acerto: %.2f%%", 100*(atual.qtdAcertos / (float)atual.qtdElementosNoCluster));
      printf("\n-------------------------------------");
      fprintf(clusterArq, "\n\tTipo do cluster: Tipo %d", atual.tipoCluster);
      fprintf(clusterArq, "\n\tQuantidade de acertos: %d", atual.qtdAcertos);
      fprintf(clusterArq, "\n\tQuantidade de erros: %d", atual.qtdErros);
      fprintf(clusterArq, "\n\tTaxa de acerto: %.2f%%", 100*(atual.qtdAcertos / (float)atual.qtdElementosNoCluster));
      fprintf(clusterArq, "\n-------------------------------------");
  }

  printf("\nACURACIA TOTAL: %.2f%%", 100*(somaAcertos/(float)TAM));
  printf("\n-------------------------------------");
  fprintf(clusterArq, "\nACURACIA TOTAL: %.2f%%", 100*(somaAcertos/(float)TAM));
  fprintf(clusterArq, "\n-------------------------------------");

  printf("\n\nMatriz de adjacencia e informacoes de clusters geradas na pasta output.\n");
}

void dfs(int nodo, int adjacencia[TAM][TAM], int visitado[TAM], cluster* retorno){
  visitado[nodo] = 1;
  retorno->qtdElementosNoCluster++;
  retorno->mediaCoordenadas[0] += nodos[nodo].x;
  retorno->mediaCoordenadas[1] += nodos[nodo].y;
  retorno->mediaCoordenadas[2] += nodos[nodo].z;
  retorno->mediaCoordenadas[3] += nodos[nodo].w;

  if(retorno->tipoCluster == 0){
    retorno->tipoCluster = nodos[nodo].tipo;
  }
  if(nodos[nodo].tipo == retorno->tipoCluster){
    retorno->qtdAcertos++;
  }else{
    retorno->qtdErros++;
    }

  for(int i = 0; i < TAM; i++){
    if(adjacencia[nodo][i] && !visitado[i]){
      dfs(i, adjacencias, visitado, retorno);
    }
  }
}

void calculaAdjacencias(float limiar){
  FILE *arq = fopen("output/adjacencias.csv", "wt");
  for(int i = 0; i < TAM; i++){
    for(int j = 0; j < TAM; j++){

      if(distNorm[i][j] <= tol && i != j) {
        fprintf(arq, "1,");
        adjacencias[i][j] = 1;
      } else {
        fprintf(arq, "0,");
        adjacencias[i][j] = 0;
      }
    }
    fprintf(arq, "\n");
  }
  fclose(arq);
}