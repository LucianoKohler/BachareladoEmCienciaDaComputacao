from math import floor

a, b = [int(i) for i in input().split(" ")]

if a < b:
    temp = a
    a = b
    b = temp
    
qtd=0

while b > 0:
    a-=1
    b-=1
    qtd+=1
    
print(qtd, floor(a/2))