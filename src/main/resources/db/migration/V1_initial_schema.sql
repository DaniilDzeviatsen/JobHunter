CREATE TABLE employee
(
    id            BIGSERIAL PRIMARY KEY,
    email         TEXT NOT NULL UNIQUE,
    employee_name TEXT NOT NULL,
    surname       TEXT NOT NULL,
    presentation  TEXT
);

CREATE TABLE employer
(
    id            BIGSERIAL PRIMARY KEY,
    email         TEXT NOT NULL UNIQUE,
    employer_name TEXT NOT NULL,
    website       TEXT NOT NULL UNIQUE

);

CREATE TABLE vacancy
(
    id           BIGSERIAL PRIMARY KEY,
    vacancy_name TEXT    NOT NULL,
    description  TEXT    NOT NULL,
    is_active    BOOLEAN NOT NULL,
    employee_id  BIGINT REFERENCES employee (id) ON DELETE CASCADE
);

CREATE TABLE response
(
    id BIGSERIAL PRIMARY KEY,
    response_text TEXT,
    employee_id BIGSERIAL NOT NULL REFERENCES employee(id) ON DELETE CASCADE,
    employer_id BIGSERIAL NOT NULL REFERENCES employer (id) ON DELETE CASCADE
)