Debug daora pro linux
```bash
for i in {1..400}; do ./a.out; done | sort | uniq -c
```

```cpp
pid_t f1, f2, f3
f1 = fork(); // processo duplicado, filho terá f1 = 0, do pai será o representador do filho
```