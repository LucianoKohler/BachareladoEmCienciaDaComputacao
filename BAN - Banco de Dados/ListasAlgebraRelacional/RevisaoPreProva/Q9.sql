-- os funcionários que venderam o CD mais caro da loja tiveram um aumento de 10% nos seus salários
-- Esse e o próximo eu pedi pra IA, mas é possível sim alterar e inserir valores na algebra relacional por meio da "<-"
-- PORÉM NÃO NO RELAX, então fico apenas no gostinho de saber se tá certo ou não.

-- 1° Descobrir o preço do CD mais caro da loja (usando a lógica de exclusão/mínimo)
CDS_COPIA = rho C (id2, tit2, dur2, ano2, tipo2, int2, preco2) (cds)
CD_NAO_MAIS_CARO = pi cds.id (sigma preco < C.preco2 (cds x CDS_COPIA))
CD_MAIS_CARO = pi id (cds) - CD_NAO_MAIS_CARO

-- 2° Isolar os funcionários que venderam esse CD específico
FUNC_VENDEDORES = pi funcionario (vendas join vendas.cd = CD_MAIS_CARO.id CD_MAIS_CARO)

-- 3° BLOCO A: Funcionários com aumento (Multiplicamos o salário por 1.1)
-- Primeiro, pegamos todos os dados originais desses funcionários
DADOS_FUNC_AUMENTO = FUNC_VENDEDORES join funcionarios

-- Aplicamos o aumento de 10% projetando e calculando a nova coluna de salário
FUNC_COM_AUMENTO = pi id, nome, cpf, fone, rg, endereco, salario * 1.1->salario, turno (DADOS_FUNC_AUMENTO)

-- 4° BLOCO B: Funcionários que NÃO ganham aumento
-- Usamos a diferença para isolar quem ficou de fora do bônus
FUNC_SEM_AUMENTO = funcionarios - DADOS_FUNC_AUMENTO

-- 5° A ATRIBUIÇÃO FINAL (A alteração real no banco de dados)
-- Unimos os que mudaram com os que continuam iguais e salvamos por cima da tabela original
funcionarios <- FUNC_COM_AUMENTO union FUNC_SEM_AUMENTO
