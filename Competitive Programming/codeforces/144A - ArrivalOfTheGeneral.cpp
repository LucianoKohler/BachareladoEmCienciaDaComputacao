#include <bits/stdc++.h>

int main()
{
    int n; std::cin >> n;
    int soldiers[n];
    int sm = INT_MAX, smI, lg = 0, lgI;
    
    for(int i = 0; i < n; i++){
        std::cin >> soldiers[i];
        if(soldiers[i] <= sm){
            sm = soldiers[i];
            smI = i;
        }
        if(soldiers[i] > lg){
            lg = soldiers[i];
            lgI = i;
        }
    }
    
    if(lgI < smI){
        std::cout << lgI+(n-smI)-1;
    }else{
        std::cout << lgI+(n-smI)-2;
    }
    
    return 0;
}
