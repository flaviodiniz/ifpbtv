CREATE TABLE usuario (
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(30) NOT NULL,
	senha VARCHAR(500) NOT NULL,
	matricula VARCHAR(50) NOT NULL,
	status VARCHAR(50) NOT NULL,
	perfil VARCHAR(50) NOT NULL
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

INSERT INTO usuario (id, nome, email, senha, matricula, status, perfil)
		values(1, 'Tiago', 'tiago01@gmail.com', '$2a$10$WtVaYP7t2EOf.OIdltYfF.Ay/fYKKnLfPD/nzyYe4oWZpQ4FTk8KS', 202125022021, 'Ativo', 'Administrador'); 

CREATE TABLE permissao (
	id BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO permissao (id, descricao) values (1, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (id, descricao) values (2, 'ROLE_REMOVER_USUARIO');
INSERT INTO permissao (id, descricao) values (3, 'ROLE_PESQUISAR_USUARIO');
INSERT INTO permissao (id, descricao) values (4, 'ROLE_CADASTRAR_TV');

-- admin
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);

