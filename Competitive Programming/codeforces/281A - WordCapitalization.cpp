#include <iostream>

int main()
{
    std::string word;
    std::cin >> word;
    
    
    if(word[0] > 96 && word[0] < 123){
        word[0] = word[0] - 32;
    }
    
    std::cout << word << std::endl;

    return 0;
}
