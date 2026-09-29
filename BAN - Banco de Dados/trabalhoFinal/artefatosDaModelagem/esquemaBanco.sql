/* TABELAS */
/* Funcionário: Salva dados do funcionário

 * Ingrediente: Salva a quantidade de cada ingrediente no estoque, além da sua
   unidade de medida para evitar confusões
   
 * Produto: Salva os produtos do cardápio
   
 * Comanda: Salva quando uma comanda é aberta e serve de âncora para salvar os
   pedidos feitos pelo cliente durante sua estadia
   
 * Pedido: Tabela que salva um pedido feito pelo cliente, os produtos pedidos
   ficam em produto_pedido
 
 * Info_produto: Tabela NxN entre produto e ingrediente. Salva informações sobre a
   receita e modo de preparo dos produtos feitos pelos baristas da cafeteria

 * Item_pedido: Tabela criada pela relação NxN entre Pedido e Produto, salva a quantidade
   e quais produtos foram pedidos em um pedido
*/

CREATE TABLE Funcionario(
	ID_funcionario SERIAL PRIMARY KEY,
	nome varchar(40),
	salario float,
	data_contratacao date,
	cargo varchar(20),
    CONSTRAINT check_cargo CHECK (cargo IN ('barista', 'gerente', 'faxineiro'))
);

CREATE TABLE Ingrediente(
	ID_ingrediente SERIAL PRIMARY KEY,
	nome varchar(40),
	descricao varchar(255),
	quantidade float,
	unidade_medida varchar(20)
);

CREATE TABLE Produto(
	ID_produto SERIAL PRIMARY KEY,
	nome varchar(40),
	preco float,
	categoria varchar(20),
    CONSTRAINT check_categoria CHECK (categoria IN ('bebida quente', 'bebida fria', 'salgado', 'doce', 'bolo', 'outros'))
);

CREATE TABLE Comanda(
	ID_comanda SERIAL PRIMARY KEY,  
	numero_mesa int,
	data_hora_abertura timestamp default CURRENT_TIMESTAMP,
	status_pgto varchar(20) DEFAULT 'aberta',
    CONSTRAINT check_status_pgto CHECK (status_pgto IN ('aberta', 'paga'))
);

CREATE TABLE Pedido(
	ID_pedido SERIAL PRIMARY KEY,
    data_hora_abertura timestamp DEFAULT CURRENT_TIMESTAMP,
    status_pedido varchar(20) DEFAULT 'pendente',
    observacao varchar(255),
	FK_comanda int,
	FK_funcionario int,

    CONSTRAINT fk_pedido_comanda FOREIGN KEY (FK_comanda) REFERENCES Comanda(ID_comanda) ON DELETE CASCADE,
    CONSTRAINT fk_pedido_funcionario FOREIGN KEY (FK_funcionario) REFERENCES Funcionario(ID_funcionario) ON DELETE SET NULL,
    CONSTRAINT check_status CHECK (status_pedido IN ('pendente', 'atendido', 'cancelado'))
);

CREATE TABLE Info_produto(
	ID_info_produto SERIAL PRIMARY KEY,
	quantidade float,
	observacao varchar(55),
	FK_ingrediente int,
	FK_produto int,

    CONSTRAINT fk_info_ingrediente FOREIGN KEY (FK_ingrediente) REFERENCES Ingrediente(ID_ingrediente) ON DELETE CASCADE,
    CONSTRAINT fk_info_produto FOREIGN KEY (FK_produto) REFERENCES Produto(ID_produto) ON DELETE CASCADE
);

CREATE TABLE Item_pedido(
	ID_item_pedido SERIAL PRIMARY KEY,
	quantidade int,
	preco_unitario float,
	FK_pedido int,
	FK_produto int,

    CONSTRAINT fk_item_pedido FOREIGN KEY (FK_pedido) REFERENCES Pedido(ID_pedido) ON DELETE CASCADE,
    CONSTRAINT fk_item_produto FOREIGN KEY (FK_produto) REFERENCES Produto(ID_produto) ON DELETE CASCADE
);