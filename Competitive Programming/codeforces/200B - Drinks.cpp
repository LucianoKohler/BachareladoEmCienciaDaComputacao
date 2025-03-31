#include <iostream>

int main()
{
    int n; std::cin >> n;
    int perc;
    float total=0;
    for(int i = 0; i < n; i++){
        std::cin >> perc;
        total+=perc;
    }
    total/=n;
    
    std::cout << total << std::endl;
    
    return 0;
}
