#include <stdio.h>

int main(){
    float a, b, c;
    printf("Digite a, b e c: ");
    scanf("%f %f %f", &a, &b, &c);

    if(c > b){
        float temp = c;
        c = b;
        b = temp;}
    if(b > a){
        float temp = b;
        b = a;
        a = temp;
    }

    if(a >= b + c){             printf("NAO FORMA TRIANGULO\n");
    }else if(a*a == b*b + c*c){ printf("TRIANGULO RETANGULO\n");
    }else if(a*a > b*b + c*c){  printf("TRIANGULO OBTUSANGULO\n");
    }else if(a*a < b*b + c*c){  printf("TRIANGULO ACUTANGULO\n");
    }else if(a == b && b == c){ printf("TRIANGULO EQUILATERO\n");
    }else{                      printf("TRIANGULO ISOSCELES\n");
    }
    
    return 0;
}