#include <iostream>
#include <string>
int main()
{
int useless, qtd;
bool R=false, G=false, B=false;
std::string str;
std::cin >> useless;
std::cin >> str;

for(int i = 0; i < str.length(); i++){
    if(str[i] == 'R'){
        if(R == true){
            str.erase(str.begin()+i);
            i--;
            qtd++;
        }else{
            R = true;
            G = false;
            B = false;
        }
    }else if(str[i] == 'G'){
        if(G == true){
            str.erase(str.begin()+i);
            i--;
            qtd++;
        }else{
            R = false;
            G = true;
            B = false;
        }    
        
    }else{
        if(B == true){
            str.erase(str.begin()+i);
            i--;
            qtd++;
        }else{
            R = false;
            G = false;
            B = true;
        }    
    }
    
}
    std::cout << qtd << std::endl;

    return 0;
}
