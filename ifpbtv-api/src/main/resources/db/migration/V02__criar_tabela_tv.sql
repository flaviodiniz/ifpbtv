CREATE TABLE grade_programacao ( 
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(50) NOT NULL,
	exibindo BOOLEAN,
	ativa BOOLEAN
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tv (
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	local VARCHAR(50) NOT NULL,
	modelo VARCHAR(15) NOT NULL,
	marca VARCHAR(15) NOT NULL,
	chave VARCHAR(20),
	disponivel VARCHAR(10) NOT NULL,
	online BOOLEAN NOT NULL,
	gradeProgramacao BIGINT(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE marca (
	mar_id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	mar_descricao VARCHAR(50) NOT NULL,
	mar_status CHAR(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO marca (mar_id, mar_descricao, mar_status) VALUES (1, 'LG', 'A');
INSERT INTO marca (mar_id, mar_descricao, mar_status) VALUES (2, 'Philips', 'A');
INSERT INTO marca (mar_id, mar_descricao, mar_status) VALUES (3, 'Sansumg', 'A');
INSERT INTO marca (mar_id, mar_descricao, mar_status) VALUES (4, 'Sony', 'A');
INSERT INTO marca (mar_id, mar_descricao, mar_status) VALUES (5, 'TLC', 'A');


