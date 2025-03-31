#include <iostream>

int main()
{
    int n, op;
    std::cin >> n;
    
    for(int i = 0; i < n; i++){
        std::cin >> op;
        if(op == 1){
            std::cout << "HARD\n";
            return 0;
        }
    }
    std::cout << "EASY\n";
    return 0;
}
