#include <iostream>

int main()
{
    int n1, n2, n3, n4, qtd=0;
    std::cin >> n1 >> n2 >> n3 >> n4;
    
    if(n1 == n2 || n1 == n3 || n1 == n4){
        qtd++;
    }
    
    if(n2 == n3 || n2 == n4){
        qtd++;
    }
    
    if(n3 == n4){
        qtd++;
    }
    
    std::cout << qtd << std::endl;
    return 0;
}
