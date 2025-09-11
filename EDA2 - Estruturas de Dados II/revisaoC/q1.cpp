/*
* Dado um vetor de números inteiros v de tamanho n e um número k, retorne 
* verdadeiro se a soma de qualquer par de números em v for igual a k.
* 
* Exemplo: dado v = [10,15,3,7] e k = 17, a saída deve ser true, pois 10 + 7 é 17
*/

#include <bits/stdc++.h>
using namespace std;
#define ll long long;

int main(){
  unordered_map<int, int>hashmap;

  int n, k;
  cin >> n >> k;

  for(int i = 0; i < n; i++){
    int val;
    cin >> val;
    if(hashmap.count(k-val)){
      auto it = hashmap.find(k-val);
      if(it != hashmap.end()){
        cout << "True" << endl;
        cout << k-it->first << " " << it->first << endl;
        return 1;
      }
    }
    hashmap[val] = i;
  }
  cout << "False" << endl;
}