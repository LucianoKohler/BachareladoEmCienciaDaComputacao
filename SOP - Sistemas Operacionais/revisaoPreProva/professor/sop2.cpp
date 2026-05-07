/* 
Continuando o arquivo anterior, esse usa 
barriers pra aleatoriezar o output ainda mais
*/

// Rode com for i in {1..500}; do ./a.out; done | sort | uniq -c

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <assert.h>
#include <sys/types.h>
#include <pthread.h>
#include <semaphore.h>

pthread_barrier_t barr;

void rand_wait(void)
{
#define RMAX 1000000
     long r;
     while ((r = rand() % RMAX) != 171)
	  ;
}

void *S(void *argp)
{
     pthread_barrier_wait(&barr);
     printf("S");
     pthread_exit(NULL);
}

void *O(void *argp)
{
     pthread_barrier_wait(&barr);
     printf("O");
     pthread_exit(NULL);
}

void *P(void *argp)
{
     pthread_barrier_wait(&barr);
     printf("P");
     pthread_exit(NULL);
}

int main(void)
{
     pthread_t t1, t2, t3;
     int rc;
     
     rc = pthread_barrier_init(&barr, NULL, 3); // 3 threads
     assert(rc == 0);
     rc = pthread_create(&t1, NULL, S, NULL);   assert(rc == 0);
     rc = pthread_create(&t2, NULL, O, NULL);   assert(rc == 0);
     rc = pthread_create(&t3, NULL, P, NULL);   assert(rc == 0);
     rc = pthread_join(t1, NULL);   assert(rc == 0);
     rc = pthread_join(t2, NULL);   assert(rc == 0);
     rc = pthread_join(t3, NULL);   assert(rc == 0);
     putchar('\n');
     return 0;
}
