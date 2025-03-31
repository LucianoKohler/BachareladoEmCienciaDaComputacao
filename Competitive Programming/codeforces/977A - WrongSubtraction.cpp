#include <iostream>

int main(){
    int a, n;
    std::cin >> a >> n;
    for(int i = 0; i < n; i++){
        
        if(a % 10==0){
            a/=10;
        }else{
            a--;
        }
    }
    
    std::cout << a << std::endl;
    
}