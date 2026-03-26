/*
Modifique o programa simples.c para criar, além das threads que executam PrintHello(),
outras NUM_THREADS+3 threads que imprimam o quadrado do número recebido como parâmetro.
(No total, portanto, o programa deve criar 2*NUM_THREADS+3 threads).
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

// Primeira função (só printa hello world da thread específica)
void *PrintHello(void *arg)
{
    long tid = (long)arg;
    printf("Alo da thread %ld\n", tid);
    pthread_exit(NULL);
}

// Segunda função (printa o quadrado do arg passado)
void *quadrado(void *args)
{
    struct args *arguments = (struct args *)args; // void* -> struct
    long tid = arguments->tid;
    long num = arguments->num;

    printf("Thread %ld diz: Quadrado de %ld = %ld\n", tid, num, num * num);
    free(arguments); 
    pthread_exit(NULL);
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

    // Primeiro loop: printa hello world NUM_THREADS vezes
    for (t = 0; t < NUM_THREADS; t++)
    {
        printf("main: criando thread %ld\n", t);
        rc = pthread_create(&threads[t],
                            NULL,
                            PrintHello,
                            (void *)t);

        if (rc)
        {
            printf("ERRO - rc=%d\n", rc);
            exit(-1);
        }
    }

    // Segundo loop: printa o quadrado de argv[1] NUM_THREADS+3 vezes
    for (t = t; t < 2 * NUM_THREADS + 3; t++)
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

    /* Ultima coisa que main() deve fazer */
    pthread_exit(NULL); // Assassinato em série
}
