#include<bits/stdc++.h>
using namespace std;

int main()
{
    int n; cin >> n;
    long long x, y, k;
    while(n--){
        cin >> x >> y >> k;

        long long moves = (x+y)/k;

        if ((x+y) %k != 0){
            moves++;
        }

        
        cout << moves << endl;
        }
    return 0;
}

// Espero que o mundo todo exploda e foda-se