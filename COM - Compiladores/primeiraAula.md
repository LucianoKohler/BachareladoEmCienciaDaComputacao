# Níveis de um Compilador

## Front-end (analisa se o código está funcional)

**Analisador Léxico**
- Pega as sequências de tokens do código para ver se eles pertencem à linguagem escolhida

**Analisador Sintático**
- Assegura que os parâmetros e afins do código façam sentido no contexto que estão inseridos

**Analisador Semântico**
- Vê se a estrutura do código têm sentido e contextualização como um todo

**Gerador de código intermediário**
- Retorna uma versão bruta do código, que funciona

## Back-end (otimiza o código gerado)

**Otimizador**
- Retira operações redundantes e/ou otimizáveis

**Gerador de código**
- Gera o código final, funcional e otimizado se passado por todas as camadas do compilador