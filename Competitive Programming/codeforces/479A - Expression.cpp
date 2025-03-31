#include <bits/stdc++.h>

int main()
{
    
int a,b,c,res[6];
std::cin >> a >> b >> c;
res[0] = a+b+c;
res[1] = a*b+c;
res[2] = a*(b+c);
res[3] = a+b*c;
res[4] = (a+b)*c;
res[5] = a*b*c;

std::cout << *std::max_element(res, res+6) << std::endl;
    return 0;
}
