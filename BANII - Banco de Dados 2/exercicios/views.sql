-- Visão 1: Estoque Crítico — Produtos que Precisam de Reposição
CREATE OR REPLACE VIEW estoque_critico AS
SELECT 
    p.codigo_produto, 
    p.nome_produto, 
    c.nome_categoria, 
    f.nome_fornecedor,
    p.estoque_atual,
    p.estoque_minimo,
    (p.estoque_minimo - p.estoque_atual) AS quantidade_sugerida_reposicao
FROM produto p
LEFT JOIN categoria c USING(id_categoria)
LEFT JOIN fornecedor f USING(id_fornecedor)
WHERE p.estoque_atual <= p.estoque_minimo;


-- Visão 2: Posição de Estoque por Depósito
CREATE OR REPLACE VIEW posicao_estoque_deposito AS
SELECT 
    d.nome_deposito,
    p.codigo_produto,
    p.nome_produto,
    p.preco_venda,
    ed.quantidade AS quantidade_disponivel,
    (ed.quantidade * p.preco_venda) AS valor_total
FROM estoque_deposito ed
JOIN produto p USING(id_produto)
JOIN deposito d USING(id_deposito)
WHERE ed.quantidade > 0;


-- Visão 3: Resumo de Vendas por Produto
CREATE OR REPLACE VIEW resumo_vendas_produto AS
SELECT 
    p.codigo_produto,
    p.nome_produto,
    COUNT(DISTINCT pv.id_pedido_venda) AS total_pedidos,
    COALESCE(SUM(ipv.quantidade), 0) AS quantidade_total_vendida,
    COALESCE(SUM(ipv.subtotal), 0) AS receita_bruta_acumulada,
    COALESCE(AVG(ipv.subtotal), 0) AS ticket_medio_linha
FROM produto p
LEFT JOIN (
    pedido_venda pv 
    JOIN item_pedido_venda ipv USING(id_pedido_venda)
) ON p.id_produto = ipv.id_produto AND pv.status = 'FATURADO'
GROUP BY p.codigo_produto, p.nome_produto;


-- Visão 4: Resumo de Compras por Fornecedor
CREATE OR REPLACE VIEW resumo_compras_fornecedor AS
SELECT 
    f.nome_fornecedor,
    COALESCE(oc_todas.total_emitidas, 0) AS total_ordens_emitidas,
    COALESCE(oc_recebidas.total_recebidas, 0) AS total_ordens_recebidas,
    COALESCE(oc_recebidas.valor_total_comprado, 0) AS valor_total_comprado_recebido,
    COALESCE(oc_todas.valor_medio, 0) AS valor_medio_por_ordem
FROM fornecedor f
LEFT JOIN (
    SELECT id_fornecedor, COUNT(id_ordem_compra) AS total_emitidas, AVG(valor_total) AS valor_medio
    FROM ordem_compra
    GROUP BY id_fornecedor
) oc_todas USING(id_fornecedor)
LEFT JOIN (
    SELECT id_fornecedor, COUNT(id_ordem_compra) AS total_recebidas, SUM(valor_total) AS valor_total_comprado
    FROM ordem_compra
    WHERE status = 'RECEBIDO'
    GROUP BY id_fornecedor
) oc_recebidas USING(id_fornecedor);


-- Visão 5: Movimentações Detalhadas de Estoque
CREATE OR REPLACE VIEW movimentacoes_detalhadas AS
SELECT 
    m.data_movimentacao,
    m.tipo_movimentacao,
    p.codigo_produto,
    p.nome_produto,
    m.quantidade,
    d_orig.nome_deposito AS deposito_origem,
    d_dest.nome_deposito AS deposito_destino,
    u.nome_completo AS nome_usuario
FROM movimentacao m
JOIN produto p USING(id_produto)
JOIN usuario u USING(id_usuario)
LEFT JOIN deposito d_orig ON m.id_deposito_origem = d_orig.id_deposito
LEFT JOIN deposito d_dest ON m.id_deposito_destino = d_dest.id_deposito;


-- Visão 6: Pedidos de Venda em Aberto ou Confirmados
CREATE OR REPLACE VIEW pedidos_abertos_confirmados AS
SELECT 
    pv.id_pedido_venda,
    pv.data_pedido,
    pv.status,
    c.nome_cliente,
    c.cpf_cnpj,
    u.nome_completo AS nome_usuario,
    pv.valor_total
FROM pedido_venda pv
JOIN cliente c USING(id_cliente)
JOIN usuario u USING(id_usuario)
WHERE pv.status IN ('ABERTO', 'CONFIRMADO');


-- Visão 7: Ordens de Compra Pendentes
CREATE OR REPLACE VIEW ordens_compra_pendentes AS
SELECT 
    oc.id_ordem_compra,
    oc.data_emissao,
    oc.data_previsao,
    f.nome_fornecedor,
    f.cnpj,
    oc.valor_total AS valor_total_estimado,
    (oc.data_previsao - CURRENT_DATE) AS dias_restantes
FROM ordem_compra oc
JOIN fornecedor f USING(id_fornecedor)
WHERE oc.status = 'PENDENTE';


-- Visão 8: Desempenho de Estoque por Categoria
-- Não consegui fazer a margem bruta média estimada.
CREATE OR REPLACE VIEW desempenho_estoque_categoria AS
SELECT 
    c.nome_categoria,
    COUNT(DISTINCT p.id_produto) AS total_produtos_cadastrados,
    COALESCE(SUM(ed.quantidade), 0) AS quantidade_total_estoque,
    COALESCE(SUM(ed.quantidade * p.preco_venda), 0) AS valor_total_estoque,
    COALESCE(SUM(ed.quantidade * p.preco_custo), 0) AS custo_total_estoque,
FROM categoria c
LEFT JOIN produto p USING(id_categoria)
LEFT JOIN estoque_deposito ed USING(id_produto)
GROUP BY c.id_categoria, c.nome_categoria;


-- Visão 9: Clientes Sem Pedidos de Venda
CREATE OR REPLACE VIEW clientes_sem_pedidos AS
SELECT 
    c.id_cliente,
    c.nome_cliente,
    c.cpf_cnpj,
    c.telefone,
    c.mail
FROM cliente c
LEFT JOIN pedido_venda pv USING(id_cliente)
WHERE pv.id_pedido_venda IS NULL;


-- Visão 10: Produtos Sem Movimentação de Estoque
CREATE OR REPLACE VIEW produtos_sem_movimentacao AS
SELECT 
    p.codigo_produto,
    p.nome_produto,
    c.nome_categoria,
    p.preco_venda,
    p.estoque_minimo
FROM produto p
LEFT JOIN categoria c USING(id_categoria)
LEFT JOIN movimentacao m USING(id_produto)
WHERE m.id_movimentacao IS NULL;

SELECT * FROM posicao_estoque_deposito