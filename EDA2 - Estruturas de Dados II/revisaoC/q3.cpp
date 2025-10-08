/*Dado um vetor de números inteiros v, encontre o primeiro inteiro positivo ausente no
* vetor. Em outras palavras, deve ser retornado o menor inteiro positivo que não existe no
* vetor. A matriz pode conter duplicados e números negativos também. O algoritmo deve
* apresentar complexidade de tempo linear e de espaço constante (pode desconsiderar
* o esforço para ordenação do vetor).
* ○ Exemplo 1, dado v = [3,4,-1,1], a saída esperada é 2
* ○ Exemplo 2, dado v = [1,2,0], a saída esperada é 3
*/


#include <bits/stdc++.h>
using namespace std;
#define ll long long

int n;

/* Em resumo:
  Passa pelo array já recebido e compara v[i]:
    
  se v[i] < 0, ignora, pois queremos o primeiro int positivo
    
    se v[i] > n, ignora, pois se temos n elementos e um elemento maior que n,
    temos um número entre 1 e n que sumiu, logo não pode ser o maior que o 
    próprio array

    Aí colocamos os restos no indice do próprio valor (exemplo v[2] = 2)
    
    Por fim, passamos mais uma vez e achamos o primeiro
    elemento que não é igual ao índice
*/
int findMissingInt(int *v){
  for(int i = 0; i < n; i++){
    while(v[i] > 0 && v[i] <= n && v[i] != v[v[i]-1]){
      swap(v[i], v[v[i]-1]);
    }
  }

  for(int i = 1; i < n; i++){
    if(v[i] != i+1) return i+1;
  }

  return n+1;
}

int main(){
  cin >> n;
  int arr[n];

  for(int i = 0; i < n; i++) cin >> arr[i];

  cout << findMissingInt(arr);
}