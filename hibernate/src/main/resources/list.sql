--Database: hibernate_dm_db

CREATE TABLE IF NOT EXISTS users
(
username VARCHAR(128) PRIMARY KEY,
firstname VARCHAR(128),
lastname VARCHAR(128),
birth_day DATE,
age INT
);