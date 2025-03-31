x = input()[1:-1].split(", ")

if(x[0] == ''):
    print("0\n")
else:
    print(len(set(x)))