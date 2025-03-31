#include <iostream>

int main()
{
    std::string word;
    std::cin >> word;
    
        for(int i = 0; i < word.length(); i++){
            switch(word[i]){
                case 'h':
                    if(i != 0){
                        word.erase(i, 1);
                        i--;
                    }
                    
                    break;
                case 'e':
                    
                    if(i != 1){
                        word.erase(i, 1);
                        i--;
                    }
                    
                    break;
                case 'l':
                    
                    if(i != 2 && i != 3){
                        word.erase(i, 1);
                        i--;
                    }
                    
                    break;
                case 'o':
                    if(i != 4){
                        word.erase(i, 1);
                        i--;
                    }
                    
                    break;
                default:
                    word.erase(i, 1);
                    i--;
            }
        }
    
    if(word == "hello"){
        std::cout << "YES\n";
    }else{
        std::cout << "NO\n";
    }
    return 0;
}
