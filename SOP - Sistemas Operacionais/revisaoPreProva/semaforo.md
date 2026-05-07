Note que na prática, up e down não existem, é usado o semáforo POSIX do Linux daí

```cpp
const int mx = 10;

semaphore mutex = 1; // PARA EXCLUSÃO MÚTUA (em 1 pra não dar deadlock)
semaphore freeSlots = mx; // PARA NÃO ADICIONAR NA FILA CHEIA
semaphore numItems = 0; // PARA NÃO CONSUMIR DA FILA VAZIA

void produtor(void) {
    int item;
    while (1) {
        item = produz_item();

        down(&freeSlots);
        down(&mutex);
        insere_buf(item);
        up(&mutex);
        up(&numItems);
    }
}

void consumidor(void) {
    int item;
    while (1) {

        down(&numItems);
        down(&mutex);
        item = retira_buf();
        up(&mutex);
        up(&freeSlots);

        consome_item(item);
    }
}
```
