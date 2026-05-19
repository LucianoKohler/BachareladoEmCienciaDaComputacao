### Integridade Conceitual
É o que torna o projeto coerente
Ex:
- Todas as tabelas devem ter mesmo formato
- Os botões de saída devem ser os mesmo por todo app
- Usar sempre PascalCase nas variáveis
- Floats são mostrados com 2 casas após a vírgula

---

### Information Hiding
Classes e objetos devem ter atributos privados para segurança e modularização do código
- Icebergs (pouca parte pública, muita parte privada)

### Coesão
- Uma classe deve oferecer um único serviço
Ex:
- Uma classe tem o método `sinOrCos(int choice, int n)`
    - Melhor separar em `sin(int n)` e `cos(int n)`
- A classe estacionamento tem os atributos:
    - nome_gerente, fone_gerente, ... (melhor criar a classe gerente e importá-la à classe estacionamento)

### Acoplamento 
Depenência de classes
- Pode, porém é necessário cautela
    - **Acoplamento bom**: A só usa métodos de B, B é uma interface estável (Não quebra)
    - **Acoplamento ruim**: A depende de uma interface instável (que depende de outras interfaces)

# Métricas

LCOM - Lack of Cohesion Between Methods
- Para cada par de métodos na classe, checar quanta interecção de variáveis há entre eles (quanto mais intersecções, pior a classe)
- Desconsiderar gets, sets e construtores

CBO - Coupling Between Objects
- Tudo que involve acoplamento
    - A herda B
    - A retorna algo tipo B
    - A cria objeto tipo B
    - ...
- Tanto faz se é acoplamento de uma classe sua ou do próprio Java

# Técnicas

### Responsabilidade Única - melhora na COESÃO
- Um método calcula algo e imprime na tela
    - Divide em dois métodos

### Segregação de Interface - melhora na COESÃO
- Focado em interfaces, exemplo classe cliente tem método pra retornar CPF e CNPJ, o que o getCNPJ retorna pro usuário PF?
    - Usar herança e separar classes

### Inversão de Dependências - melhora no ACOPLAMENTO
- Prefira Interfaces à Classes na hora da dependência, pois interfaces funcionam para toda classe nova que herde dependências dela

### Composição > Herança - Melhora no ACOPLAMENTO

- Nem sempre só usar herança é a forma correta de gerar bom acoplamento, uma composição bem feita é preferível

### Princípio de Demeter - Melhora no OCULTAMENTO DE INFORMAÇÃO
- Cuidar com cadeias de métodos
    - `A.getB().getC().getD()`
- Somente em casos extremos arrumar esses problemas, na maioria das vezes a lógica é assim e pronto

### Princípio do Aberto/fechado - Melhora na EXTENSIBILIDADE
- Uma clase não deve ser modificada, apenas incrementada!

### Substituição de Liskov - Melhora na EXTENSIBILIDADE
- Se uma classe usa métodos de uma classe A, tanto faz se ela usa o objeto A1, A2, A3, ...