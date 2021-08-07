CREATE TABLE programacao ( 
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(50) NOT NULL,
	tipoProgramacao VARCHAR(15) DEFAULT 'Normal',
	horarioInicio VARCHAR(5),
	horarioFim VARCHAR(5),
	diaSemana VARCHAR(10),
	dataCriacao DATE
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE tipoProgramacao (
	tp_id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	tp_descricao VARCHAR(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tipoProgramacao (tp_id, tp_descricao) VALUES (1, 'Normal');
INSERT INTO tipoProgramacao (tp_id, tp_descricao) VALUES (2, 'Imediata');


CREATE TABLE diaSemana (
	ds_id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	ds_descricao VARCHAR(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO diaSemana (ds_id, ds_descricao) VALUES (1, 'Domingo');
INSERT INTO diaSemana (ds_id, ds_descricao) VALUES (2, 'Segunda');
INSERT INTO diaSemana (ds_id, ds_descricao) VALUES (3, 'Terça');
INSERT INTO diaSemana (ds_id, ds_descricao) VALUES (4, 'Quarta');
INSERT INTO diaSemana (ds_id, ds_descricao) VALUES (5, 'Quinta');
INSERT INTO diaSemana (ds_id, ds_descricao) VALUES (6, 'Sexta');
INSERT INTO diaSemana (ds_id, ds_descricao) VALUES (7, 'Sabádo');