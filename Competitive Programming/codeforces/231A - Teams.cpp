#include <iostream>

int main()
{
int n, qtd=0;
bool a, b, c;

std::cin >> n;
for(int i = 0; i < n; i++){
    std::cin>>a>>b>>c;
    if(a && b || a && c || b && c || a && b && c){
        qtd++;
    }
}

std::cout << qtd << std::endl;
    return 0;
}
