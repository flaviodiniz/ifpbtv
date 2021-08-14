CREATE TABLE midia ( 
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(50) NOT NULL,
	chaveEspecifica VARCHAR(50) DEFAULT 'Informe',
	dataCriacao DATE,
	tipoMidia VARCHAR(10),
	disponibilidadeMidia VARCHAR(10),
	link VARCHAR(80),
	duracao INTEGER,
	usuario BIGINT(20),
	FOREIGN KEY (usuario) REFERENCES usuario(id)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
 CREATE TABLE midia_usuario (
	midia BIGINT(20) NOT NULL,
	usuario BIGINT(20) NOT NULL,
	PRIMARY KEY (midia, usuario),
	FOREIGN KEY (midia) REFERENCES midia(id),
	FOREIGN KEY (usuario) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tipoMidia (
	tpm_id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	tpm_descricao VARCHAR(50) NOT NULL,
	tpm_status CHAR(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tipoMidia (tpm_id, tpm_descricao, tpm_status) VALUES (1, 'Imagem', 'A');
INSERT INTO tipoMidia (tpm_id, tpm_descricao, tpm_status) VALUES (2, 'Streaming', 'A');
INSERT INTO tipoMidia (tpm_id, tpm_descricao, tpm_status) VALUES (3, 'Video', 'A');

CREATE TABLE disponibilidadeMidia (
	dpm_id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	dpm_descricao VARCHAR(50) NOT NULL,
	dpm_status CHAR(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO disponibilidadeMidia (dpm_id, dpm_descricao, dpm_status) VALUES (1, 'Privado', 'A');
INSERT INTO disponibilidadeMidia (dpm_id, dpm_descricao, dpm_status) VALUES (2, 'Público', 'A');

USE ifpbtv;
set global max_allowed_packet = 3 * 1024 * 1024 * 10; # 30M to
show VARIABLES like '% max_allowed_packet%';
