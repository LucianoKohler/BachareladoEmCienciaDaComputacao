# Hazards
Hazards são problemas que provém do uso de *pipelining*, existem três tipos principais de hazards:

- Hazard Estrutural
- Hazard de Dados
- Hazard de Controle

## Hazard Estrutural
*Quando um componente é utilizado por mais de uma intrução para tarefas diferentes*

**Problema**: Sobrecarga do componente, falhando em executar a instrução

**Solução**: Duplicar componentes que são utilizados mais de uma vez na execução da instrução

## Hazard de Dados
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