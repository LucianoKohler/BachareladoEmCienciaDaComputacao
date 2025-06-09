/*
Apagando tudo do banco para poder rodá-lo
o código mais vezes sem redeclarar tabelas
*/

DROP TABLE IF EXISTS pessoa CASCADE;
DROP TABLE IF EXISTS animal CASCADE;
DROP TABLE IF EXISTS endereco CASCADE;
DROP TABLE IF EXISTS tipoAnimal CASCADE;

DROP SEQUENCE IF EXISTS seq_pessoa CASCADE;
DROP SEQUENCE IF EXISTS seq_animal CASCADE;
DROP SEQUENCE IF EXISTS seq_endereco CASCADE;
DROP SEQUENCE IF EXISTS seq_tipoAnimal CASCADE;

/* FIM DA REINICIALIZAÇÃO */

-- Criando as sequencias para as tabelas
CREATE SEQUENCE seq_pessoa;
CREATE SEQUENCE seq_animal;
CREATE SEQUENCE seq_endereco;
CREATE SEQUENCE seq_tipoAnimal;

/* CRIANDO AS TABELAS */

CREATE TABLE endereco(
	id INT PRIMARY KEY DEFAULT nextval('seq_endereco'),
	rua varchar(50),
	numero INT,
	cep varchar(8),
	cidade varchar(50),
	estado varchar(50),
	pais varchar(50)
);

CREATE TABLE pessoa(
	id INT PRIMARY KEY DEFAULT nextval('seq_pessoa'),
	nome varchar(50),
	cpf varchar(11),
	id_endereco INT, -- FK
	/* Como uma pessoa pode ter N animais mas um animal pertence somente
	à uma pessoa, é mais fácil referenciar a pessoa na tabela animal */
	
	FOREIGN KEY (id_endereco) REFERENCES endereco(id)
);

CREATE TABLE tipoAnimal(
	id INT PRIMARY KEY DEFAULT nextval('seq_tipoAnimal'),
	tipo varchar(20)
);

CREATE TABLE animal(
	id INT PRIMARY KEY DEFAULT nextval('seq_animal'),
	nome varchar(20),
	idade INT,
	descricao varchar(100),
	tipoAnimal INT, -- Puxa o enum criado no topo do .sql
	idDono INT,

	FOREIGN KEY (idDono) REFERENCES pessoa(id),
	FOREIGN KEY (tipoAnimal) REFERENCES tipoAnimal(id)
);

/* FIM DA CRIAÇÃO DAS TABELAS */

/* COLOCANDO VALORES NA TABELA */

-- Ruas
INSERT INTO endereco values
(DEFAULT, 'Rua Benjamin Constant', 221, '89204361', 'Joinville', 'Santa Catarina', 'Brasil'),
(DEFAULT, 'Rua Deputado Guilherme Urban', 75, '89204290', 'Joinville', 'Santa Catarina', 'Brasil'),
(DEFAULT, 'Estrada da Tromba', 280, '89239440', 'Joinville', 'Santa Catarina', 'Brasil');

-- Pessoas
INSERT INTO pessoa values
(DEFAULT, 'Matheus Finkler', '12345678910', 2),
(DEFAULT, 'Luciano Kohler da Silva', '99822811702', 3),
(DEFAULT, 'João Testão', '11322344622', 1);

-- Tipos de Animais
INSERT INTO tipoAnimal values
(DEFAULT, 'cachorro'),
(DEFAULT, 'gato'),
(DEFAULT, 'passaro'),
(DEFAULT, 'roedor');

-- Animais
INSERT INTO animal values
(DEFAULT, 'Trovão', 13, 'Gordinho', 1, 1),
(DEFAULT, 'Mimo', 12, 'Pelo caramelo', 2, 1),
(DEFAULT, 'Lelepa', 1, 'Come pepino', 4, 2);

/* EXEMPLOS DE QUERIES ÚTEIS */

-- Mostra os animais e seus donos (mostra o nome deles, e não o ID)
/*
SELECT 
	animal.nome AS nome_animal, 
	tipoAnimal.tipo AS tipo_animal, 
	pessoa.nome AS nome_dono 
FROM animal 
JOIN pessoa ON animal.idDono = pessoa.id
JOIN tipoAnimal ON animal.tipoAnimal = tipoAnimal.id;
*/

-- Mostra as pessoas cadastradas e seus endereços escritos
/*
SELECT 
	pessoa.nome,
	pessoa.cpf,
	endereco.rua 
FROM pessoa
JOIN endereco ON pessoa.id_endereco = endereco.id;
*/