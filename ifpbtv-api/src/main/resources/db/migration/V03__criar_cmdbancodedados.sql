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
WHERE usu.nome like ? OR -1 = ?
  AND (usu.matricula like ? OR -1 = ?)
  AND (usu.perfil like ? OR -1 = ?)','A');
  
INSERT INTO cmdbancodedados (cmd_id, cmd_descricao, cmd_sql, cmd_status) VALUES (3, 'seleciona todas marcas', 'SELECT * FROM ifpbtv.marca WHERE mar_status = ''A''','A');
INSERT INTO cmdbancodedados (cmd_id, cmd_descricao, cmd_sql, cmd_status) VALUES (4, 'seleciona tvs', 
  'SELECT *
 FROM tv tv
 WHERE tv.local like ? OR -1 = ?','A');
  
INSERT INTO cmdbancodedados (cmd_id, cmd_descricao, cmd_sql, cmd_status) VALUES (5, 'seleciona tipos de midia', 'SELECT * FROM ifpbtv.tipoMidia WHERE tpm_status = ''A''','A'); 
  
INSERT INTO cmdbancodedados (cmd_id, cmd_descricao, cmd_sql, cmd_status) VALUES (6, 'seleciona disponiblidade midia', 'SELECT * FROM ifpbtv.disponibilidadeMidia WHERE dpm_status = ''A''','A');

INSERT INTO cmdbancodedados (cmd_id, cmd_descricao, cmd_sql, cmd_status) VALUES (7, 'seleciona chaves', 'SELECT * FROM ifpbtv.chave','A');
  