n = input()
a = [int(i) for i in input().split(" ")]
a = sorted(a)

for i in a:
    print(i, end=" ")
    
print()