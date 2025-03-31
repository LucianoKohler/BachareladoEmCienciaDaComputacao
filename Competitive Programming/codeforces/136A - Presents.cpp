#include<bits/stdc++.h>
using namespace std;

int main()
{
    int n; cin >> n;
    int m;
    int a[n+1];
    
    for(int i = 1; i <= n; i++)
    {
        cin >> m;
        a[m] = i;
    }
    
    cout << a[1] << " ";
    
    for(int i = 2; i <= n; i++)
    {
        cout << a[i] << " ";
    }
    cout << endl;
    return 0;
}

// This is NOT 800 difficulty.