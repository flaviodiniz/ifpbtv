CREATE TABLE usuario (
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(30) NOT NULL,
	matricula VARCHAR(50) NOT NULL,
	perfil VARCHAR(50) NOT NULL,
	senha VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	



