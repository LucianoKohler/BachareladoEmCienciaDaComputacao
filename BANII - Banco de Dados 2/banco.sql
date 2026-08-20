CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    desc_categoria TEXT
);

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150),
    login VARCHAR(50) UNIQUE,
    senha VARCHAR(255),
    e_mail VARCHAR(150) UNIQUE,
    perfil_acesso VARCHAR(50)
);

CREATE TABLE inventario (
    id SERIAL PRIMARY KEY,
    data_realiz DATE,
    status VARCHAR(50),
    obs_gerais TEXT
);

CREATE TABLE fornecedor (
    id SERIAL PRIMARY KEY,
    cnpj VARCHAR(20) UNIQUE,
    raz_social VARCHAR(150),
    nome_fant VARCHAR(150),
    telefone VARCHAR(20),
    rua VARCHAR(150),
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(2),
    cep VARCHAR(10)
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    tipo_cliente CHAR(2),
    telefone VARCHAR(20),
    endereco TEXT,
    
    nome VARCHAR(150),
    cpf VARCHAR(20) UNIQUE,
    raz_social VARCHAR(150),
    cnpj VARCHAR(20) UNIQUE
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    unidade_medida VARCHAR(20),
    preco_custo DECIMAL(10, 2),
    preco_venda DECIMAL(10, 2),
    estoque_min DECIMAL(10, 2) DEFAULT 0,
    estoque_atual DECIMAL(10, 2) DEFAULT 0,

    categoria_id INT REFERENCES categoria(id)
);

CREATE TABLE compra (
    id SERIAL PRIMARY KEY,
    data_emissao DATE,
    data_entrega DATE,
    valor_total DECIMAL(10, 2),
    status VARCHAR(50)
        CHECK (status IN ('ABERTO', 'FINALIZADA', 'COTACAO')),
    fornecedor_id INT REFERENCES fornecedor(id)
);

CREATE TABLE venda (
    id SERIAL PRIMARY KEY,
    data DATE,
    hora TIME,
    valor_total DECIMAL(10, 2),
    cliente_id INT REFERENCES cliente(id)
);

CREATE TABLE historico_precos (
    id SERIAL PRIMARY KEY,
    preco_anterior DECIMAL(10, 2),
    novo_preco DECIMAL(10, 2),
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    produto_id INT REFERENCES produto(id),
    usuario_id INT REFERENCES usuario(id)
);

CREATE TABLE fornecimento (
    prazo_entrega INT,
    preco_praticado DECIMAL(10, 2),
    produto_id INT REFERENCES produto(id),
    fornecedor_id INT REFERENCES fornecedor(id)
);

CREATE TABLE prod_inventario (
    id SERIAL PRIMARY KEY,
    qtd_contada DECIMAL(10, 2),
    observacoes TEXT,
    produto_id INT REFERENCES produto(id),
    inventario_id INT REFERENCES inventario(id)
);

CREATE TABLE item_compra (
    id SERIAL PRIMARY KEY,
    qtd DECIMAL(10, 2),
    qtd_recebida DECIMAL(10, 2) DEFAULT 0,
    preco_unit DECIMAL(10, 2),
    compra_id INT REFERENCES compra(id),
    produto_id INT REFERENCES produto(id)
);

CREATE TABLE item_venda (
    id SERIAL PRIMARY KEY,
    qtd DECIMAL(10, 2),
    preco_unit DECIMAL(10, 2),
    subtotal DECIMAL(10, 2),
    venda_id INT REFERENCES venda(id),
    produto_id INT REFERENCES produto(id)
);

CREATE TABLE movimentacao (
    id SERIAL PRIMARY KEY,
    
    data DATE,
    hora TIME,
    tipo VARCHAR(20),
    qtd_movimentada DECIMAL(10, 2),

    produto_id INT REFERENCES produto(id),
    usuario_id INT REFERENCES usuario(id),
    item_venda_id INT REFERENCES item_venda(id),
    item_compra_id INT REFERENCES item_compra(id),
    prod_inventario_id INT REFERENCES prod_inventario(id)
);