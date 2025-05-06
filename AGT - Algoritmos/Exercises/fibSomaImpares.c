#include <stdio.h>

int fibonacci(int n) {
    if (n <= 1)
        return n;
    else
        return fibonacci(n - 1) + fibonacci(n - 2);
}

int main() {
    int n = 0;
    int soma = 0;
    int termo = 0;

    while (soma + termo <= 5000000) {
        termo = fibonacci(n);
        if (termo % 2 != 0) {
            printf("%d ", termo);
            soma += termo;
        }
        n++;
    }

    printf("\nSoma total: %d\n", soma);

    return 0;
}
