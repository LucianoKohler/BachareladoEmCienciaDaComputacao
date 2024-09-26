#include <stdio.h>
#include <stdio.h>

void fibonacci(int v[], int n){
    v[0] = 0;
    v[1] = 1;

    for (int i = 2; i < n; i++) {
        v[i] = v[i - 1] + v[i - 2];
    }

}

int main() {
    int n;
    printf("Digite o número de termos da sequência: ");
    scanf("%d", &n);

    int v[n];
    fibonacci(v, n);

    printf("Sequência: ");
    for (int i = 0; i < n; i++) {
        printf("%d, ", v[i]);
    }

    return 0;
}