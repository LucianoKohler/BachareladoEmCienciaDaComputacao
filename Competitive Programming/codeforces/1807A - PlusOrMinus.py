n = int(input())

while(n > 0):
    a, b, c = [int(i) for i in input().split(" ")]
    if(a+b == c): print("+")
    else: print("-")
    n-=1