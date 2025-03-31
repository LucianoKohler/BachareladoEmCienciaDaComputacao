#include <bits/stdc++.h>
using namespace std;

int main(){
    int n; cin >> n;
    vector<int> arr;
    
    for(int i = 0; i < n; i++){
        int a;
        cin >> a;
        arr.push_back(a);
    }
    
    sort(arr.begin(), arr.end(), greater<int>());
    
    int buses = 0;
    
    while(!arr.empty()){
        int capacity=4;
        for(int i = 0; i < arr.size(); i++){
            if(arr[i] <= capacity){
                capacity-=arr[i];
                arr.erase(arr.begin() + i);
                i--;
            }
            
            if(capacity == 0){
                break;
            }
        }
        
        buses++;
    }
    
    cout << buses << endl;
    
}