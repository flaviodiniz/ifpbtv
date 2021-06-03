CREATE TABLE cmdbancodedados (
	cmd_id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	cmd_descricao VARCHAR(50) NOT NULL,
	cmd_sql VARCHAR(500) NOT NULL,
	cmd_status CHAR(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cmdbancodedados (cmd_id, cmd_descricao, cmd_sql, cmd_status) VALUES (1, 'seleciona todos perfis', 'SELECT * FROM ifpbtv.perfil WHERE per_status = ''A''','A');
INSERT INTO cmdbancodedados (cmd_id, cmd_descricao, cmd_sql, cmd_status) VALUES (2, 'seleciona usuarios', 
  'SELECT *
FROM usuario usu
INNER JOIN perfil per ON per.per_id = usu.perfil 
WHERE usu.nome = ? OR -1 = ?
  AND (usu.matricula = ? OR -1 = ?)
  AND (per.per_descricao = ? OR -1 = ?)','A');

