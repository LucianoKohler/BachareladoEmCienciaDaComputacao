/*
* Dado um vetor de números inteiros v, encontre o primeiro inteiro positivo ausente no
* vetor. Em outras palavras, deve ser retornado o menor inteiro positivo que não existe no
* vetor. A matriz pode conter duplicados e números negativos também. O algoritmo deve
* apresentar complexidade de tempo linear e de espaço constante (pode desconsiderar
* esforço para ordenação do vetor).
* Exemplo 1, dado v = [3,4,-1,1], a saída esperada é 2
* Exemplo 2, dado v = [1,2,0], a saída esperada é 3
*/

#include <bits/stdc++.h>
using namespace std;
#define ll long long;

int main(){
  int missing = 1;
  int n;
  cin >> n;

  for(int i = 0; i < n; i++){
    int val;
    cin >> val;
    if(val == missing){
      missing++;
    }
  }
  cout << "False" << endl;
}