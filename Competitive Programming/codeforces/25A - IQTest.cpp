#include <iostream>

int main(){
    int a, even=0, odd=0;
    std::cin >> a;
    
    int inputs[a];
    
    for(int i = 0; i < a; i++){
        std::cin >> inputs[i];
        
        if(i == 0 || i == 1 || i == 2){
            if(inputs[i]%2==0){
                even++;
            }else{
                odd++;
            }
        }
    }

    for(int i = 0; i < a; i++){
        if((odd > even && inputs[i]%2==0) || (even > odd && inputs[i]%2==1)){
            std::cout << i+1 << std::endl;
        }
    }
}