#include<bits/stdc++.h> 

int main()
{
    std::vector<char> letters = {};
    int n; std::cin >> n;
    bool appeared;
    
    std::string word;
    std::cin >> word;
    
    for(int a = 0; a < word.size(); a++){
        word[a] = tolower(word[a]);
    }
    
    for(char i : word){
        appeared = false;
        for(char j : letters){
            if(i == j){
                appeared = true;
            }
        }
        if(!appeared){
            letters.push_back(i);
        }
    }
    
    letters.size() == 26 ? std::cout << "YES\n" : std::cout << "NO\n";
    return 0;
}
