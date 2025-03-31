
#include <iostream>

int main()
{
    bool condition = false;
    int year;
    std::string yearS;
    std::cin >> year;
    
    while(!condition){
        year++;
        yearS = std::to_string(year);
        if(
            yearS[0] != yearS[1] &&
            yearS[0] != yearS[2] &&
            yearS[0] != yearS[3] &&
            yearS[1] != yearS[2] &&
            yearS[1] != yearS[3] &&
            yearS[2] != yearS[3]
        ){condition = true;}
    }
    
    std::cout << year << "\n";

    return 0;
}
