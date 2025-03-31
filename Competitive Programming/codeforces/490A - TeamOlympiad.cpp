#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n; cin >> n;
    int groups=0, stud;
    vector<int> ones, twos, threes;
    
    for(int i = 1; i <= n; i++){
        cin >> stud;
             if(stud == 1){  ones.push_back(i);}
        else if(stud == 2){  twos.push_back(i);}
        else              {threes.push_back(i);}
    }
    
    int minSize = min(ones.size(), min(twos.size(), threes.size()));
    
    if(minSize == 0){
        cout << "0\n";
    }else{
        cout << minSize << endl;
        for(int i = 0; i < minSize; i++){
            cout << ones[i] << " " << twos[i] << " " << threes[i] << endl;
        }
    }
    
    return 0;
}