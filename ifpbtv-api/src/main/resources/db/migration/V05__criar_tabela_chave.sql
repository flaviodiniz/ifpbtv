CREATE TABLE chave(
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	chave VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE midia_chave (
	midia BIGINT(20) NOT NULL,
	chave BIGINT(20) NOT NULL,
	PRIMARY KEY (midia, chave),
	FOREIGN KEY (midia) REFERENCES midia(id),
	FOREIGN KEY (chave) REFERENCES chave(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO chave (id, chave) VALUES (1, 'Abertura');
INSERT INTO chave (id, chave) VALUES (2, 'Detalhamento');
INSERT INTO chave (id, chave) VALUES (3, 'Informe');
INSERT INTO chave (id, chave) VALUES (4, 'Intervalo');
INSERT INTO chave (id, chave) VALUES (5, 'Encerramento');

