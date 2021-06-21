CREATE TABLE usuario (
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(30) NOT NULL,
	matricula VARCHAR(50) NOT NULL,
	perfil VARCHAR(50) NOT NULL,
	senha VARCHAR(50) NOT NULL,
	status VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE usuario ADD UNIQUE (email);

ALTER TABLE usuario ADD UNIQUE (matricula);

CREATE TABLE perfil (
	per_id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	per_descricao VARCHAR(50) NOT NULL,
	per_status CHAR(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO perfil (per_id, per_descricao, per_status) VALUES (1, 'Administrador', 'A');
INSERT INTO perfil (per_id, per_descricao, per_status) VALUES (2, 'Comum', 'A');

CREATE TABLE Status (
	sts_id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	sts_descricao VARCHAR(50) NOT NULL,
	sts_status CHAR(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Status (sts_id, sts_descricao, sts_status) VALUES (1, 'Ativo', 'A');
INSERT INTO Status (sts_id, sts_descricao, sts_status) VALUES (2, 'Inativo', 'A');
