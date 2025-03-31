import math

a = int(input())

for i in range (0, a):
    b, c, d = [int(j) for j in input().split(" ")]
    if b == c+d or c == b+d or d == b+c:
        print("YES")
    else:
        print("NO")