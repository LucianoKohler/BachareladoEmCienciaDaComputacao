
#include <iostream>

int main()
{
    int n; std::cin >> n;
    long long int a, b;
    for(int i = 0; i < n; i++){
        std::cin >> a >> b;
        if(b > a) std::cout << b-a << std::endl;
        else if(a%b==0) std::cout << "0\n";
        else std::cout << b-(a%b) << std::endl;
    }
    
    return 0;
}
