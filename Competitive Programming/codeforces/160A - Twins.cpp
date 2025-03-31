#include <iostream>
#include <vector>
#include <algorithm>
int main()
{
    int input, myCoins=0;
    float total=0, myCoinsValue=0;
    int n; std::cin >> n;
    std::vector<int> coins;

    for(int i = 0; i < n; i++){
        std::cin >> input;
        coins.push_back(input);
        total+=input;
    }
    
    std::sort(coins.begin(), coins.end());
    std::reverse(coins.begin(), coins.end()); 
    
    for(int i : coins){
        if(myCoinsValue<=total/2){
            myCoins++;
            myCoinsValue+=i;
        }
    }
    
    std::cout << myCoins << std::endl;
    return 0;
}
