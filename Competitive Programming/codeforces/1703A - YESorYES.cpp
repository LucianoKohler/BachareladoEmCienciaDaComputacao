#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n; cin >> n;
    string yes;
    while(n--){
        cin >> yes;
        if((yes[0] == 'Y' || yes[0] == 'y') 
        && (yes[1] == 'E' || yes[1] == 'e') 
        && (yes[2] == 'S' || yes[2] == 's')){
            cout << "YES\n";
        }else{
            cout << "NO\n";
        }
    }
    return 0;
} 