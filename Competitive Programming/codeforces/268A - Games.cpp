#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n; cin >> n;
    int changes = 0;
    int main[n];
    int secon[n];

    for(int i = 0; i < n; i++){
      cin >> main[i] >> secon[i];
    }

    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        if(main[i] == secon[j]){
          changes++;
        }
      }
    }

    cout << changes << endl;
    return 0;
}
