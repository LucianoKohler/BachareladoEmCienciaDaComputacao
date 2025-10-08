#include <iostream>
#include <algorithm>
#define ll long long

int main(){
  int n;
  std::cout << "Insira a quantidade de valores (n): ";
  std::cin >> n;
  int arr[n];
  
  
  // Recebendo os valores do conjunto
  for(int i = 0; i < n; i++){
    std::cin >> arr[i];
  }


  // Ordenando o vetor para analisar os dados
  int tam = sizeof(arr) / sizeof(int);
  std::sort(arr, arr+tam);


  // Encontrando os pares possíveis
  int qtdPares = 0;
  for(int i = 1; i < n; i++){
    if(arr[i] == arr[i-1]){
      qtdPares++;
      std::cout << "Par possivel: Tamanho " << arr[i] << std::endl;
      i++; // Pula um valor para o corner case "3, 3, 3, 2" (onde a luva n° 2 não faz par com a luva n° 3)
    }
  }

  std::cout << "Quantidade total de pares possiveis: " << qtdPares << std::endl;  
}
