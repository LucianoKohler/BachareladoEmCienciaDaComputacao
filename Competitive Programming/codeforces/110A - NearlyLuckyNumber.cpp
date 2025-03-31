#include <iostream>

int main()
{
int qtd=0;
std::string num;
std::cin >> num;
for(char i : num){
    if(i == '4' || i == '7'){
        qtd++;
    }
}

if(qtd == 4 || qtd == 7){
    std::cout << "YES\n";
}else{
    std::cout << "NO\n";
}


    return 0;
}
