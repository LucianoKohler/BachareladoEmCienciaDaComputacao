# Exceções e Interrupções
São eventos não programados que podem alterar o fluxo do código
(Tudo falando no MIPS)

* Exceção - Interna, normalmente relacionado a bugs (1/0, overflow, ...)
* Interrupções - Externas, quando o usuário insere algo no teclado

* No MIPS:
  * Entrada e saída = interrupção
  * O resto = exceção (syscall, overflow, ins. inválida)

### O que fazer na exceção?
* Salva o endereço da instrução da exceção no registrador EPC (Exception PC)
* Salvar os códigos de erro no refistrador **cause** (1 para overflow, 2 para ins. inválida, ...)
* Pula para o código do sistema operacional (inicia-se em 0x80000180)
* O sistema então decide se continua o programa ou para, salvando o EPC para debug se ele parar