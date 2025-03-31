#include <iostream>

int main()
{
    int n, x=0, y=0, z=0, num;
    
    std::cin >> n;
    
    for(int i = 0; i < n; i++){
        std::cin >> num;
        x+=num;   
        std::cin >> num;
        y+=num;   
        std::cin >> num;
        z+=num;   
    }
    
    if(x == 0 && y == 0 && z == 0){
        std::cout << "YES\n";
    }else{
        std::cout << "NO\n";
    }
    return 0;
}
