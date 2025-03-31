a, b = [int(i) for i in input().split(" ")]

for i in range(0, a):
    if i%4==0 or i%4==2:
        print("#" * (b-1), end="")
    elif i%4==1:
        print("." * (b-1), end="")
    if i%4==3:
        print("#", end="")
        print("." * (b-2), end="")
        print(".")
    else:
        print("#")
        