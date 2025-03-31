#include <iostream>

int main()
{
    std::string str;
    std::cin >> str;
    
    for(char i : str){
        if(i == 'H' || i=='Q'|| i=='9'){
            std::cout << "YES\n";
            return 0;
        }
    }
    
    std::cout << "NO\n";
    return 0;
}
