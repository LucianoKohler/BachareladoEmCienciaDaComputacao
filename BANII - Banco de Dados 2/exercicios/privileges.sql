-- Exercício 1: Criar os Grupos de Acesso

CREATE ROLE grp_operadores NOLOGIN;
CREATE ROLE grp_gerentes NOLOGIN;

-- Exercício 2: Criar os Usuários e Associá-los aos Grupos

CREATE ROLE usr_joao LOGIN PASSWORD '0000';
GRANT grp_operadores TO usr_joao;

CREATE ROLE usr_maria LOGIN PASSWORD '0000';
GRANT grp_operadores TO usr_maria;

CREATE ROLE usr_carlos LOGIN PASSWORD '0000';
GRANT grp_gerentes TO usr_carlos;

CREATE ROLE usr_ana LOGIN PASSWORD '0000';
GRANT grp_gerentes TO usr_ana;

-- Exercício 3: Conceder Consulta nas Tabelas de Cadastro ao Grupo de Operadores

GRANT SELECT ON produto, categoria, fornecedor, cliente TO grp_operadores;

-- Exercício 4: Conceder Inserção e Atualização na Tabela de Movimentações ao Grupo de Operadores

GRANT INSERT, UPDATE ON movimentacao TO grp_operadores;

SELECT * FROM information_schema.table_privileges WHERE grantee = 'grp_operadores';

-- Exercício 5: Conceder Atualização em Colunas Específicas da Tabela produto ao Grupo de Operadores

GRANT UPDATE (estoque_atual, estoque_minimo) ON TABLE produto TO grp_operadores;

-- Exercício 6: Conceder Leitura e Inserção em Pedido de Venda à Usuária Maria

GRANT SELECT, INSERT ON TABLE pedido_venda, item_pedido_venda TO usr_maria;

-- Exercício 7: Conceder Permissões Completas de Manutenção em Ordens de Compra ao Gerente Carlos

GRANT ALL privileges ON ordem_compra, item_ordem_compra TO usr_carlos;

-- Exercício 8: Conceder Leitura nas Quatro Primeiras Visões ao Grupo de Gerentes

GRANT SELECT ON visaoGerente1, visaoGerente2, visaoGerente3, visaoGerente4 TO grp_gerentes;

-- Exercício 9: Revogar a Permissão de Exclusão na Tabela produto do Grupo de Operadores

REVOKE DELETE ON TABLE produto FROM grp_operadores;

-- Exercício 10: Conceder Todos os Privilégios sobre as Visões de Relatório à Analista Ana

ALTER DEFAULT PRIVILEGES IN SCHEMA PUBLIC GRANT ALL PRIVILEGES ON TABLES TO usr_ana;