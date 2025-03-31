import math

n = int(input())

while(n>0):
    num=int(input())
    print(math.ceil(num/2)-1)
    n-=1