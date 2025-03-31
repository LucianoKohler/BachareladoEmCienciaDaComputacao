#include<bits/stdc++.h> 

int main()
{
    std::vector<int> levels = {};
    int n, x, a;
    bool appeared;
    std::cin >> n >> a;
    
    for(int i = 0; i < a; i++){
        
        appeared = false;
        std::cin >> x;
        
        for(int i : levels){if (i == x) appeared = true;}
        
        if(!appeared && x != 0) levels.push_back(x);
    }
    
    std::cin >> a;
    for(int i = 0; i < a; i++){
        
        appeared = false;
        std::cin >> x;
        
        for(int i : levels){if (i == x) appeared = true;}
        
        if(!appeared) levels.push_back(x);
    }
    
    sort(levels.begin(), levels.end()); 

    if(levels.size() == 0){
        std::cout << "Oh, my keyboard!\n";
    }else{
        for(int i = 1; i <= n; i++){
            if(levels[i-1] != i){
                std::cout << "Oh, my keyboard!\n";
                return 0;
            }
        }
        std::cout << "I become the guy.\n"; 
    }
}
