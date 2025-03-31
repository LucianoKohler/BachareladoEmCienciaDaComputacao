#include <iostream>

int main()
{
    std::string n1, n2, newStr = "";
    std::cin >> n1 >> n2;
    
    for(int i = 0; i < n1.length(); i++){
        if(n1[i] == n2[i]){
            newStr += "0";
        }else{
            newStr += "1";
        }
    }
    
    std::cout << newStr << std::endl;
    return 0;
}
