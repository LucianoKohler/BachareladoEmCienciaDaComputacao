#include<bits/stdc++.h>
using namespace std;

int main()
{
    long long n; cin >> n;
    long long prices[n];
    
    for(long long i = 0; i < n; i++){
        cin >> prices[i];
    }
    
    long long q; cin >> q;
    for(long long i = 0; i < q; i++){
        long long todayCoins;
        int canBuy=0;
        cin >> todayCoins;
        
        for(int price : prices){
            if(price <= todayCoins){
                canBuy++;
            }
        }
        cout << canBuy <<endl;
    }
    return 0;
}
