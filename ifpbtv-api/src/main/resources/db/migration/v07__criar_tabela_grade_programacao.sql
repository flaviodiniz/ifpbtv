CREATE TABLE grade_de_programacao (
	grade BIGINT(20) NOT NULL,
	programacao BIGINT(20) NOT NULL,
	PRIMARY KEY (grade, programacao),
	FOREIGN KEY (grade) REFERENCES grade_programacao(id),
	FOREIGN KEY (programacao) REFERENCES programacao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE grade_tv (
	grade BIGINT(20) NOT NULL,
	tv BIGINT(20) NOT NULL,
	PRIMARY KEY (grade, tv),
	FOREIGN KEY (grade) REFERENCES grade_programacao(id),
	FOREIGN KEY (tv) REFERENCES tv(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;