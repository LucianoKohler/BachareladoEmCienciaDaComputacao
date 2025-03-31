#include <iostream>

int main()
{
    int useless, n;
    std::string queue;
    
    std::cin >> useless >> n >> queue;
    while(n!=0){
        n--;
    
        size_t pos = queue.find("BG");
        while(pos != std::string::npos){
            queue.replace(pos, 2, "GB");
            pos = queue.find("BG", pos + 2);
        }
    }
    
    std::cout << queue << std::endl;
    return 0;
}
