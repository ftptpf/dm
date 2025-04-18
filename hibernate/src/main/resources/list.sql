--Database: hibernate_dm_db

CREATE TABLE IF NOT EXISTS users
(
id BIGSERIAL PRIMARY KEY,
username VARCHAR(128) UNIQUE,
firstname VARCHAR(128),
lastname VARCHAR(128),
birth_day DATE,
role VARCHAR(32),
info JSONB,
company_id INT REFERENCES company(id)
);

CREATE TABLE IF NOT EXISTS company
(
id SERIAL PRIMARY KEY,
name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS profile
(
id BIGSERIAL PRIMARY KEY,
user_id BIGINT NOT NULL UNIQUE REFERENCES users(id),
street VARCHAR(128),
language CHAR(2)
);

CREATE TABLE IF NOT EXISTS chat
(
id BIGSERIAL PRIMARY KEY,
name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users_chat
(
id BIGSERIAL PRIMARY KEY,
user_id BIGINT REFERENCES users(id),
chat_id BIGINT REFERENCES chat(id),
created_at TIMESTAMP NOT NULL,
created_by VARCHAR(128) NOT NULL,
UNIQUE (user_id, chat_id)
);

CREATE TABLE IF NOT EXISTS company_locale
(
company_id INT NOT NULL REFERENCES company(id),
lang CHAR(2) NOT NULL,
description VARCHAR(512) NOT NULL,
PRIMARY KEY (company_id, lang)
);

DROP TABLE users;
DROP TABLE company;
DROP TABLE profile;
DROP TABLE chat;
DROP TABLE users_chat;
