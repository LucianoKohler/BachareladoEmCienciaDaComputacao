#include "arq.h"


int main(void)
{
  struct descF *ptr = NULL; 

  if((ptr=cria(sizeof(info))) == FRACASSO)
      puts("erro fatal durante a cria��o do tda");
  else
  	menu(ptr);
  return 1;
}

void menu(struct descF *p)
{  char opc;
   info auxInfo;

   do{ fflush(stdin);
   puts(" entre com uma opcao 1 (busca na frente), 2 (enfileira),");
   puts("                      3 (remove da fila),4 (busca na cauda), 5 purga");
   puts("                      \"zero\" para encerrar");
   printf("\n");
	opc = getchar();

	switch (opc)
	{ case '1':
               if(buscaNaFrente(&auxInfo,p)==FRACASSO) puts("fila vazia!");
               else
                printf("\n consultado : %i \n", auxInfo.chave);
		         break;
     case '2':
            printf("\nentre com o valor de prioridade:\n");
            fflush(stdin);
            scanf("%i", &auxInfo.chave);
            if(insere(&auxInfo,p,compara)==FRACASSO)
             { puts("erro na insercao: estrutura de dados cheia !");
              
             }
            break;
     case '3':

            if(remove_(&auxInfo,p)==FRACASSO) 
               puts("fila vazia!");
            else
               printf("\n item retirado : %i \n", auxInfo.chave);
            break;
     case '4':

            if(buscaNaCauda(&auxInfo,p)==FRACASSO)
               printf("fila vazia!");
            else
               printf("\n consultado : %i \n", auxInfo.chave);
		      break;
     case '5' :
            reinicia(p);
            puts("limpou a fila !!!");
            break;


        }
        getchar();
   }while(opc != '0');
   puts("tchau");

}


int compara(info *a, info *b)
{ if (a->chave > b->chave)
     return MAIOR;
  else
     if (a->chave < b->chave)
             return MENOR;
     else
        return IGUAL;
}
