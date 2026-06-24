# Introdução
Um código passa por várias fases e analisadores antes de se tornar um executável:
* ***Pré-processador***: Em `C`, modifica o código preliminarmente (quase tudo envolvendo diretivas que inicial com `#` como `include` e `ifdef`) (retorna **arquivo.i**)
* ***Compilador***: Analisa o código à procura de erros e avisos e retorna um código em linguagem de máquina caso não encontre problemas (retorna **arquivo.as**)
* ***Montador***: Transforma o código em linguagem de máquina direta (0 e 1) chamada de objeto (retorna **arquivo.o**)
* ***Editor de Ligação***: Junta outros arquivos relevantes para o código e cola o código deles no .o (retorna **arquivo.out ou arquivo.exe**)

# Compilador
Olhando somente para o compilador, também temos várias fases de execução:
- ***Analisador Léxico***: Lê o código caractere por caractere e agrupa palavras ou símbolos nos chamados **tokens**, palavras reconhecíveis pelo resto dos analisadores, se você usar um caractere inválido ou palavra não reconhecida pela linguagem, o léxico barra aí
- ***Analisador Sintático***: Vê se os tokens estão inseridos na ordem correta, exemplos de erro sintático são:
    - `if(a > b) {`: Não fechou as chaves
    - `s string = "roberto"`: Ordem da associação não faz sentido 
- ***Analisador Semântico***: Vê se o código faz sentido lógico, encontrando erros de tipo e similares, além de checar se existem variáveis não declaradas ou redeclaradas e etc...
    - `int num = "ronaldo"`: Erro de tipo
    - `void func(bool a){ return a; }`: Retorno errado

- Após isso existem outras fases de otimização porém essas são complexas e não serão vistas.

# Gramáticas
- ***Irrestritas***: É da forma `A -> B`, onde  a única restrição é que `A` precisa de um não-terminal 
- ***Sensíveis ao Contexto***: É da forma `xAy -> xBy`, onde o tamanho de `A` deve ser menor ou igual à `B`, é sensível ao contexto pois além da variável, a linguagem também analisa os seus vizinhos, por fim, a linguagem não tem como encolher (*Mas ainda pode incluir a palavra vazia como palavra válida!*)
- ***Livres de Contexto***: São da forma `A -> x`, sendo x qualquer combinação de variáveis e terminais, é muito usada nos analisadores sintáticos. Pois é fácil representar expressões com ela:
    - `Ex: if -> "if" "(" expr ")" bloco`
- ***Regulares***: Estão sempre nas formas 
    - `A -> bC`
    - `A -> Cb` 
    - `A -> b` 
        - sendo `C` uma variável e `b` um terminal
    - São muito usadas em expressões regulares e analisadores léxicos.

# Analisadores

Existem diversas estratégias diferentes que um parser pode aplicar para detectar a validade sintática de um programa, a maioria delas é classificada em:

- **Top-Down**, onde o analisador começa com o token mais alto da árvore e tenta **prever** o caminho que o código tomou para validá-lo

- **Bottom-Up**, onde o analisador se inicia nas folhas do programa e tenta aplicar estratégias de consumir `n` tokens e **reduzí-los** numa expressão maior

Alguns deles são:

## LL(1):
---  
- **1° L**: Left-to-right (leitura do código analisado é feita da esquerda pra direita)
- **2°L**: Leftmost-derivation (no caso de existirem variações, é sempre escolhida a variável mais à esquerda para derivar)
- **(1)**: Lookahead (Só olha 1 token à frente para tomar a decisão)

A linguagem é **muito** simples e eficiente, porém ela **não** pode ler por exemplo uma linguagem com essas regras:

- **1° Ambiguidade**
    - `comando -> if (expr) then bloco`
    - `comando -> if (expr) then bloco else bloco`
        - Isso acontece pois o lookahead é sempre feito somente no primeiro token, e como ambos os comandos têm os mesmos priemiros tokens (`if e (expr)`), a linguagem não saberia qual aplicar

- **2° Recursão à Esquerda**
    - `expr -> expr + numero | numero`
        - Ao derivar `expr`, o analisador irá infinitamente tentar derivar `expr`, entrando em loop. Para isso, é necessário eliminar a recursão à esquerda, que normalmente é feito assim:
        - `expr -> numero expr'`
        - `expr' -> + numero expr' | e`

### Tabela Sintática

Para que a gramática consiga gerar a cadeia correta de caracteres, é utilizado uma **tabela sintática** que indica qual caminho a variável **X** deve tomar para chegar no caractere **y**

Para isso, são usadas duas funções para auxiliar na tomada de decisão

- ***FIRST/PRIMEIROS(V)***: A lista de terminais que podem aparecer como primeiro caractere da saída da derivação `V`
    - PRIMEIROS(A) se `A -> BC` = `PRIMEIROS(B)`
    - Se `B` puder virar `e`, `PRIMEIROS(A)` também engloba `PRIMEIROS(C)`

- ***FOLLOW/SEGUINTES(V)***: A lista de terminais que podem surgir diretamente ao lado da variável `V`
    - Se eu tenho `A -> a B C b`: `SEGUINTES(B)` = `PRIMEIROS(C)`
    - Se eu tenho `A -> B`: `SEGUINTES(B)` = `SEGUINTES(A)`
    - Se eu tenho `A -> B C` e `C` pode virar `e`, `SEGUINTES(B)` recebe também `SEGUINTES(A)`

***EXEMPLO***

```
E  -> TE'

E' -> + TE'
    | e

T  -> FT'
T' -> * FT'
    | e

F  -> c
    | (E)
 ```

 ***PRIMEIROS***
- **Primeiros(E )** = `{ c, ( }`
- **Primeiros(E')** = `{ + }`
- **Primeiros(T )** = `{ c, ( }`
- **Primeiros(T')** = `{ * }`
- **Primeiros(F )** = `{ c, ( }`

***SEGUINTES***
- **Seguintes(E )** = `{ $, ) }`
- **Seguintes(E')** = `{ $, ) }`
- **Seguintes(T )** = `{ +, $, ) }`
- **Seguintes(T')** = `{ +, $, ) }` 
- **Seguintes(F )** = `{ *, +, $, ) }`

 ***TABELA***

 Estratégia: 
 - PEGA UMA VARIÁVEL E ANALISA AS TRANSFORMAÇÕES:
    - Para cada transformação (Ex. `A -> BC`) da tabela, olhar o PRIMEIROS(`B`) e colocar essa transformação em todos os terminais que estão presentes nele.
        - Se `B` puder sumir, olhe pro PRIMEIROS(`C`), e assim por diante...

    - Se temos `A -> e`, olhe para os SEGUINTES(`B`) e coloca palavra vazia (representada por `e`) nas transformações de A que estão no seguintes.

 | var |  c  |  +  |  *  |  (  |  )  |  #  | 
 |-----|-----|-----|-----|-----|-----|-----|
 |  E  | TE' |  -  |  -  | TE' |  -  |  -  | 
 | E'  |  -  | +TE'|  -  |  -  |  e  |  e  | 
 |  T  | FT' |  -  |  -  | FT' |  -  |  -  | 
 | T'  |  -  |  e  | *TE'|  -  |  e  |  e  | 
 |  F  |  c  |  -  |  -  | (E) |  -  |  -  | 

***Dica***: Calcule apenas os SEGUINTES() das variáveis que têm transformações no formato `A -> e`

## SLR(1)

Usa a estratégia Bottom-Up para, à partir de uma entrada, derivar sua gramática até chegar na gramática desejada.

É a mais simples das três gramáticas `LR`, decide se vai empilhar ou reduzir por meio do `SEGUINTES(A)`

É bem leve porém não tem muito contexto, logo ela pode considerar `SEGUINTES(A)` em um contexto errado, onde terminais ao redor da derivação podem influenciar o resultado.

## LR(1)

Carrega na sua tabela sintática somente  as derivações válidas para ele e só reduz caso a derivação dê match exato com o lookahead da gramática

Porém é muito mais custoso, como os estados precisam duplicar regras para acomodar todas as possibilidades de derivações, o número de estados cresce exponencialmente

## LALR(1)

LR porém otimizado ao selecionar estados com as mesmas regras para fundi-los, assim consumindo menos espaço, porém podendo sofrer com problemas parecidos do SLR, onde o contexto perdido pode ocasionar em problemas de derivação

# Definição Dirigida pela Sintaxe

Onde os tokens da gramática possuem valores e regras semânticas, assim não é necessário redeclarar tipos ou tornar a gramática muito complexa

Atributos podem vir de duas formas:
- **Atributos Sintetizados**: Vêm dos filhos da computação deles (Ex. `a = b+c` -> `a = tipo(b ou c)`)
- **Atributos Herdados**: Vêm dos irmãos ou pais do token (Ex. `int a` -> `a tem valor int`)

# Código de 3 Endereços

Intermediário entre a linguagem e o Assembly, que não sabe fazer algo do tipo

`a = b*c+d`

então o código de 3 endereços adiciona operações intermediárias para que todas as operações estejam no formato `X = Y op Z`:

- `t1 = b*c`
- `t2 = t1+d`
- `a = t2`

Esse código normalmente só tem instruções do tipo

- `x = y op z`
- `x = op z`
- `x = y`
- `goto L`
- `if relop x goto L`
- `...`