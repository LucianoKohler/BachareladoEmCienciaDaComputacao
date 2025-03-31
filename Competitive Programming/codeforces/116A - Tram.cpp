#include <iostream>

int main()
{
int capacity=0, n, in, out, qtd=0;
std::cin >> n;

for(int i = 0; i <n; i++){
    std::cin >> out >> in;
    qtd-=out;
    qtd+=in;
    if(qtd > capacity){
        capacity = qtd;
    }
}

    std::cout << capacity << std::endl;

    return 0;
}
