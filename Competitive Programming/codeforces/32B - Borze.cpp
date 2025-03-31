#include <bits/stdc++.h>
using namespace std;

int main()
{
    string bor;
    cin >> bor;
    int i=0;
    while(true){
        
        if(i == bor.length()){
            break;
        }
        
        if(bor[i] == '-' && bor[i+1] == '.'){
            cout << "1";
            i+=2;
        }else if(bor[i] == '-' && bor[i+1] == '-'){
            cout << "2";
            i+=2;
        }else{
            cout << "0";
            i+=1;
        }
    }
    
    cout << endl;
    
    return 0;
}