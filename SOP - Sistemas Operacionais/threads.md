# Ideia Geral

Um processo pode ocupar 1 ou mais threads

Cada thread tem sua própria pilha para armazenar ponteiros de execução e variáveis

Modelos sequenciais são muitas das vezes, catastróficos para rodar um código que envolve várias funcionalidades (Ex. Bloco de notas, que escuta o mouse, o teclado, e o código do app em si), para isso, o **uso de threads é essencial**

Núcleo de eficiência: tem 1 Thread
Núcleo de processamento: tem 2 Threads

# Multiprocessos

Com a ideia de que multiprocessamento é boa (e é), temos novos problemas:

- Variáveis globais podem ser modificadas concorrentemente por várias threads, *corrompendo* seu valor
     - **Solução**: Usar mecanismos de trava (semáforos, barreiras, monitores, mutex, etc...) para prevenir esse problema
- Algumas funções não são thread-safe e não foram feitas para multithreading
     - **Solução**: Trocar funções por suas *counterparts* thread-safe 
          - ex. random() -> random_r()
- O sistema precisa de muito mais pilhas para manter várias threads
     - Mudar o esquema de gestão das pilhas para comportar multithreading

# Pthreads

Uma biblioteca do C que possibilita a criação de threads concorrentes para fazer concorrência, pelos exercícios do professor, é possível provar a eficiência da biblioteca, mostrando que um mesmo código que conta de 1 a 2^31 toma, com uma thread, aproximadamente 5 segundos, mas com uma concorrência quádrupla, menos de 2 segundos.

## Criação e retorno

Uma pthread pode ser **criada** com a linha
```cpp
int pthread_create(
     pthread_t *thread, // Pointeiro para a thread (do tipo pthread_t)

     const pthread_attr_t *attr, // Configurações da pthread (deixa em NULL mesmo)
     void *(*start_routine) (void *), // Função que a pthread executará
     void *arg); // Argumento da função (só 1!!!, para mais, usar struct)
```

---

Uma pthread pode ser **encerrada** quando
- Ela retorna de uma `start_routine()` como o `pthread_create()`
- Ela chama `pthread_exit()`, podendo retornar valore como parâmetro (em void*)
- Ela é cancelada por outra thread com `pthread_cancel()`
- O processo pai é encerrado com `exit()` ou `exec()`

---

## Problemas
Em resumo, **comunicação interprocesso**

Imagine a função 

`void depositar(long *saldo, long valor) {
(*saldo) += valor;
}`

Pela definição de adição, o valor de saldo entra em um registrador intermediário antes de retornar o novo valor, por conta disso, PODE SER (ou não) que duas threads depositem concorrentemente na mesma conta, e os acessos ocorram de forma que um saldo sobreescreva o outro, e não se somem (um **erro determinístico**)

As **regiões críticas**, ou **regiões de disputa**



## Exemplos de PThreads

Para a criação, segue o código que executa a tarefa vista acima, **desenvolvido pelo grande Rafael R. Obelheiro**

```cpp

/* Ideia geral do programa:*/

/*
O código recebe um argumento: a quantidade de threads, o código 
então separa em n intervalos e chama a concorrência para contar de 1 a 2^31

Na amostra dada, feita com uma máquina com 4 núcleos, o desempenho
caiu regularmente de 1 a 4 threads, e se manteve estável após isso
*/

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <errno.h>

#define MAX_NTHR 100
#define VALOR (1L<<31)

unsigned long total = 0;

void *soma(void *arg) {
   unsigned long parcela = (unsigned long)arg;
   unsigned long total_thr = 0;
   
   for (; parcela; parcela--)
	total_thr++;
   pthread_exit((void *)total_thr);
}

int main(int argc, char *argv[])
{
     long nthr, t;
     char *ptrfim;
     pthread_t *thr;
     int rc;
     unsigned long parc, resto, total_thr;
     void *ret;

     /* o numero de threads e' passado na linha de comando */
     if (argc < 2) {
	  fprintf(stderr, "uso: %s <num-threads>", argv[0]);
	  fprintf(stderr, " (entre 1 e %u)\n", MAX_NTHR);
	  exit(1);
     }

     /* valida o numero de threads */
     errno = 0;
     nthr = strtol(argv[1], &ptrfim, 10);
     if ((errno != 0) || (ptrfim != NULL && *ptrfim != '\0') || (nthr < 1) ||
	 (nthr > MAX_NTHR)) {
	  fprintf(stderr, "erro: numero de threads invalido\n");
	  exit(1);
     }

     /* aloca os descritores de thread */
     thr = (pthread_t *)calloc(nthr, sizeof(pthread_t));
     if (thr == NULL) {
	  fprintf(stderr, "erro em calloc()\n");
	  exit(1);
     }

     /* quanto cada thread contribui para o total */
     parc = VALOR / nthr;

     /* cria as threads */
     for (t = 0; t < nthr; t++) {
	  rc = pthread_create(&thr[t], NULL, soma, (void *)parc);
	  if (rc != 0) {
	       fprintf(stderr, "erro em pthread_create()\n");
	       exit(1);
	  }
     }

     /* acrescenta a parcela do main() */
     for (resto = VALOR % nthr; resto; resto--)
	  total++;

     /* espera as threads terminarem e recupera o valor de retorno */
     for (t = 0; t < nthr; t++) {
     	  pthread_join(thr[t], &ret);
	  total_thr = (unsigned long)ret;
	  total += total_thr;
     }
     
     pthread_exit(NULL);
}
```

---

E outro que brinca com timestamps para medir tempo, novamente desenvolvido pelo professor **Rafael R. Obelheiro**

```cpp
// Código que conta quantos ímpares e pares tem num vetor (uma thread pra pares e outra pra ímpares)
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>
#include <pthread.h>

#define T_PAR 1
#define T_IMPAR 2

#define NELEM 2000000

int v[NELEM];

struct arg_st {
     int *v;
     char tipo;
};

void *conta(void *arg) {
     long i, total = 0;
     struct arg_st *argp = (struct arg_st *)arg;
     int *v = argp->v;
     char tipo = argp->tipo;
     
     for (i = 0; i < NELEM; i++) {
	  if ((tipo == T_PAR) && ((v[i] % 2) == 0))
	      total++;
	  else if ((tipo == T_IMPAR) && ((v[i] % 2) != 0))
	       total++;
     }
     pthread_exit((void *)total);
}

int main(int argc, char *argv[])
{
     int i, pares, impares, rc;
     struct timeval tv_ini, tv_fim;
     unsigned long time_diff, sec_diff, usec_diff, msec_diff;
     pthread_t tpar, timpar;
     struct arg_st arg_par, arg_impar;
     void *ret;

     srandom(time(NULL));
     for (i = 0; i < NELEM; i++) {
	  v[i] = (int)random();
/*	  vetor[i] = i*2;*/
     }
     
     /* marca o tempo de inicio */
     rc = gettimeofday(&tv_ini, NULL);
     if (rc != 0) {
	  perror("erro em gettimeofday()");
	  exit(1);
     }

     /* faz o processamento de interesse */
     arg_par.v = v;
     arg_par.tipo = T_PAR;
     rc = pthread_create(&tpar, NULL, conta, (void *)&arg_par);
     if (rc != 0) { perror("pthread_create()"); exit(1); }

     arg_impar.v = v;
     arg_impar.tipo = T_IMPAR;
     rc = pthread_create(&timpar, NULL, conta, (void *)&arg_impar);
     if (rc != 0) { perror("pthread_create()"); exit(1); }

     rc = pthread_join(tpar, &ret);
     if (rc != 0) { perror("pthread_join()"); exit(1); }
     pares = (long)ret;

     rc = pthread_join(timpar, &ret);
     if (rc != 0) { perror("pthread_join()"); exit(1); }
     impares = (long)ret;

     /* marca o tempo de final */
     rc = gettimeofday(&tv_fim, NULL);
     if (rc != 0) {
	  perror("erro em gettimeofday()");
	  exit(1);
     }
     /* calcula a diferenca entre os tempos, em usec */
     time_diff = (1000000L*tv_fim.tv_sec + tv_fim.tv_usec) - 
  	         (1000000L*tv_ini.tv_sec + tv_ini.tv_usec);
     /* converte para segundos + microsegundos (parte fracion�ria) */
     sec_diff = time_diff / 1000000L;
     usec_diff = time_diff % 1000000L;
     
     /* converte para msec */
     msec_diff = time_diff / 1000;
     
     printf("O vetor tem %d numeros pares e %d numeros impares.\n", pares,
	    impares);
/*     printf("Tempo de execucao: %lu.%06lu seg\n", sec_diff, usec_diff);*/
     printf("Tempo de execucao: %lu.%03lu mseg\n", msec_diff, usec_diff%1000);
     return 0;
}

```