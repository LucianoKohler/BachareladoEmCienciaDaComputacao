#include <iostream>

int main()
{
    int low=0, high=0;
    std::string word;
    std::cin >> word;
    
    for(char i : word){
        if(i <= 90){
            high++;
        }else{
            low++;
        }
    }
    
    if(high > low){
        for(int i = 0; i < word.length(); i++){
            if(word[i] > 90){
                word[i]-=32;
            }
        }
    }else{
        for(int i = 0; i < word.length(); i++){
            if(word[i] < 91){
                word[i]+=32;
            }
        }
    }
    
    std::cout << word << std::endl;
    return 0;
}
