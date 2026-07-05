# Change log

O desenvolvimento do trabalho, ao invés de seguir uma linha contínua de desenvolvimento, teve um desenvolvimento bem "florido", pois ideias novas para incrementar o jogo foram surgindo durante o desenvolvimento, então a timeline do desenvolvimento do projeto será separada por funcionalidades, contendo, claro, as funções relacionadas à tais features.

**28/06; 14:00** - Ideia geral do jogo foi gerada por ambos os integrantes, a ideia original foi um jogo no estilo "flappy bird", mas para variar a jogabilidade, ele seria jogado na vertical e seus controles seriam diferentes. Ao final, foi decidido que o jogo seria sobre um pinguim deslizando em cima de um iceberg com obstáculos para se esquivar;

**28/06; 14:30** - Primeiros diagramas e features do jogo foram desenhados, o jogo teria estalactites de gelo gigantes bloqueando a passagem e buracos no iceberg para derrubar o pinguim; 

**29/06; 13:30** - Iniciado desenvolvimento do projeto, Luciano criou variáveis globais para o jogador e aspectos do jogo, como o tamanho da tela, a pontuação do jogo e a velocidade dos inimigos

**29/06; 13:45** - Luciano criou os objetos responsáveis para serem os obstáculos do jogo (buracos e estalactites)

**29/06; 14:15** - Luiza criou a estrutura de "Vertexes" para tratar corretamente das texturas do jogo.

**29/06; 14:32** - Luciano criou a função "loadTexture", que carrega todas as texturas do jogo previamente e envia erros caso alguma textura não carregue, as texturas iniciaram como apenas quadrados simples

**30/06; 08:44** - Os membros da equipe mudara a ideia dos obstáculos, agora, existem 3 obstáculos diferentes:
- **Buracos**: Ideia mantida igual à original
- **Morsas**: Andam horizontalmente pela tela
- **Penguins vermelhos**: Mostram um aviso na tela e caem logo em seguida como um míssil 

**30/06; 08:50** - Luiza implementou aparição de inimigos em um intervalo de tempo que decrementa dependendo do quão longe o jogador foi. Os inimigos aparecem em raridades diferentes (buracos são mais comuns)

**01/07** - 
02/07 - 

- Inimigos
    - Ideia original (Espinhos e poças)
    - Ideia nova (3 obstáculos)

- Pontuação e gameover

- Peixes
    - Raridades

- Sprites
    - Pinguim
    - Inimigos
    - Peixe
    - Fundo