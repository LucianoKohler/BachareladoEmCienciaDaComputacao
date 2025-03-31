#include <iostream>

int main(){
    int a, b, dominoes=0;
    std::cin >> a >> b;
    int area = a*b;
    
    while(area>=2){
        area-=2;
        dominoes++;
    }
    
    std::cout << dominoes << std::endl;
}
