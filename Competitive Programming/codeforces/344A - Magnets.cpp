#include <iostream>

int main()
{
    int n; std::cin >> n;
    int qtd=0;
    std::string magnet, last;

    for(int i = 0; i < n; i++){
        std::cin >> magnet;
        if(i == 0){
            last = magnet;
            qtd++;
            continue;
        }
        
        if(magnet != last){
            last = magnet;
            qtd++;
        }
        
    }
    
    std::cout << qtd << std::endl;

    return 0;
}
