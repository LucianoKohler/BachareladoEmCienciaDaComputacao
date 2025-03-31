a = int(input())
summ=0
while a > 0:
    thing = input()
    if(thing == "Tetrahedron"):
        summ+=4
    elif(thing == "Cube"):
        summ+=6
    elif(thing == "Octahedron"):
        summ+=8
    elif(thing == "Dodecahedron"):
        summ+=12
    else:
        summ+=20
    a-=1
print(summ)