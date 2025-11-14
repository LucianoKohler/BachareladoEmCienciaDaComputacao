# Hazards
Hazards são problemas que provém do uso de *pipelining*, existem três tipos principais de hazards:

- Hazard Estrutural
- Hazard de Dados
- Hazard de Controle

# Hazard Estrutural
*Quando um componente é utilizado por mais de uma intrução para tarefas diferentes*

**Problema**: Sobrecarga do componente, falhando em executar a instrução

**Solução**: Duplicar componentes que são utilizados mais de uma vez na execução da instrução

# Hazard de Dados
*Quando um valor ainda não foi atualizado, mas é necessário para a próxima instrução*

Exemplo:

```s
add $s0, $t0, $t1
sub $s1, $s0, $t2
```

**Problema**: Sub precisa do valor dentro de $s0, mas o pipelining não garante que a segunda instrução terá os valores de $s0 já atualizados pelo add

**Solução**: Ligar a ALU que faz a operação anterior na ALU que fará a nova operação, assim, não precisando acessar o banco de registradores à procura do valor ainda não salvo (retirar o valor direto da fonte), esta técnica se chama **forwarding**.

*Nota: Para o `lw`, é necessário um forwarding diferente.

Algumas sequências de instruções precisam, mesmo com o forwarding, dar um *stall* no código para atrasar uma instrução e executá-la sem perigo de hazards, por conta disso, os compiladores mais sofisticados tentam fazer de tudo para evitar esses stalls (como por exemplo, inverter a ordem de algumas instruções que não mudem a lógica do código)

`lw com add/sub` sempre precisa de stall!!

# Hazard de Controle
*Quando, após um branch, o código não sabe qual instrução ele deve ler à seguir para manter o pipeline regular.*

Exemplo:
```asm
0x40 beq $1, $3, 7
0x44 and $12, $2, $5
0x48 or $13, $6, $2
0x4C add $14, $2, $2
...
0x60 lw $4, 50($7)
```

**Problema:** O processador não sabe se carrega as instruções dentro do endereço de memória **0x44** ou **0x60**

**Solução:** Prevemos (na sorte) qual caminho tomar, e se o caminho está correto, prosseguimos, mas senão, voltamos atrás, perdendo tempo no processo

Esta solução é funcional, porém muito lenta e afetaria demais o pipeline, então não a adotamos...

---

**Nova Solução:** Fazer os branches com antecedência em uma unidade separada para receber o resultado mais rapidamente e conseguir decidir qual caminho tomar

**NOVO PROBLEMA:** Essa solução gera mais hazards de dados, pois a comparação da branch (Realizada no ID) pode vir do EX, MEM ou WB, então teria de haver outra unidade de forwarding ainda mais complicada para o branch.

Esquecemos essa solução por conta da grande complexidade que o processador terá de tomar...

---

**NOVA SOLUÇÃO**: Previsão dinâmica de caminhos, onde à partir de uma tabela que salva todas as comparações já feitas anteriormente, adotamos o caminho **mais provável** de ser seguido.

Esta solução é boa pois a grande maioria das comparações seguem padrões no processamento, então, esta será a solução adotada: Uma versão muito mais eficaz da primeira solução vista aqui.

---