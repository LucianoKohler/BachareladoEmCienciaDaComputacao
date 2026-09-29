-- Função 1: Registrar Entrada de Estoque em Depósito
CREATE OR REPLACE FUNCTION registra_entrada_estoque (
	p_id_produto INT, 
	p_id_deposito INT, 
	p_quantidade NUMERIC, 
	p_id_usuario INT, 
	p_observacao TEXT
) RETURNS TEXT
AS 
$$

DECLARE
	v_novo_estoque NUMERIC;
	v_id_movimentacao INT;
	
BEGIN
	IF NOT EXISTS (SELECT 1 FROM produto WHERE id_produto = p_id_produto) THEN
		RAISE EXCEPTION 'O produto com id % não existe', p_id_produto;
	END IF;
	
	UPDATE produto 
		SET estoque_atual = estoque_atual + p_quantidade
		WHERE id_produto = p_id_produto
	RETURNING estoque_atual INTO v_novo_estoque;

	INSERT INTO movimentacao(
		data_movimentacao,
		tipo_movimentacao,
		quantidade,
		id_produto,
		id_deposito_destino,
		id_usuario,
		observacao
	) VALUES (NOW(), 'ENTRADA', p_quantidade, p_id_produto, p_id_deposito, p_id_usuario, p_observacao)
	RETURNING id_movimentacao INTO v_id_movimentacao;

	INSERT INTO estoque_deposito(
	id_produto,
	id_deposito,
	quantidade
	) VALUES(p_id_produto, p_id_deposito, p_quantidade)
	ON CONFLICT (id_produto, id_deposito)
	DO UPDATE SET quantidade = estoque_deposito.quantidade + EXCLUDED.quantidade;

RETURN FORMAT(
		'Entrega registrada, novo Estoque = %s, ID da movimentacao = %s', 
		v_novo_estoque, v_id_movimentacao
	);
END;
$$
language plpgsql;
-- SELECT registra_entrada_estoque(8, 1, 50, 1, 'qualidade do estoque duvidosa')

-- Função 2: Abrir Pedido de Venda com Itens
-- Exemplo de JSON: [{"id_produto": 1, "quantidade": 5}]

CREATE OR REPLACE FUNCTION abrir_pedido_venda (
	p_id_cliente INT,
    p_id_usuario INT,
    p_itens JSONB
) RETURNS TABLE(id_pedido INT, valor_total NUMERIC)

AS
$$
DECLARE
v_id_pedido INT;
    v_item JSONB;
    v_preco NUMERIC;
    v_subtotal NUMERIC;
    v_total NUMERIC := 0;	
BEGIN
	IF jsonb_array_length(p_itens) = 0 THEN
		RAISE EXCEPTION 'pedido sem itens!';
	END IF;
	
	INSERT INTO pedido_venda(id_cliente, id_usuario, data_pedido, status, valor_total)
	VALUES (p_id_cliente, p_id_usuario, NOW(), 'ABERTO', 0)
	RETURNING id_pedido_venda INTO v_id_pedido;
	
	FOR v_item IN SELECT * FROM jsonb_array_elements(p_itens)
	LOOP
	
		SELECT preco_venda FROM produto WHERE id_produto = (v_item->> 'id_produto') :: INT INTO v_preco;
		
		v_subtotal := v_preco * (v_item ->> 'quantidade')::NUMERIC;
        v_total := v_total + v_subtotal;		
		
		INSERT INTO item_pedido_venda (id_pedido_venda, id_produto, quantidade, valor_unitario)
		VALUES (v_id_pedido, (v_item->>'id_produto') :: INT, (v_item ->>'quantidade') :: INT, v_preco);
	END LOOP;
	
	UPDATE pedido_venda SET valor_total = v_total WHERE id_pedido_venda = v_id_pedido;
	
	RETURN QUERY SELECT v_id_pedido, v_total;
END;
$$
LANGUAGE plpgsql;

SELECT * FROM abrir_pedido_venda(1, 1, '[{"id_produto": 1, "quantidade": 5}]');

-- Função 3: Cancelar Pedido de Venda e Reverter Reservas

CREATE OR REPLACE FUNCTION cancela_pedido_venda(
	p_id_pedido_venda INT,
	p_id_usuario INT
)
RETURNS TABLE(status_anterior TEXT, status_novo TEXT) AS
$$
DECLARE
	v_status INT;
	v_item RECORD;   
BEGIN
	SELECT status INTO v_status FROM pedido_venda WHERE id_pedido_venda = p_id_pedido_venda;

	IF v_status IS NULL OR v_status IN ('FATURADO', 'CANCELADO') THEN
		RAISE EXCEPTION 'O pedido % com status % nao pode ser cancelado', p_id_pedido_venda, status_pedido;
	END IF;

	IF v_status = 'CONFIRMADO' THEN
		FOR v_item IN	
			SELECT id_produto, quantidade FROM item_pedido_venda
			WHERE id_pedido_venda = p_id_pedido_venda
		LOOP
			UPDATE produto SET estoque_atual = estoque_atual + v_item.quantidade
			WHERE id_produto = v_item.id_produto;

			INSERT INTO movimentacao(tipo_movimentacao, quantidade, id_produto, id_usuario, observacao)
			VALUES ('BAIXA_RESERVA', v_item.quantidade, v_item.id_produto, p_id_usuario, 'O pedido foi cancelado');
		END LOOP;
	END IF;

	UPDATE pedido_venda SET status = 'CANCELADO' WHERE id_pedido_venda = p_id_pedido_venda;

	RETURN QUERY SELECT V_STATUS::text, 'CANCELADO'::TEXT;
END;
$$ LANGUAGE plpgsql;

-- Função 4: Transferir Estoque Entre Depósitos

CREATE OR REPLACE FUNCTION transfere_estoque(
	p_id_produto INTEGER,
	p_id_deposito_origem INTEGER,
	p_id_deposito_destino INTEGER,
	p_quantidade NUMERIC,
	p_id_usuario INTEGER
)
RETURNS TABLE(saldo_origem NUMERIC, saldo_destino NUMERIC) AS
$$
DECLARE 
	v_quantidade_origem INTEGER;
	v_saldo_origem NUMERIC;
	v_saldo_destino NUMERIC;
	
BEGIN
	IF p_id_deposito_origem = p_id_deposito_destino THEN 
		RAISE EXCEPTION 'Depositos devem ser diferentes';
	END IF;

	IF NOT EXISTS (SELECT 1 FROM deposito WHERE id_deposito = p_id_deposito_origem) THEN
		RAISE EXCEPTION 'Deposito de origem não existe';
	END IF;

	IF NOT EXISTS (SELECT 1 FROM deposito WHERE id_deposito = p_id_deposito_destino) THEN
		RAISE EXCEPTION 'Deposito de destino não existe';
	END IF;

	SELECT quantidade INTO v_quantidade_origem FROM estoque_deposito
		WHERE id_produto = p_id_produto AND id_deposito = p_id_deposito_origem;
	IF v_quantidade_origem IS NULL OR v_quantidade_origem < p_quantidade THEN
		RAISE EXCEPTION 'Quantidade menor que disponível ou nula';
	END IF;

	UPDATE estoque_deposito SET quantidade = quantidade - p_quantidade
		WHERE id_produto = p_id_produto AND id_deposito = p_id_deposito_origem
		RETURNING quantidade INTO v_saldo_origem;

	INSERT INTO estoque_deposito (id_produto, id_deposito, quantidade)
		VALUES (p_id_produto, p_id_deposito_destino, p_quantidade)
		ON CONFLICT (id_produto, id_deposito)
		DO UPDATE SET quantidade = estoque_deposito.quantidade + EXCLUDED.quantidade
		RETURNING quantidade INTO v_saldo_destino;

		INSERT INTO movimentacao (tipo_movimentacao, quantidade, id_produto, id_deposito_origem,
		id_deposito_destino, id_usuario)
		VALUES('TRNSFERENCIA', p_quantidade, p_id_produto, p_id_deposito_origem, p_id_deposito_destino);

		RETURN QUERY SELECT v_saldo_origem, v_saldo_destino;
END
$$ LANGUAGE plpgsql;

-- Função 5: Atualizar Preços de Custo e Venda de um Produto

CREATE OR REPLACE FUNCTION atualiza_preco_produto(
	p_codigo_produto VARCHAR,
	p_novo_preco_custo NUMERIC,
	p_novo_preco_venda NUMERIC
) RETURNS TABLE (codigo TEXT, nome TEXT, preco_custo_anterior NUMERIC,
preco_venda_anterior  NUMERIC, preco_custo_novo NUMERIC, preco_vendaa_novo NUMERIC) AS

$$
DECLARE
	v_produto RECORD;
	preco_custo_antigo NUMERIC;
	preco_venda_antigo NUMERIC;
	
BEGIN

	IF NOT EXISTS (SELECT 1 FROM produto WHERE codigo_produto = p_codigo_produto) THEN
		RAISE EXCEPTION 'O produto com código dado não existe';
	END IF;

	IF p_novo_preco_custo <= 0 OR p_novo_preco_venda <= 0 THEN
		RAISE EXCEPTION 'Os valores informados sao invalidos';
	END IF;

	IF p_novo_preco_custo > p_novo_preco_venda THEN
		RAISE EXCEPTION 'O produto com codigo dado nao existe';
	END IF;

	SELECT * INTO v_produto FROM produto WHERE codigo_produto = p_codigo_produto;

	preco_custo_antigo := v_produto.preco_custo;
	preco_venda_antigo := v_produto.preco_venda;

	UPDATE produto SET 
		preco_custo = p_novo_preco_custo, 
		preco_venda = p_novo_preco_venda
	WHERE codigo_produto = p_codigo_produto;

	RETURN QUERY SELECT(p_codigo_produto, v_produto.nome_produto,
	v_produto.preco_custo, v_produto.preco_venda, 
	p_novo_preco_custo, p_novo_preco_venda);
END;
$$

LANGUAGE plpgsql;

-- Função 6: Relatório de estoque crítico

CREATE OR REPLACE FUNCTION relatorio_estoque_critico()

RETURNS TABLE(
	codigo_produto VARCHAR,
	nome_produto VARCHAR,
	nome_categoria VARCHAR,
	nome_fornecedor VARCHAR,
	estoque_atual INTEGER,
	estoque_minimo INTEGER,
	qtd_sugerida_reposicao INTEGER
)

AS 
$$
BEGIN
	RETURN QUERY
	SELECT 
		p.codigo_produto,
		p.nome_produto, 
		c.nome_categoria, 
		f.nome_fornecedor,
		p.estoque_atual,
		p.estoque_minimo,
		(p.estoque_minimo - p.estoque_atual + 10)  qtd_sugerida
	FROM produto p 
		LEFT JOIN categoria c USING(id_categoria)
		LEFT JOIN fornecedor f USING(id_fornecedor)
	WHERE p.setoque_atual <= p.estoque_minimo;
END;
$$
LANGUAGE plpgsql;

-- Função 7: Relatório de vendas de produto

CREATE OR REPLACE FUNCTION relatorio_vendas_produto(
	p_data_inicio DATE,
	p_data_fim DATE
) RETURNS TABLE(
	codigo_produto VARCHAR,
	nome_produto VARCHAR,
	qtd_pedidos INTEGER,
	total_unidades_vendidas INTEGER,
	receita_bruta_total NUMERIC,
	ticket_medio_item NUMERIC
) AS
$$
BEGIN

	IF p_data_inicio > p_data_fim THEN
		RAISE EXCEPTION 'data de inicio é maior do que a data de fim';
	END IF;

	RETURN QUERY
	SELECT 
		p.codigo_produto,
		p.nome_produto,
		COALESCE(COUNT(DISTINCT pv.id_pedido_venda), 0)::INT AS quantidade_pedidos,
		COALESCE(SUM(ipv.quantidade), 0)::INT AS total_unidades_vendidas,
		COALESCE(SUM(ipv.subtotal), 0 )AS receita_bruta_total,
		COALESCE(AVG(ipv.subtotal), 0) AS ticket_medio
	FROM produto p
		LEFT JOIN item_pedido_venda ipv USING(id_produto)
		LEFT JOIN pedido_venda pv ON pv.id_pedido_venda = ipv.id_pedido_venda
		AND pv.status = 'FATURADO'
		AND pv.data_pedido BETWEEN p_data_inicio AND p_data_fim
	GROUP BY p.codigo_produto, p.nome_produto;
END;
$$
LANGUAGE plpgsql;

-- Função 8: Relatório de histórico de movimentações

CREATE OR REPLACE FUNCTION relatorio_historico_mov(
	p_id_produto INTEGER,
	p_data_inicio DATE,
	p_data_fim DATE
) RETURNS TABLE(
	data_movimentacao TIMESTAMP, tipo_movimentacao VARCHAR, quantidade INT,
	nome_produto VARCHAR, deposito_origem VARCHAR, deposito_destino VARCHAR,
	usuario_responsavel VARCHAR, observacao TEXT
)
AS 
$$
BEGIN
	IF p_data_inicio < p_data_fim THEN
		RAISE EXCEPTION 'data de inicio é maior do que a data de fim';
	END IF;

	IF NOT EXISTS (SELECT 1 FROM produto WHERE id_produto = p_id_produto) THEN
		RAISE EXCEPTION 'produto não existe';
	END IF;

	RETURN QUERY
		SELECT m.data_movimentacao, m.tipo_movimentacao, m.quantidade,
		p.nome_produto, dor.nome_deposito, de.nome_deposito, u.nome_usuario,
		m.observacao
		FROM movimentacao m
			LEFT JOIN produto p USING(id_produto)
			LEFT JOIN deposito dor ON m.id_deposito_origem = dor.id_deposito
			LEFT JOIN deposito dde ON m.id_deposito_destino = dde.id_deposito
			LEFT JOIN usuario u USING(id_usuario)
		WHERE p.id_produto = p_id_produto 
		AND m.data_movimentacao BETWEEN p_data_inicio AND p_data_fim
		ORDER BY m.data_movimentacao DESC;
END;
$$
LANGUAGE plpgsql;

-- Função 9: Relatório de Desempenho de Compras por Fornecedor

CREATE OR REPLACE FUNCTION relatorio_fornecedor() 
	RETURNS TABLE(nome_fornecedor TEXT, cnpj TEXT, total_ordens_emitidas INT, total_ordens_recebidas INT,
	valor_total_comprado NUMERIC, valor_medio_por_ordem NUMERIC, qtd_produtos_distintos INT)
AS
$$
BEGIN
	RETURN QUERY 
		SELECT 
			f.nome_fornecedor::TEXT, 
			f.cnpj::TEXT, 
			COALESCE(COUNT(oc.id_ordem_compra), 0)::INT,
			COALESCE(COUNT(CASE WHEN status = 'RECEBIDO' THEN 1 END), 0)::INT AS total_ordens_recebidas,
			COALESCE(SUM(oc.valor_total), 0)::NUMERIC,
			COALESCE(AVG(oc.valor_total), 0)::NUMERIC,
			COALESCE(COUNT(DISTINCT ioc.id_produto), 0)::INT
		FROM fornecedor f
			LEFT JOIN ordem_compra oc USING(id_fornecedor)
			LEFT JOIN item_ordem_compra ioc USING(id_ordem_compra)
		GROUP BY id_fornecedor;
END;
$$
LANGUAGE plpgsql;

-- Função 10: Relatório de Lucratividade por Categoria em um Período

CREATE OR REPLACE FUNCTION relatorio_lucratividade_p_categoria(p_data_inicio DATE, p_data_fim DATE) 
	RETURNS TABLE(
		nome_categoria TEXT, 
		qtd_produtos INT, 
		qtd_total_estoque INT, 
		valor_total_estoque NUMERIC,
		receita_bruta_periodo NUMERIC, 
		custo_estimado_vendas NUMERIC, 
		margem_bruta_valor NUMERIC,
		margem_bruta_pct NUMERIC
	)
AS
$$
BEGIN

	IF p_data_inicio > p_data_fim THEN
		RAISE EXCEPTION 'data de inicio é maior do que a data de fim';
	END IF;

	
	RETURN QUERY 
		SELECT 
			c.nome_categoria,
			COALESCE(COUNT(p.id_produto), 0),
			COALESCE(SUM(p.estoque_atual), 0),
			COALESCE(SUM(p.estoque_atual * p.preco_venda), 0) AS receita_bruta,
			COALESCE(SUM(pv.valor_total), 0),
			COALESCE(SUM(ipv.quantidade * ipv.valor_unitario), 0),
			1,
			1
		FROM categoria c
			LEFT JOIN produto p USING(id_categoria)
			LEFT JOIN pedido_venda pv USING(id_pedido)
			LEFT JOIN item_pedido_venda ipv USING(id_pedido_venda)
		WHERE p_data_inicio > pv.data_pedido AND p_data_fim < pv.data_pedido
		GROUP BY nome_categoria;
END;
$$
LANGUAGE plpgsql;

