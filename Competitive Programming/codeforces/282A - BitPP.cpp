#include <iostream>

int main()
{
    int n, x=0;
    std::string oper;
    
    std::cin >> n;
    
    for(int i = 0; i < n; i++){
        std::cin >> oper;
        if     (oper[0] == '+'){++x;}
        else if(oper[0] == '-'){--x;}
        else if(oper[oper.length()-1] == '+'){x++;}
        else                   {x--;}
    }
    
    std::cout << x << std::endl;

    return 0;
}
