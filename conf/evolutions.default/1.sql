
# dojo schema

# --- !Ups
CREATE TABLE IF NOT EXISTS dojo.usuario (
  id bigint NOT NULL AUTO_INCREMENT,
  nome VARCHAR(60) NOT NULL,
  email VARCHAR(60) NOT NULL,
  PRIMARY KEY (id)
  ) engine=InnoDB DEFAULT CHARACTER SET = utf8

# --- !Downs
drop table usuario