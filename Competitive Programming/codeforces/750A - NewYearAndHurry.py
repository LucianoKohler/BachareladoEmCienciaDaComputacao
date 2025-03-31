# 20:00 - 00:00 = 240 min

a, b = [int(i) for i in input().split(" ")]

time = 240-b
problems = 0

while time >= 0:
    problems+=1
    time-=5*problems
    
if problems > a:
    print(a)
else:
    print(problems-1)