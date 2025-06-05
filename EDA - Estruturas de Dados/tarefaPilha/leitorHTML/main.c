#include <stdio.h>
#include "arq.h"

int main(int argc, char* argv[]){
  descritor* pilha = criaPilha(50); // Cabe no máximo, 50 elementos em cada lado da multipilha
  
  if(argc < 2){ // Se não foi passado nenhum .html ao programa
    printf("Rode o programa passando um parametro junto a linha de codigo\n");
    printf("Exemplos: \n.\\leitorHTML arquivoCorreto.html \n.\\leitorHTML arquivoErrado.html");
    return 1;
  }
  
  // Vendo se o arquivo é .html
  char extensao[5];
  strncpy(extensao, argv[1] + strlen(argv[1]) - 5, 5);

  if(strcmp(extensao, ".html") != 0) {
    printf("Erro: arquivo lido nao e um .html");
    return 0;
  }

  // Por fim, lê o arquivo se tudo deu certo
  lerHTML(argv[1], pilha);    


  return 0;
}