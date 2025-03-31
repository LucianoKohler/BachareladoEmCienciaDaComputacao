#include <iostream>

int main()
{
    int n, k, qtd=0;
    
    std::cin >> n >> k;
    int vals[n];
    
    for(int i = 0; i < n; i++){
        std::cin >> vals[i];
    }
    
    for(int i = 0; i < n; i++){
        if(vals[i] >= vals[k - 1] && vals[i] > 0){
            qtd++;
        }
    }
    
    std::cout << qtd << std::endl;
    return 0;
}
