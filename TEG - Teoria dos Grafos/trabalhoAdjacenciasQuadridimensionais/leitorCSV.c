#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
const int tam = 150;

int dfs(int nodo, int qtdElementosCluster, int adjacencia[tam][tam], int visitado[tam]);

int main(){
  
  // 1. Declarando as variáveis
  float data[tam][4];
  float dist[tam][tam];
  char linha[20];
  float distNorm[150][150];
  int adjacencia0_0[150][150];
  int adjacencia0_3[150][150];
  int adjacencia0_5[150][150];
  int adjacencia0_9[150][150];
  int visitado[150] = {0};
  float tolerancia; // Varia de 0 (nada passa) a 1 (tudo passa)
  
  FILE *arquivo = fopen("in/dataset.csv", "rt");
  
  // 2. Lendo o arquivo e salvando as coordenadas na matriz data
  if(arquivo == NULL){
    printf("Impossivel abrir o arquivo");
    return 1;
  }

  int i = 0;
  while(fgets(linha, sizeof(linha), arquivo) != NULL){ // Linha por linha
    char *token;
    const char delimitador[] = ",";
    token = strtok(linha, delimitador);
    int j=0;
    while(token != NULL){ // Elemento por elemento
      data[i][j] = atof(token);
      token = strtok(NULL, delimitador);
      j++;
    }
    i++;
  }

  // 3. Calculando distâncias e valores máximos/mínimos
  float min = 1e10;
  float max = 0;
  for(int i = 0; i < tam; i++){
    for(int j = 0; j < tam; j++){
      dist[i][j] = sqrt( // Distância euclidiana no plano quadridimensional
          pow(data[i][0] - data[j][0], 2.0)
        + pow(data[i][1] - data[j][1], 2.0)
        + pow(data[i][2] - data[j][2], 2.0) 
        + pow(data[i][3] - data[j][3], 2.0));
        
             if(dist[i][j] > max) max = dist[i][j];
        else if(dist[i][j] < min) min = dist[i][j];
      }
  }
  
  fclose(arquivo);

  // 4. Calculando distâncias normalizadas
  for(int i = 0; i < tam; i++){
    for(int j = 0; j < tam; j++){
      distNorm[i][j] = (dist[i][j] - min)/(max - min); 
    }
  }

  // 5. Calculando as matrizes de adjacência de acordo com os limiares (0.0, 0.3, 0.5 e 0.9)
  
  FILE *p0_0 = fopen("out/adjacenciasLimiar0_0.csv", "wt");
  FILE *p0_3 = fopen("out/adjacenciasLimiar0_3.csv", "wt");
  FILE *p0_5 = fopen("out/adjacenciasLimiar0_5.csv", "wt");
  FILE *p0_9 = fopen("out/adjacenciasLimiar0_9.csv", "wt");

  for(int i = 0; i < tam; i++){
    for(int j = 0; j < tam; j++){

      // Com tolerância zero
      if(distNorm[i][j] <= 0.0 && i != j) {
        fprintf(p0_0, "1,");
        adjacencia0_0[i][j] = 1;
      } else {
        fprintf(p0_0, "0,");
        adjacencia0_0[i][j] = 0;
      }
      
      // Com tolerância baixa
      if(distNorm[i][j] <= 0.3 && i != j) {
        fprintf(p0_3, "1,");
        adjacencia0_3[i][j] = 1;
      } else {
        fprintf(p0_3, "0,");
        adjacencia0_3[i][j] = 0;
      }

      // Com tolerância moderada
      if(distNorm[i][j] <= 0.5 && i != j) {
        fprintf(p0_5, "1,");
        adjacencia0_5[i][j] = 1;
      } else {
        fprintf(p0_5, "0,");
        adjacencia0_5[i][j] = 0;
      }

      // Com tolerância alta
      if(distNorm[i][j] <= 0.9 && i != j) {
        fprintf(p0_9, "1,");
        adjacencia0_9[i][j] = 1;
      } else {
        fprintf(p0_9, "0,");
        adjacencia0_9[i][j] = 0;
      }
    }

    fprintf(p0_0, "\n");
    fprintf(p0_3, "\n");
    fprintf(p0_5, "\n");
    fprintf(p0_9, "\n");
  }
  
  fclose(p0_0);
  fclose(p0_3);
  fclose(p0_5);
  fclose(p0_9);

  // 6. Definindo a tolerância para a análise de clusters
  printf("Escolha um limiar/tolerancia para analisar a quantidade de clusters:\n");
  printf("1 (Tolerancia 0.0)\n");
  printf("2 (Tolerancia 0.3)\n");
  printf("3 (Tolerancia 0.5)\n");
  printf("4 (Tolerancia 0.9)\n");
  printf("Sua escolha: ");
  int escolha = 0;
  scanf("%d", &escolha);

int (*adjacenciaEscolhida)[tam] = NULL;
  switch (escolha){
  case 1:
      adjacenciaEscolhida = adjacencia0_0;
    break;
    case 2:
      adjacenciaEscolhida = adjacencia0_3;
    break;
    case 3:
      adjacenciaEscolhida = adjacencia0_5;
    break;
    case 4:
      adjacenciaEscolhida = adjacencia0_9;
    break;
    default:
      printf("Tolerancia invalida.");
      return 1;
  }

  // 7. Encontrando a quantidade de clusters com o tolerância recebida
  int qtdClusters = 0;
  for(int i = 0; i < tam; i++){
    if(!visitado[i]){
      qtdClusters++;
      int elementosNoCluster = dfs(i, 1, adjacenciaEscolhida, visitado);
      printf("Cluster numero %d tem %d elementos\n", qtdClusters, elementosNoCluster);
    }
  }

  return 0;
}

int dfs(int nodo, int qtdElementosCluster, int adjacencia[tam][tam], int visitado[tam]){
  visitado[nodo] = 1;
  for(int i = 0; i < tam; i++){
    int adjAnalisada = adjacencia[nodo][i];
    if(adjAnalisada && !visitado[i]){
      qtdElementosCluster = dfs(i, qtdElementosCluster, adjacencia, visitado) + 1;
    }
  }

  return qtdElementosCluster;
}