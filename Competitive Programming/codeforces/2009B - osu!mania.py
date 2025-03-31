c = int(input())

for i in range (0, c):
    d = int(input())
    hits = []
    
    for j in range(0,d):
        row = input()
        hits.append(row.find("#")+1)
    
    print(" ".join(map(str, reversed(hits))))