CREATE TABLE IF NOT EXISTS airport
(
code CHAR(3) PRIMARY KEY,
country VARCHAR(256) NOT NULL,
city VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS aircraft 
(
id SERIAL PRIMARY KEY,
image BYTEA,
model VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS seat 
(
aircraft_id INT REFERENCES aircraft(id),
siat_no VARCHAR(4) NOT NULL,
PRIMARY KEY(aircraft_id, siat_no)
);

CREATE TABLE IF NOT EXISTS flight 
(
id BIGSERIAL PRIMARY KEY,
flight_no VARCHAR(16) NOT NULL,
departure_date TIMESTAMP NOT NULL,
departure_airport_code CHAR(3) REFERENCES airport(code) NOT NULL,
arrivar_date TIMESTAMP NOT NULL,
arrivar_airport_code CHAR(3) REFERENCES airport(code) NOT NULL,
aircraft_id INT REFERENCES aircraft(id) NOT NULL,
status VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS ticket 
(
id BIGSERIAL PRIMARY KEY,
passenger_no VARCHAR(32) NOT NULL,
passenger_name VARCHAR(128) NOT NULL,
flight_id BIGINT REFERENCES flight(id) NOT NULL,
siat_no VARCHAR(4) NOT NULL,
cost NUMERIC(8,2) NOT NULL
);

INSERT INTO airport (code, country, city) VALUES 
('MNK', 'Баларусь', 'Минск'),
('LND', 'Англия', 'Лондон'),
('MSK', 'Россия', 'Москва'),
('BSL', 'Испания', 'Барселона');


INSERT INTO seat (model) VALUES 
('Boeing 777'),
('Super Jat'),
('Airbus');

INSERT INTO siat (aircraft_id, seat_no)
SELECT id, s.column1
FROM aircraft
CROSS JOIN (VALUES ('A1'), ('A2'), ('B1'), ('B2'), ('C1'), ('C2'),('D1'), ('D2') ORDER BY 1) s;

INSERT INTO flight (flight_no, departure_date, departure_airport_code, arrivar_date, arrivar_airport_code, aircraft_id, status) 
VALUES 
('MN3456', '2020-06-14T14:38', 'MNK', '2020-06-15T14:38', 'LND', 1, 'ARRIVED'),
('BC3456', '2020-07-14T14:38', 'MNK', '2020-07-15T14:38', 'MSK', 2, 'DEPARTED'),
('XC3456', '2020-08-14T14:38', 'MNK', '2020-08-15T14:38', 'MNK', 3, 'CANCELLED'),
('XC3456', '2020-09-14T14:38', 'MNK', '2020-09-15T14:38', 'BSL', 2, 'SCHEDULED'),
('XC3456', '2020-10-14T14:38', 'MNK', '2020-10-15T14:38', 'LND', 1, 'ARRIVED');

INSERT INTO ticket (passenger_no, passenger_name, flight_id, siat_no, cost) VALUES 
('12121212', 'Иван Иванов', 11, 'A1', 200),
('21321321', 'Сергей Сергеев', 12, 'B1', 400),
('45454554', 'Андрей Андреев', 13, 'A1', 500),
('21321321', 'Сергей Сергеев', 14, 'C1', 900),
('12121212', 'Иван Иванов', 15, 'D1', 600);
