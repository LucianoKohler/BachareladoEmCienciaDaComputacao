**02/06; 17:30** *(1h)* - Luciano e Luiza se reuniram para decidir a stack técnica do projeto: C++ com OpenGL (GLFW + GLAD + GLM), já que era o que estava sendo visto em aula. Foram cogitadas algumas ideias de jogo, mas nenhuma foi decidida ainda;

**02/06; 20:00** - Ideia geral do jogo foi gerada por ambos os integrantes, a ideia original foi um jogo no estilo "flappy bird", mas para variar a jogabilidade, ele seria jogado na vertical e seus controles seriam diferentes. Ao final, foi decidido que o jogo seria sobre um pinguim deslizando em cima de um iceberg com obstáculos para se esquivar;

**03/06; 10:00** *(1h)*- Primeiros diagramas e features do jogo foram desenhados, o jogo teria estalactites de gelo gigantes bloqueando a passagem e buracos para derrubar o pinguim;

**03/06; 20:15** *(1h10)* - Luciano configurou o ambiente de desenvolvimento (instalação do GLFW, GLAD e GLM, além do comando de compilação com g++). Bug: erro de linkagem "undefined reference" nas funções do GLAD, resolvido colocando o glad.c na mesma pasta do projeto e compilando ele junto no comando;

**09/06; 19:15** *(2h30)* - Luiza criou o primeiro protótipo: uma janela GLFW vazia, apenas validando se a janela abria e fechava corretamente com ESC e escreveu a primeira versão do vertex e fragment shader para desenhar quads texturizados;

**11/06; 20:00** *(1h)* - Luciano prototipou uma mecânica de "quadrado se movendo para os lados" só para testar o input do teclado.

**14/06; 14:30** *(1h20)* - Luciano integrou a biblioteca stb_image.h e escreveu a primeira versão (bem simples) de uma função de carregamento de textura, sem tratamento de erro, o programa crashava se o arquivo não existisse;

**18/06; 20:45** *(1h)* - Luiza refatorou as chamadas de desenho soltas em uma função única, `drawSprite`, para parar de duplicar código de bind de textura, matriz de modelo e projeção;

**21/06; 13:30** *(1h30)* - Iniciado desenvolvimento do projeto, Luciano criou variáveis globais para o jogador e aspectos do jogo, como o tamanho da tela, a pontuação do jogo e a velocidade dos inimigo. Criou, também, os objetos responsáveis para serem os obstáculos do jogo (buracos e estalactites);

**22/06; 14:15** *(2h)* - Luciano criou a função `loadTexture`, que carrega todas as texturas do jogo previamente e envia erros caso alguma textura não carregue, as texturas iniciaram como apenas quadrados simples;

**28/06; 08:44** - Os membros da equipe mudara a ideia dos obstáculos, agora, existem 3 obstáculos diferentes:
- **Buracos**: Ideia mantida igual à original
- **Morsas**: Andam horizontalmente pela tela
- **Penguins vermelhos**: Mostram um aviso na tela e caem logo em seguida como um míssil 

**28/06; 11:10** *(3h)* - Luiza implementou aparição de inimigos em um intervalo de tempo que decrementa dependendo do quão longe o jogador foi. Os inimigos aparecem em raridades diferentes (buracos são mais comuns);

**30/06; 18:20** *(2h)* - Estalactites foram removidas do código e Luciano implementou o obstáculo "morsa", com movimento horizontal e rebote automático ao encostar nas bordas da tela. Bug: a morsa "grudava" na borda por um frame antes de inverter a direção, corrigido travando a posição em "0" ou "SCREEN_WIDTH - width" no exato momento da colisão;

**01/07; 20:10** *(1h05)* - Luiza implementou o terceiro obstáculo, o "pinguim vermelho": fica parado com um aviso piscando na tela por um tempo (`warningTimer`) antes de cair feito um míssil na velocidade triplicada;

**01/07; 21:30** *(30min)* - Luiza escreveu a função `checkCollision` definitiva do jogo (colisão AABB entre dois retângulos), usada pra detectar colisão do jogador com qualquer obstáculo.

**01/07; 20:30** *(3h30)* - Luciano criou os sprites: pinguim jogador (azul), pinguim inimigo (vermelho), morsa, buraco e aviso de queda;

**02/07; 17:45** *(40min)* - Luciano implementou a pontuação incremental (`score` aumenta com o tempo de jogo) e a lógica de game over ao colidir com qualquer obstáculo;

**02/07; 19:00** *(50min)* - Luiza criou a primeira versão da tela de game over e a função de reinício pela tecla R, restaurando as variáveis do jogo ao estado inicial;

**02/07; 21:15** *(25min)* - Bug encontrado por Luciano: o reinício não limpava o vetor de obstáculos, então "obstáculos fantasmas" da partida anterior continuavam na tela depois do reset. Corrigido chamando `obstacles.clear()` dentro do reinício;

**03/07; 18:00** *(1h20)* - Luiza criou a estrutura `Fish` e o sistema de spawn de peixes, com 4 raridades (comum, incomum, raro e lendário), cada uma dando uma pontuação diferente ao ser coletada;

**03/07; 19:20** *(1h)* - Luciano criou os sprites dos quatro peixes de cada raridade e do fundo;

**03/07; 20:30** *(1h)* - Luciano fez uma spritesheet de numeros e implementou a função `drawScore`, que desenha a pontuação dígito por dígito usando ela;

**03/07; 22:00** *(30min)* - Luciano percebeu que o buraco (WATER_HOLE) estava sendo desenhado no mesmo laço que a morsa e o pinguim vermelho, então em certos momentos ele aparecia sobrepondo os outros obstáculos, o que ficava visualmente errado (o buraco deveria estar "no chão", atrás de tudo). Corrigido separando o desenho em camadas: o buraco passou a ser desenhado sozinho logo depois do fundo (Camada 2), enquanto morsa e pinguim vermelho ficaram numa camada desenhada depois (Camada 4), garantindo que o buraco sempre fique por baixo dos outros objetos.

**04/07; 19:45** *(50min)* - Luiza implementou o scroll infinito do fundo (`backgroundScroll`), sincronizando a velocidade do deslocamento visual com a velocidade atual dos obstáculos;

**04/07; 21:00** *(30min)* - Luiza corrigiu um bug visual em que o fundo "pulava" perceptivelmente ao reiniciar o ciclo do scroll (quando `backgroundScroll` chegava a 1.0), resolvido subtraindo 1.0 do valor em vez de resetar para 0;
 
**05/07; 10:00** *(35min)* - Luciano adicionou o efeito de piscar no sprite de aviso antes da queda do pinguim vermelho, usando `fmod` sobre o `warningTimer` para alternar a visibilidade do sprite;

**05/07; 11:00** *(1h15)* - Luiza implementou a tela de game over completa: sprite de "Game Over" com efeito pulsante (usando seno do tempo), label de "Pontuação" e o aviso de "pressione R para reiniciar";

**05/07; 13:30** *(1h)* - Sessão de testes gerais feita por Luciano e Luiza juntos: ajuste de dificuldade, definindo a velocidade inicial dos obstáculos e um intervalo mínimo de spawn de 0.6s (para o jogo não ficar impossível rápido demais);

**05/07; 15:00** *(20min)* - Luiza ajustou a margem de colisão da função `checkCollision` de 0 para 10 pixels, evitando mortes "injustas" causadas pela área transparente das bordas dos sprites;

**05/07; 16:00** *(45min)* - Luciano revisou o código-fonte, organizou comentários e removeu trechos de teste antes da entrega final do projeto.