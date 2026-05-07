/*
Seguindo os arquivos anteriores, esse código
usa mutexes condicionais para printar "SOP".
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <assert.h>
#include <sys/types.h>
#include <pthread.h>
#include <semaphore.h>

int letras = 0;
pthread_cond_t condSO = PTHREAD_COND_INITIALIZER,
     condOP = PTHREAD_COND_INITIALIZER;
pthread_mutex_t mtx = PTHREAD_MUTEX_INITIALIZER;

void rand_wait(void)
{
#define RMAX 1000000
     long r;
     while ((r = rand() % RMAX) != 171)
	  ;
}

void *S(void *argp)
{
     pthread_mutex_lock(&mtx);
     printf("S");
     letras++;
     pthread_cond_signal(&condSO);
     pthread_mutex_unlock(&mtx);
     pthread_exit(NULL);
}

void *O(void *argp)
{
     pthread_mutex_lock(&mtx);
     while (letras < 1)
	  pthread_cond_wait(&condSO, &mtx);
     printf("O");
     letras++;
     pthread_cond_signal(&condOP);
     pthread_mutex_unlock(&mtx);
     pthread_exit(NULL);
}

void *P(void *argp)
{
     pthread_mutex_lock(&mtx);
     while (letras < 2)
	  pthread_cond_wait(&condOP, &mtx);
     printf("P");
     letras++;
     pthread_mutex_unlock(&mtx);
     pthread_exit(NULL);
}

int main(void)
{
     pthread_t t1, t2, t3;
     int rc;
     
     rc = pthread_create(&t1, NULL, S, NULL);   assert(rc == 0);
     rc = pthread_create(&t2, NULL, O, NULL);   assert(rc == 0);
     rc = pthread_create(&t3, NULL, P, NULL);   assert(rc == 0);
     rc = pthread_join(t1, NULL);   assert(rc == 0);
     rc = pthread_join(t2, NULL);   assert(rc == 0);
     rc = pthread_join(t3, NULL);   assert(rc == 0);
     putchar('\n');
     return 0;
}
