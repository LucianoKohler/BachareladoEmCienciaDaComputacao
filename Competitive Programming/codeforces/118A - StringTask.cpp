#include <iostream>
#include <string>
int main(){
    std::string word;
    std::cin >> word;
    for(int i = 0; i < word.length(); i++){
        if(65 <= word[i] && word[i] <= 90){
            word[i]+=32;
        }
    }
    
    for(int i = 0; i < word.length(); i++){
        if(word[i] == 'a' ||
           word[i] == 'e' || 
           word[i] == 'i' || 
           word[i] == 'o' || 
           word[i] == 'u' || 
           word[i] == 'y'){
            word.erase(word.begin()+i);
            i--;
        }
    }
    for(char i : word){
        std::cout << "." << i;
    }    
    std::cout << std::endl;
    return 0;
}
