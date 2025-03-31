#include <iostream>

int main()
{
    int cost, money, qtd, price=0;
    std::cin >> cost >> money >> qtd;
    for(int i = 1; i <= qtd; i++){
        price+=cost*i;
    }
    
    if(price - money < 0){
        std::cout << "0\n";
    }else{
        std::cout << price - money << std::endl;
    }
    
    
    return 0;
}
