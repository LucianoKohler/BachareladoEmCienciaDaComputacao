/*
Modifique o programa simples.c para que cada thread retorne o quadrado do numero 
recebido como parametro. O programa principal deve imprimir a soma dos valores de 
retorno de todas as threads.
*/

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS 2

// Para passar multiplos parâmetros, é necessário criar uma struct para tal
struct args
{
    long tid;
    long num;
};

void *quadrado(void *args)
{
    struct args *arguments = (struct args *)args; // void* -> struct
    long tid = arguments->tid;
    long num = arguments->num;

    printf("Thread %ld diz: Quadrado de %ld = %ld\n", tid, num, num * num);
    long *res = malloc(sizeof(long));
    *res = num * num;
    free(arguments);
    pthread_exit((void*) res);
}

int main(int argc, char *argv[])
{
    if (argc < 2)
    { // Se não há argumento
        printf("E o n, querido?\n");
        exit(0);
    }
    long n = atoi(argv[1]);
    int rc;                         // Return code (0 = ok, o resto é erro)
    long t;                         // Thread (i no nosso caso)
    pthread_t threads[NUM_THREADS]; // Local de salvamento das threads

    for (t = 0; t < NUM_THREADS; t++)
    {
        printf("main: criando thread %ld\n", t);

        struct args *a = malloc(sizeof(struct args));
        a->tid = t;
        a->num = n;

            rc = pthread_create(&threads[t], NULL, quadrado, (void *)a);
        if (rc)
        {
            printf("ERRO - rc=%d\n", rc);
            exit(-1);
        }
    }

    long soma = 0;
    for(int t = 0; t < NUM_THREADS; t++){
        void*res;
        pthread_join(threads[t], &res);

        long num = *((long *) res);
        soma += num;
    }

    printf("Resultado: %ld\n", soma);

    /* Ultima coisa que main() deve fazer */
    pthread_exit(NULL); // Assassinato em série
}
