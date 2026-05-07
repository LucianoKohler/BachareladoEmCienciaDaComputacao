#include <bits/stdc++.h>
#include <pthread.h>
#include <semaphore.h>  
#define down(x) sem_wait(x)
#define up(x)   sem_post(x)

sem_t semA, semB, semC;

void* A(void* arg)
{
    printf("deu o A\n");
    while (1)
    {
        down(&semA); // Unlock on "K"
        printf("i");
        up(&semB); // Unlocks "t"
        printf("a");
    }
    return NULL;
}

void* B(void* arg)
{
    printf("deu o B\n");
    int i;
    while (1)
    {
        for (i = 0; i < 2; i++)
        {
            down(&semB); // Unlocks on "i"
            printf("t");
            if (i == 0){
                printf(" ");
            }
                up(&semC); // Unlocks "k"
        }
    }
    return NULL;
}

void* C(void* arg)
{
    printf("deu o C\n");
    int j;
    while (1)
    {
        for (j = 0; j < 2; j++)
        {
            printf("K");
            up(&semA); // can print "i"
            down(&semC); // Unlock on " "
        }
        printf("\n");
    }
    return NULL;
}

int main(){
    printf("alo\n");
    sem_init(&semA, 0, 0);
    sem_init(&semB, 0, 0);
    sem_init(&semC, 0, 0);

    pthread_t tA, tB, tC;
    pthread_create(&tA, NULL, A, NULL);
    pthread_create(&tB, NULL, B, NULL);
    pthread_create(&tC, NULL, C, NULL);

    pthread_join(tA, NULL);
    pthread_join(tB, NULL);
    pthread_join(tC, NULL);


    return 0;
}

// Close enough?