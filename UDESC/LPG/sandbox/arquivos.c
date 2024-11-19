#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define N 20

int main(){
  FILE *f = fopen("teste.txt", "wt"); // Write, Text
  /*
  w - write (apaga e escreve do 0)
  r - read
  a - append (acrescenta)
  t - texto
  b - binario
  */

  int i;
  srand(time(0));
  for(int i = 1; i <= N; i++){
    int x = rand() % 1000 + 1; // 1 a 1000
    fprintf(f, "%d\n", x); // Arquivo, formato do print, variavel
  }

  fclose(f);
}