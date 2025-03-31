#include <iostream>

int main()
{
int n, h, person, qtd=0;
std::cin >> n >> h;

for(int i = 0; i < n; i++){
    std::cin >> person;
    if(person > h){
        qtd+=2;
    }else{
        qtd++;
    }
}

    std::cout << qtd << std::endl;
    return 0;
}
