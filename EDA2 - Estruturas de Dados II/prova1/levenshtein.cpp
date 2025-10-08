#include <iostream>
#include <string>
#define ll long long

int main(){
  std::string a, b;
  std::cout << "Insira a string A: ";
  std::cin >> a;
  std::cout << "Insira a string B: ";
  std::cin >> b;
  
  int m = a.size();
  int n = b.size();

  int arr[m+1][n+1] = {0};

  for(int i = 0; i <= m; i++){
    arr[i][0] = i;
  }
  
  for(int j = 0; j <= n; j++){
    arr[0][j] = j;
  }

  int custo = 0;
  for(int i = 1; i <= m; i++){
    for(int j = 1; j <= n; j++){
      if(a[i-1] == b[j-1]){
        custo = 0;
      }else{
        custo = 1;
      }

      arr[i][j] = std::min(std::min(arr[i-1][j]+1, arr[i-1][j-1]+custo), arr[i][j-1]+1);
    }
  }

  std::cout << "Quantidade de passos: " << arr[m][n] << std::endl;
}
 