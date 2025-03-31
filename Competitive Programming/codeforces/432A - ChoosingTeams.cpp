#include<bits/stdc++.h>
using namespace std;

int main()
{
    int n, k; cin >> n >> k;
    int cand, able = 0;
    while(n--){
        cin >> cand;
        if(cand+k <=5){
            able++;
        }
    }
    
    cout << floor(able/3) << endl;
    
    
    
    
    
    return 0;
}
