/*
* Dado um vetor de números inteiros v, retorne um novo vetor de forma que cada
* elemento no índice i seja o produto de todos os números na matriz original, com
* exceção de i.
* ○ Exemplo 1: dado v = [1,2,3,4,5], a saída esperada é [120,60,40,30,24]
* ○ Exemplo 2: dado v = [3,2,1], a saída esperada é [2,3,6]
*/

#include <bits/stdc++.h>
using namespace std;
#define ll long long

int main(){
  int n;
  ll prod = 1;
  cin >> n;
  
  ll arr[n];
  ll newArr[n];

  for(int i = 0; i < n; i++){
    cin >> arr[i];
    prod*=arr[i];
  }

  for(int i = 0; i < n; i++){
      newArr[i] = (arr[n] != 0 ? prod/arr[i] : prod);

    cout << newArr[i] << " ";
  }
}
