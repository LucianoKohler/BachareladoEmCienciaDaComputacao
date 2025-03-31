#include <iostream>

int main(){
    std::string results;
    int USELESS, A=0, D=0;
    
    std::cin >> USELESS;
    std::cin >> results;
    for(char i : results){
        if (i == 'A'){
            A++;
        }else{
            D++;
        }
    }
    
         if(A > D){std::cout << "Anton\n";}
    else if(D > A){std::cout << "Danik\n";}
    else          {std::cout << "Friendship\n";}
    
}
