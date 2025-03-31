#include <iostream>

int main(){
    int a, input, highest, lowest, qtd;
    std::cin >> a;
    
    for(int i = 0; i < a; i++){
        std::cin >> input;
        if(i == 0){
            highest = input;
            lowest = input;
        }else{
            if(input > highest){
                qtd++;
                highest = input;
            }
            if(input < lowest){
                qtd++;
                lowest = input;
            }
            
            
        }
    }
    
    std::cout << qtd << std::endl;
}