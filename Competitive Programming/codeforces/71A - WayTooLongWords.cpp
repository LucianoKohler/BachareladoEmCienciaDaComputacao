#include <iostream>
int main()
{
    int n;
    std::string word, abbreviation;
    std::cin >> n;
    for(int i = 0; i < n; i++){
        std::cin >> word;
        if(word.length() > 10){
            abbreviation = word[0] + std::to_string(word.length() - 2) + word[word.length() - 1];
        }else{
            abbreviation = word;
        }
        std::cout << abbreviation << std::endl;
    }
    
    return 0;
}
