#include <iostream>

int main(){
    int v, n, sum=0, cops=0; std::cin >> v;
    
    for(int i = 0; i < v; i++){
        std::cin >> n;
        cops+=n;
        
        if(cops < 0){
            sum++;
            cops = 0;
        }    
    }
    std::cout << sum << std::endl;
}