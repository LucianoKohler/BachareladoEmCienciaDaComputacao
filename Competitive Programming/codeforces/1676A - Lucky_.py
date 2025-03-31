n = int(input())

while(n>0):
    num = input()
    if int(num[0]) + int(num[1]) + int(num[2]) == int(num[3]) + int(num[4]) + int(num[5]):
        print("YES")
    else:
        print("NO")
    n-=1