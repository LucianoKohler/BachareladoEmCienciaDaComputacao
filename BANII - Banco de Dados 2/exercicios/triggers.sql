-- Trigger 1 e 2: Atualiza valor total em pedido e ordem

-- Tabelas: item_ordem_compra, item_pedido_venda
-- Operações: Insert, Delete, Update
-- Quando: after
-- nível: Row

CREATE OR REPLACE FUNCTION atualiza_cabecalho() RETURNS TRIGGER AS 
$$
DECLARE
	v_total NUMERIC(12, 2) := 0;
	id_ordem INT;
BEGIN	
	IF TG_OP = 'INSERT' THEN
		v_total = NEW.subtotal;
		id_ordem = NEW.id_ordem;
	ELSIF TG_OP = 'DELETE' THEN
		v_total = -OLD.subtotal;
	ELSE
		v_total = OLD.subtotal - NEW.subtotal;
	END IF;

	IF TG_TABLE_NAME = 'item_ordem_compra' THEN
		IF TG_OP IN ('INSERT', 'UPDATE') THEN
			id_ordem = NEW.id_ordem_compra;
		ELSE
			id_ordem = OLD.id_ordem_compra;
		END IF;
		
		UPDATE ordem_compra 
			SET valor_total = valor_total + v_total 
			WHERE id_ordem_compra = NEW.id_ordem_compra;
	ELSE
	IF TG_OP IN ('INSERT', 'UPDATE') THEN
			id_ordem = NEW.id_pedido_venda;
		ELSE
			id_ordem = OLD.id_pedido_venda;
		END IF;
		
		UPDATE pedido_venda 
			SET valor_total = valor_total + v_total 
			WHERE id_pedido_venda = NEW.id_pedido_venda;
	END IF;
		RETURN NULL;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER atualiza_cabecalho AFTER INSERT OR UPDATE OR DELETE
ON item_pedido_venda FOR EACH ROW EXECUTE PROCEDURE atualiza_cabecalho();

CREATE OR REPLACE TRIGGER atualiza_cabecalho AFTER INSERT OR UPDATE OR DELETE
ON item_ordem_compra FOR EACH ROW EXECUTE PROCEDURE atualiza_cabecalho();

-- Trigger 3: Atualização de saldo físico

CREATE OR REPLACE FUNCTION atualiza_estoque() RETURNS TRIGGER AS
$$
DECLARE
	quantidadeTemp INT;
BEGIN
	IF new.id_deposito_origem IS NOT NULL THEN
		INSERT INTO estoque_deposito (id_produto, id_deposito, quantidade)
		VALUES (new.id_produto, new.id_deposito_origem, 0) ON CONFLICT DO NOTHING;
	END IF;
	
	IF new.id_deposito_destino IS NOT NULL THEN
		INSERT INTO estoque_deposito (id_produto, id_deposito, quantidade)
		VALUES (new.id_produto, new.id_deposito_destino, 0) ON CONFLICT DO NOTHING;
	END IF;

	IF new.tipo_movimentacao = 'ENTRADA' THEN
		UPDATE estoque_deposito 
		SET quantidade = quantidade + new.quantidade 
		WHERE id_produto = new.id_produto AND id_deposito = new.id_deposito_destino;

		UPDATE produto 
		SET estoque_atual = estoque_atual + new.quantidade
		WHERE id_produto = new.id_produto;
		
	ELSIF new.tipo_movimentacao = 'SAIDA' THEN
		SELECT quantidade FROM estoque_deposito 
		WHERE id_produto = new.id_produto
		AND id_deposito = new.id_deposito_origem
		INTO quantidadeTemp;

		IF quantidadeTemp < new.quantidade THEN
			RAISE EXCEPTION 'Saldo de origem insuficiente.';
		END IF;

		UPDATE estoque_deposito
		SET quantidade = quantidade - new.quantidade
		WHERE id_produto = new.id_produto AND id_deposito = new.id_deposito_origem;

		UPDATE produto 
		SET estoque_atual = estoque_atual - new.quantidade
		WHERE id_produto = new.id_produto;
	
	ELSIF new.tipo_movimentacao = 'TRANSFERENICA' THEN
		SELECT quantidade FROM estoque_deposito 
		WHERE id_produto = new.id_produto
		AND id_deposito = new.id_deposito_origem
		INTO quantidadeTemp;

		IF quantidadeTemp < new.quantidade THEN
			RAISE EXCEPTION 'Saldo de origem insuficiente.';
		END IF;

		UPDATE estoque_deposito
		SET quantidade = quantidade - new.quantidade
		WHERE id_produto = new.id_produto AND id_deposito = new.id_deposito_origem;

		UPDATE estoque_deposito 
		SET quantidade = quantidade + new.quantidade 
		WHERE id_produto = new.id_produto AND id_deposito = new.id_deposito_destino;
	END IF;

	RETURN NULL;
END;
$$
LANGUAGE plpgsql;

/*
CREATE OR REPLACE TRIGGER atualiza_estoque AFTER INSERT ON movimentacao FOR EACH ROW EXECUTE PROCEDURE atualiza_estoque();

SELECT * FROM estoque_deposito;
SELECT * FROM produto;
SELECT * FROM movimentacao;

INSERT INTO movimentacao 
	(tipo_movimentacao, quantidade, id_produto, id_deposito_origem, id_usuario)
	VALUES ('ENTRADA', 20, 10, 5, 1);
*/

-- Trigger 4: Validação de preços

CREATE OR REPLACE FUNCTION valida_produto() RETURNS TRIGGER AS
$$
DECLARE

BEGIN
	IF new.preco_custo <= 0 THEN	
		RAISE EXCEPTION 'Preço de custo do produto % menor ou igual a zero', new.nome_produto;
	END IF;

	IF new.preco_venda <= new.preco_custo THEN
		RAISE EXCEPTION 'Preço de venda do produto % menor ou igual a zero', new.nome_produto;
	END IF;

	IF new.estoque_minimo < 0 THEN
		RAISE EXCEPTION 'Estoque minimo nao alcançado';
	END IF;

	RETURN new;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER valida_produto BEFORE INSERT OR UPDATE
ON produto FOR EACH ROW EXECUTE PROCEDURE valida_produto();
/*

INSERT INTO produto (codigo_produto, nome_produto, preco_custo, preco_venda, estoque_minimo)
	VALUES ('SPHEAL', 'SPHEAL', 20, 10, -10);

SELECT * FROM produto
*/

-- Trigger 5: Verificar saldo disponível pra saídas/reservas de estoque
CREATE OR REPLACE FUNCTION verifica_saldo() RETURNS TRIGGER AS
$$
DECLARE
	estoqueAtual INT;
	nomeProduto VARCHAR;
BEGIN
	IF new.tipo_movimentacao NOT IN ('SAIDA', 'RESERVA') THEN
		RETURN new;
	END IF;

	SELECT estoque_atual, nome_produto FROM produto
	WHERE id_produto = new.id_produto
	INTO estoqueAtual, nomeProduto;

	IF estoque_atual < new.quantidade THEN
		RAISE EXCEPTION 'Saldo insuficiente para o produto % com estoque atual %', nomeProduto, estoqueAtual;
	END IF;
	
	RETURN new;
	
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER verifica_saldo BEFORE INSERT ON movimentacao
FOR EACH ROW EXECUTE PROCEDURE verifica_saldo();

/*
SELECT * FROM movimentacao;
SELECT * FROM produto;

INSERT INTO movimentacao (tipo_movimentacao, quantidade, id_produto, id_deposito_destino, id_usuario)
VALUES ('SAIDA', 10, 5, 1, 1);
*/

-- Trigger 6: Validar transição de estados
CREATE OR REPLACE FUNCTION valida_transicao_status() RETURNS TRIGGER AS
$$
DECLARE
BEGIN

	IF new.status = old.status THEN
		RETURN new;
	END IF;

	IF old.status IN ('FATURADO', 'CANCELADO') THEN
		RAISE EXCEPTION 'pedido não pode ser alterado, pois foi %', old.status;
	END IF;

	IF NOT 
		  (old.status = 'ABERTO' AND new.status IN ('CANCELADO', 'CONFIRMADO') 
		OR old.status = 'CONFIRMADO' AND new.status IN ('FATURADO', 'CANCELADO')) THEN
			RAISE EXCEPTION 'Pedido não pode transicionar do status % para o status %', old.status, new.status;
	END IF;

	RETURN new;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER valida_transicao_status BEFORE UPDATE ON pedido_venda
FOR EACH ROW EXECUTE PROCEDURE valida_transicao_status();

/*
UPDATE pedido_venda SET status = 'CANCELADO' WHERE id_pedido_venda = 1;
*/