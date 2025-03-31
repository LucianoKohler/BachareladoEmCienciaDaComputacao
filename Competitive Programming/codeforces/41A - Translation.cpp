#include <iostream>

int main()
{
    std::string word, reversed;
    std::cin >> word >> reversed;
    
    for(int i = 0; i < word.length(); i++){
        if(!(word[i] == reversed[reversed.length()-1-i])){
            std::cout << "NO\n";
            return 0;
        }
    }
    std::cout << "YES\n";
    return 0;
}
