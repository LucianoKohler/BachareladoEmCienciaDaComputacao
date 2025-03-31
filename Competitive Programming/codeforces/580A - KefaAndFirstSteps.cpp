#include <iostream>

int main()
{
    int n; std::cin >> n;
    int a=0, b, seq=0, biggSeq=0;
    
    while(n--){
        std::cin >> b;
        if(b >= a){
            seq++;
        }else{
            seq = 1;
        }
        
        a = b;
        
        if(seq > biggSeq) biggSeq = seq;
    }
    
    std::cout << biggSeq << std::endl;

    return 0;
}
