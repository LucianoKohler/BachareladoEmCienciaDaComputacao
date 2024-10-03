Ao invés da corrente ser constante, ela será, em um circuito alternado, variada entre positiva e negativa

Corrente Alternada Senóide
Remete à sinais analógicos e irá variar igual à um gráfico ed uma função seno, contendo as mesmas variáveis que o gráfico:

## Variáveis

- Vn = Amplitude (V)
- T = Período/tempo (S)
- w = Frequência Angular (rad/s)
- F = Frequência (Hz)
- v = voltagem (V)
- ø = fase (Deslocamento para direita/esquerda no gráfico)

## Fórmulas

- Normalmente, a lei da função será **V(t) = sin(wT + ø)**
- A lei da função para corrente será **I(t) = cos(wT + ø)** 
- T = 2pi/w
- V(t) = v(t+T)
- F = 1/T
- w = 2pi/T

## Defasagem

Como sabemos, o gráfico de uma CA segue o padrão de uma senóide, mas essa senóide pode ser deslocada para esquerda ou para direita, para realizar isso, é necessário somar o nosso ø ao seno da função

- Somar = mover gráfico para a esquerda
- Subtrair = mover o gráfico para a direita

## Fasores

São um tipo de vetor que representa a amplitude da fase de uma senoide, um fasor engloba números complexos

Parte real       de 2+3j = 2
Parte imaginária de 2+3j = 3

Trocando 2 e 3 por valores genéricos, teremos um fasor **Z = x+yj**, que pode ser representado em um plano cartesiano.

- ang(θ) = e^jθ = cos(θ) + j sin(θ)
- Z = re^jθ = r(cos(θ) + j sin(θ))
- Corrente de um fasor:
  - I = jwCV
    - C = capacitância
    - V = Tensão do Fasor

com R sendo a hipotenusa do triângulo formado por Z
e θ sendo a angulação o fasor

## Propriedades de números complexos

**Sendo:**
- **Z1 = x1+jy1**
- **Z2 = x2+jy2**

Soma/Subtração = (x1±x2)+j(y1±y2)
Multiplicação = (x1x2) + jx1y2 + jx2y1 - y1y2
Divisão = ((x1+jy1)*(x2+jy2)) / (x2²+y2²)
Inversão = 1/Z1 = 1/r1 * ang(-θ1)

## Soma de fasores

**Exemplo: (40*ang(50°) + 20ang(-30°))^(1/2)**

Como devemos tratar as defasagens como vetores, não podemos apenas somá-los, mas devemos decompô-los em seno e cosseno:

(40cos(50) + j40sen(50) + 20cos(-30) + j20(sen-30))^1/2
(43,09 + j20,64)^1/2 * ang(θ)

*Sendo θ procurada pelo gráfico do fasor com X = 43,09 e Y = 20,64
