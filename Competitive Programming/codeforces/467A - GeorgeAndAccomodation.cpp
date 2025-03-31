#include <iostream>

int main()
{
    int n; std::cin >> n;
    int p, q, qtd=0;
    
    for(int i = 0; i < n; i++){
        std::cin >> p >> q;
        if(p+1 < q){
            qtd++;
        }
    }
    std::cout << qtd << std::endl;
    return 0;
}
