CREATE TABLE midia ( 
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(50) NOT NULL,
	chaveEspecifica VARCHAR(50) NOT NULL,
	dataCriacao DATE,
	tipoMidia VARCHAR(10) NOT NULL,
	disponibilidadeMidia VARCHAR(10) NOT NULL,
	link VARCHAR(80),
	duracao INTEGER,
	usuario BIGINT(50) NOT NULL,
	FOREIGN KEY (usuario) REFERENCES usuario(id)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;