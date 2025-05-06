#include <stdio.h>
#include <stdlib.h>

int main(){
  FILE *f = fopen("texto.txt", "wt"); // Write, Text
  /*
  w - write (apaga e escreve do 0)
  r - read
  a - append (acrescenta)
  t - texto
  b - binario
  */

    // Criando e inserindo
	fprintf(f, "teste");

	fclose(f);

    // Abrindo, extraindo uma str e printando
	f = fopen("texto.txt", "rt");
	
	char str[10];
	fscanf(f, "%s", str);

	printf("%s", str);
	fclose(f);
}