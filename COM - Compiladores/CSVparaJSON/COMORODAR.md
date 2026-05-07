O interpretador já está compilado por meio das tecnologias:
- **alex** para o analisador léxico
- **happy** para o Parser

Mas se você quiser recompilá-los, basta rodar no terminal, nessa sequêcia:
- alex Lexer.x
- happy --ghc Parser.y
    - *A flag --ghc é para caso você compile o código no ghc*

Após isso, só rodar o Parser.hs no seu compilador **e chamar a função main**, era mostrará a AST e o arquivo de saída *saida.json*