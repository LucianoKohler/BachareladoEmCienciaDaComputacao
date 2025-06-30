CREATE SEQUENCE seq_usuario;
CREATE SEQUENCE seq_seguidores;
CREATE SEQUENCE seq_post;
CREATE SEQUENCE seq_usuarioFavoritos;

/* Tabelas:
** Usuario
** Post
** usuarioFavoritos
*/

CREATE TABLE usuario(
	ID INT PRIMARY KEY DEFAULT nextval('seq_usuario'),
	username varchar(30),
	senha varchar(30),
	nomeCompleto varchar(30),
	biografia varchar (100),
	imagemPerfil BYTEA
);

CREATE TABLE seguidores(
	ID INT PRIMARY KEY DEFAULT nextval('seq_seguidores'),
	idFollowed INT,
	idFollower INT,

	FOREIGN KEY (idFollowed) REFERENCES usuario(ID),
	FOREIGN KEY (idFollower) REFERENCES usuario(ID)
);

CREATE TABLE post(
	ID INT PRIMARY KEY DEFAULT nextval('seq_post'),
	donoPost INT, -- FK
	legenda varchar(50),
	imagem BYTEA,
	
	FOREIGN KEY (donoPost) REFERENCES usuario(ID)
);

CREATE TABLE usuarioFavoritos (
	ID INT PRIMARY KEY DEFAULT nextval('seq_usuarioFavoritos'),
	idUsuario INT,
	idPost INT,

	FOREIGN KEY (idUsuario) REFERENCES usuario(ID),
	FOREIGN KEY (idPost) REFERENCES post(ID)
);