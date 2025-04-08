# 1. Responda:
## A. Você conhece as diretivas de compilação? Descreva algumas: 

Diretivas de compilação em C são palavras-chave especiais que, quando colocadas em um código a ser compilado, o compilador primeiramente irá passar pelo código para ver se há alguma diretiva e, se houver, ele modificará o código compilado final dependendo de cada diretiva, removendo ou adicionando partes do código que sejam relevantes e indicadas pelas diretivas. Vejamos o que cada um faz:

- ```#include```: Inclui um bloco de texto indicado após o include no código principal, similar ao chamar de uma função

- ```#define```: Define uma constante para ser usada no código

- ```#undef```: Remove a definição da constante em específico

- ```#ifdef```: Executa uma parte do código se uma constante específica estiver definida

- ```#ifndef```: Executa uma parte do código se uma constante específica NÃO estiver definida

- ```#if```: Executa uma parte do código se alguma condição for verdadeira

- ```#else```: Executa uma parte do código se o #if acima dela for falso

- ```#elif```: Mesma coisa que o "else if"

- ```#endif```: Termina o if, já que não usamos chaves para determinar o início e o fim do #if

## B. Descreva os comandos memcpy e typedef:
- ```memcpy```: No C, principalmente envolvendo Structs e Strings, quando queremos copiar um dado de uma variável para outra, não conseguimos na maioria das vezes, fazer isso somente com o comando "=", logo, o memcpy serve como uma função "=" mais elaborada para copiar um valor de uma variável para outra.

- ```typedef```: typedef ajuda a simplificar o código por renomear nomes de tipos de dados para outros tipos, um exemplo pode explicar bem o assunto:

```c
// Declaração comum de variável
unsigned long long int i = 0;

// Declaração com a mesma variável, porém renomeando seu tipo com o typedef
typedef unsigned long long ull;
ull i = 0;
```

Assim, todas as vezes que quisermos criar uma variável de tipo grande, podemos só renomear esse tipo por meio do ```typedef```.

## C. Leia o código à seguir, e preencha a tabela com os valores de cada variável:

```c
main(void) {
  int *p=NULL, **pp=NULL, x = 321, y=101;
  p = &x;
  pp=&p;
  *p= -3;
  y=**pp;
}
```

| Campo  |    Conteúdo   |
| ------ | ------------- |
|   p    | endereço de X |
|   x    |       -3      |
|   y    |       -3      |
|   pp   | endereço de P |
|*pp == p|      true     |
|*p == x |      true     |

## D. Leia o código e responda:

```c
struct teste2 { 
  int inteiro;
  float real;
  char nome[30];
  struct teste2 *self;
};

struct teste2 x = {115, 2.5, "Smith", NULL},*p=NULL;
p = &x;
p->self = &x;
```

---
### I. Qual é o valor final de p?
- Como visto abaixo da declaração, p tem como valor, o endereço de X
---
### II. Qual é o valor final de p->self?
- Assim como em I, é o endereço de memória de X 

## E. Veja o código e preencha a tabela:

```c
struct teste {
  int inteiro;
  float real;
  char nome[30];
  char rua[30];
  int *apont;
};

main(void)
{
  struct teste *p=NULL, x = {321, 2.39, "Silva", "Timbo", NULL};
  int y = 101;
  p = &x;
  p->apont = &y;
}
```

|  Campo   |   Conteúdo    |
| -------- | ------------- |
|    p     | endereço de X |
|p->inteiro|     321       |
| p->real  |     2.39      |
| p->nome  |   "Silva"     |
| p->rua   |   "Timbo"     |
| p->apont | endereço de Y |
| y        |     101       |

# 2. Leia os blocos de código descritos abaixo e, à partir da struct teste, encontre os erros de cada um:

```c
// Struct definida
typedef struct teste{ int inteiro;
float real;
 char nome[30];
 } informacao;
 ```

---

```c
// A
main(void) {
  informacao *p, x = {321, 2.39, "Silva"};
  if (p)
  printf("valores da struct X: %i, %f, %s", p->inteiro, p->real, p->nome);
  else
  printf("o ponteiro está anulado");
} 
```
R: Na linha de declaração, estamos declarando as variáveis *p e x, porém estamos atribuindo valores apenas para x, enquanto *p é declarado vazio, e provavelmente com lixo da memória, então o que pode estar dentro de p pode ser imprevisível.

---

```c
// B
main(void) { 
  informacao *p, x = {321, 2.39, "Silva"};
  p= (struct teste *) malloc(sizeof(struct teste));
  printf("Campos da variável x: %i, %f, %s", p->inteiro, p->real, p->nome);
}
```
R: Agora, a variável p é moldada para que caiba uma struct do tamanho certo, porém novamente, ela não recebe os valores de X, logo, ela pode conter lixo da memória.

---

```c
 // C
 main(void) { 
  informacao *p, x = {321, 2.39, "Silva"};
  p = x;
  printf("Campos da variável x: %i, %f, %s", p->inteiro, p->real, p->nome);
}
```
R: Na linha "p = x", por p ser um ponteiro, ele deve receber um endereço de memória para apontar, logo o certo deveria ser "p = &x".

---

```c
// D
main(void) { 
  informacao *p, x = {321, 2.39, "Silva"};
  p = &x;
  printf("Campos da variável x: %i, %f, %s", p.inteiro, p.real, p.nome);
}
```
R: Agora, o erro está no acesso das informações de p, por p ser um ponteiro, devemos utilizar a seta ( -> ) para acessar as informações dentro da variável apontada por p, se ele fosse a struct em si, aí o certo seria utilizar do ponto ( . )
- Lembrando que a->x = (*a).x

---

```c
// E
main(void) { 
  informacao *p, x = {321, 2.39, "Silva"};
  p= (struct teste *) malloc(sizeof(struct teste));
  p=&x;
  printf("Campos da variável x: %i, %f, %s", p->inteiro, p->real, p->nome);
}
```
R: Tudo certo neste exemplo!

---

