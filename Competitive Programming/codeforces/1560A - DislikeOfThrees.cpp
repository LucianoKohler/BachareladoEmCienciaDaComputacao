#include<bits/stdc++.h>
using namespace std;

int main()
{
    int n; cin >> n;
    int num = 0;
    
    while(n--){
        int nth; cin >> nth;
        while(nth--){
            num++;
            while(num % 10 == 3 || num % 100 == 3 || num % 1000 == 3 || num % 3 == 0){
                num++;
            }
        }
        cout << num <<endl;
        num = 0;
        
    }
    return 0;
}
