#include <bits/stdc++.h>

int main()
{
    int notes[] = {100,20,10,5,1};
    int n, qtd; std::cin >> n;
    
    for(int i = 0; i < 5; i++){
        if(n >= notes[i]){
            n-=notes[i];
            qtd++;
            i--;
        }
    }
    
    std::cout << qtd << std::endl;
    return 0;
}
