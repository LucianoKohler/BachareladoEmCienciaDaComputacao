#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n; cin >> n;
    int number;
    
    while(n--){
        cin >> number;
        string numb = to_string(number);

        int moves = 0;
        for(char c : numb){
            if(c != '0'){
                moves++;
            }
        }
        
        cout << moves <<endl;
        for(int i = 0; i < numb.length(); i++){
            
            if(numb[i] == '0'){ continue; }
            cout << numb[i];
            int trailingZeros = numb.length()-i-1;
            for(int j = 0; j < trailingZeros; j++){
                cout << "0";
            }
            cout << " ";
            
        }
        
        cout << endl;
    }
    return 0;
}
