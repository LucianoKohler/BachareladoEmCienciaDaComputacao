#include <iostream>
using namespace std;

int main(){
    int highest=0, n; cin >> n;
    int peop[n];
    
    while(n--){
        cin >> peop[n];
        if(peop[n] > highest) highest = peop[n];
    }
    
    int sum = 0;
    
    for(int i : peop){
        sum+=highest-i;
    }
    
    cout << sum << endl;
}