#include <stdio.h>

int main(){
    int n;
    float a, b, c;
    printf("Digite a quantidade de médias: ");
    scanf("%d", &n);

    while(n--){
        scanf("%f %f %f", &a, &b, &c);
        printf("%.1f\n", (a*2 + b*3 + c*5)/10);
    }
    
    return 0;
}