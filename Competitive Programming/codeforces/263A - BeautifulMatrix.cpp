#include <iostream>

int main()
{
    int temp, moves=0;
    int n[5][5];
    
    for(int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
            std::cin >> n[i][j];
        }
    }
    
    while(n[2][2] != 1){
        for(int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
            if(n[i][j] == 1 && i > 2){
                temp = n[i-1][j];
                n[i-1][j] = n[i][j];
                n[i][j] = temp;
                moves++;
            }else if(n[i][j] == 1 && i < 2){
                temp = n[i+1][j];
                n[i+1][j] = n[i][j];
                n[i][j] = temp;
                moves++;
            }else if(n[i][j] == 1 && j > 2){
                temp = n[i][j-1];
                n[i][j-1] = n[i][j];
                n[i][j] = temp;
                moves++;
            }else if(n[i][j] == 1 && j < 2){
                temp = n[i][j+1];
                n[i][j+1] = n[i][j];
                n[i][j] = temp;
                moves++;
            }
        }
    }
    }
    
    std::cout << moves << std::endl;
    return 0;
}
