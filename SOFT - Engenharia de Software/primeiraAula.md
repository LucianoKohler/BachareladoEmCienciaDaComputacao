# Engenharia de Software
Área da Computação destinada a *investigar os desafios* e propor *soluções* que permitam desenvolver sistemas de software. Principalmente aqueles mais complexos e de maior tamanho — de forma *produtiva* e com *qualidade*. 
- Surgiu da dificuldade de gerir o desenvolvimento de **grandes projetos**

## Tópicos que ela abrange
### 1. Engenharia de Requisitos
- Gerar **requisitos** que indicam, definem e documentam o que o software faz.
    - Tem requisitos **Funcionais** (o que o sistema faz) e **Não-funcionais** (como o sistema faz)

### 2. Projeto de Software
- Definir o "esqueleto" do código
```java
class ContaBancaria {
private Cliente cliente;
private double saldo;
public double getSaldo() { ... }
public String getNomeCliente() { ... }
public String getExtrato (Date inicio) { ... }
// ...
```

### 3. Construção de Software
- É a mão na massa.

### 4. Testes de Software
- Executados para validar requisitos no software, podem ser manuais ou automatizados
- **Verificação**: O software tá de acordo com os requisitos?
- **Validação**:  O software é o que o cliente quer?

### 5. Manutenção de Software
- Vários tipos:
    - Corretiva
        - A mais padrão, o bugfix
    - preventiva
        - Corrigir um bug antes que ele aconteça
    - Adaptativa
        - Ex: Nova lei exige mudança no software (fator externo)
    - Refactoring
        - SÓ melhora a manutenibilidade, renomear funções, variáveis, etc...
    - Evolutiva
        - Evolui sistemas legados, cara, importante

### 6. Gerência de Configuração
- Cuida da forma de gerir o código (ex. Git), servindo de fonte da verdade.

### 7. Gerência de Projetos
- Negociação de projetos, RH, riscos, etc... (burocracia fora do código)

### 8. Processos de Software
- Define como o software será criado (métodos ágeis, cascata, etc...)

### 9. Modelos de Software
- Modelar o software fora do código para o dev se encontrar
    - Mockups, designs, UMLs, ...

### 10.Qualidade de Software
- Uso de métricas para segurar a qualidade do software, revisão de código muito comum

### 11.Prática Profissional
- Ética, cursos, currículo, etc...

### 12.Aspectos Econômicos
- Gestão monetária do software (vai valer a pena? Etc...)

## Tipos de Software
- **C**: Casual
    - Projetinhos "tanto faz"
- **B**: Bussiness
    - Focados para uma empresa ou similar, muito beneficiado pela **SOFT**
- **A**: Acute
    - NÃO PODE FALHAR, envolve custo humano ou monetário enorme

## SaaS
Software as a Service, em resumo, sites. (Softwares que você não baixa, mas sim acessa pela Internet), acham rendimento pelo uso de **assinaturas** e o modelo **freemium**

Normalmente se origina da dor/problema do dev/de alguém que o dev conhece

O software alcança seu público por dois "canais":
- Outbound: **você** procura os clientes
    - Anúncios, vendedores
- Inbound: **Os clientes** procuram você
    - Blog, Youtube, ... O conteúdo tá lá tlgd

### Métricas de um Saas
- **CAC** - Custo de aquisição de cliente
    - Compara o custo de propagandas com a quantidade de clientes novos no mês
- **MRR** - Monthly Recurring Revenue
    - Lucro recebido pelas assinaturas num mês
- **Churn Rate** - Taxa de cancelamento
    - Clientes que cancelaram / clientes no início do mês
- **LTV** - Customer Lifetime Value
    - MRR médio / churn rate
    - **Se LTV > CAC, ele é viável monetáriamente, o ideal é que LTV / CAC > 3**
- **NPS** - Net Promoter Score
    - Nota em uma escala de 0 a 10 
    - = (% de 9 e 10) - (% de 0 a 6)
    - Acima de 50 é bom, até big tech apanha pra manter acima de 60

---

*"Em um sistema gratuito, você não é um usuário, mas sim um produto"*
- Grande volume de usuários
- Coleta de dados de navegação
- Anúncios personalizados

## Open Source
-  Rende por meio de 
    - **doações** (Mozilla, Linux)
    - **prestação de serviços** (o software é de graça, mas o treinamento não)

## Métricas de  Monetização
- Custo de desenvolvimento
- Preço da concorrência
- Capacidade de pagamento do cliente
- **Valor** do sistema
    - **Preço** é o dinheiro
    - **Valor** é a utilidade do software