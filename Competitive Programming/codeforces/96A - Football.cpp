#include <iostream>

int main()
{
std::string str;
std::cin >> str;
int ones=0, zeroes=0;

    for(char i : str){
        if(i == '1'){
            ones++;
            zeroes = 0;
        }else{
            zeroes++;
            ones = 0;
        }
        if(ones == 7 || zeroes == 7){
            std::cout << "YES\n";
            return 0;
        }
    }
    std::cout << "NO\n";
    return 0;
}
