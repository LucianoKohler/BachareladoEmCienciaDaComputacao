#include <iostream>

int main(){
    int a, b, years=0;
    std::cin >> a >> b;
    while(a <= b){
        b*=2;
        a*=3;
        years++;
    }
    
    std::cout << years << std::endl;
}