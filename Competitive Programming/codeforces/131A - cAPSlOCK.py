a = input()

if a[1:] == a[1:].upper():
    if a[0] == a[0].lower():
        a = a.capitalize()
    else:
        a = a.lower()
        
print(a)
