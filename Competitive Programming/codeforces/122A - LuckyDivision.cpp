#include <iostream>

int main()
{
    int n;
    std::string ans = "NO\n";
    std::cin >> n;
    
    int luckies[] = {4, 7, 44, 47, 74, 77, 444, 447, 474, 477, 744, 747, 774, 777};
    
    for(int i : luckies){
        if(n%i==0){
            ans = "YES\n";
            break;
        }
    }
    
    std::cout << ans;
    return 0;
}
