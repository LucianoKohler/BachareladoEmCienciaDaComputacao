#include <iostream>

int main()
{
    int val, qtd=0;
    std::cin >> val;
    while(val >=5){
        val-=5;
        qtd++;
    }
    if(val > 0){
        qtd++;
    }
    
    std::cout << qtd << std::endl;
    
    return 0;
}
