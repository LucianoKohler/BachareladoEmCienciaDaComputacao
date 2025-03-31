#include <iostream>

int main()
{
    long long n, ans;
    std::cin >> n;
    if(n % 2 == 0){
        ans = n / 2;
    }else{
        ans = ((n + 1)/2)*-1;   
    }
    std::cout << ans << std::endl;
    return 0;
}