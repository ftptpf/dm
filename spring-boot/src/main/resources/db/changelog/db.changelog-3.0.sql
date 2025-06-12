--liquibase formatted sql

--changeset ftptpf:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);

--changeset ftptpf:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);