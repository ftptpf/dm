--Database: hibernate_dm_db

CREATE TABLE IF NOT EXISTS users
(
firstname VARCHAR(128),
lastname VARCHAR(128),
birth_day DATE,
username VARCHAR(128) UNIQUE,
role VARCHAR(32),
info JSONB,
PRIMARY KEY(firstname, lastname, birth_day)
);

DROP TABLE users;

/*CREATE TABLE IF NOT EXISTS users
(
id BIGSERIAL PRIMARY KEY,
username VARCHAR(128) UNIQUE,
firstname VARCHAR(128),
lastname VARCHAR(128),
birth_day DATE,
role VARCHAR(32),
info JSONB
);*/
