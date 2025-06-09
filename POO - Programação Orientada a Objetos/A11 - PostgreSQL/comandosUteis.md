
# Comentando em SQL
```sql
-- Comentário simples

/*
Comentário
multi-linha
*/
```

# Criando tabelas com ID
```sql
-- Dando ao ID uma autoincrementação
CREATE SEQUENCE pessoa_id_seq;

-- Criando a tabela dando PK pro id e dando sempre o próximo valor da sequencia
CREATE TABLE pessoa (
    id INT PRIMARY KEY DEFAULT nextval('pessoa_id_seq'),
    nome TEXT,
    idade INT
);

```

# Inserindo valores na tabela
```sql
-- Inserindo uma pessoa
INSERT INTO pessoa values (DEFAULT, 'Lucas', 11111);
-- Ou
INSERT INTO pessoa (nome, idade) values ('Lucas', 11)
```

# Criando uma tabela com FK
```sql
CREATE SEQUENCE end_seq;

CREATE TABLE endereco(
	id INT PRIMARY KEY DEFAULT nextval('end_seq'),
	nome varchar(100),
	cep varchar(8),
	id_pessoa INT, -- primeiro cria
	
	FOREIGN KEY (id_pessoa) REFERENCES pessoa(id) -- depois referencia
)

-- Inserindo na tabela
INSERT INTO endereco(DEFAULT, "procopio gomes", "92929110", 1);
-- Usamos o id da pessoa para referencia-la
```

# Selecionando dados da Tabela
```sql
SELECT * FROM pessoa;
-- Seleciona tudo da tabela pessoa

SELECT * FROM pessoa WHERE cpf = 1111;
-- Seleciona tudo da tabela pessoa cujo cpf seja igual a 1111

SELECT id,nome FROM pessoa;
-- Seleciona só o id e o nome da tabela pessoa

SELECT count(*) FROM pessoa;
-- Mostra a quantidade de registros da tabela pessoa

SELECT max(numero) FROM endereco WHERE rua = 'rua 1';
-- Mostra o registro com maior numero da rua 1
```

# Atualizando dados
```sql
UPDATE pessoa SET nome = 'joao', cpf = '22222' WHERE id = 1
-- Atualiza a os dados da pessoa cujo id seja 1 (pode achar pelo nome)

UPDATE produto SET qtd_produto += 10 WHERE nome = 'alvejante';
-- Aumenta o campo qtd em 10
```

# Deletando dados
```sql
DELETE FROM pessoa WHERE id = 1
-- apaga o registro de id 1
```

# Outros comandos para alterar a tabela
```sql
DROP TABLE tabela;
-- Apaga a tabela inteira

ALTER TABLE ADD COLUMN rg INT;
-- Insere uma nova coluna à tabela

ALTER TABLE endereco DROP COLUMN id_pessoa CASCADE;
-- Apaga o campo id_pessoa, e se esse id_pessoa for referenciado em outra tabela, substituirá todos os valores por null
```