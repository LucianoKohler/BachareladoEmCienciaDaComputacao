import math

a = int(input())

for i in range (0, a):
    b, c = [int(j) for j in input().split(" ")]
    if b < c:
        temp = b
        b = c
        c = temp
    print(math.ceil((b-c)/10))