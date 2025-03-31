#include <iostream>

int main()
{
    int n;
    std::cin >> n;
    std::string str = "I hate ";
    
    while(n > 0){
        
        std::cout << str;
        if(str == "I hate "){
            str = "I love ";
        }else{
            str = "I hate ";
        }
        
        if(n != 1){
            std::cout << "that ";
        }else{
            std::cout << "it\n";
        }
        n--;
    }
    
    
    return 0;
}
