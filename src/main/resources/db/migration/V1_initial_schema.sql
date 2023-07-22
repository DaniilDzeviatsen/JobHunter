CREATE TABLE candidate
(
    id             BIGSERIAL PRIMARY KEY,
    candidate_email          TEXT NOT NULL UNIQUE,
    candidate_name TEXT NOT NULL,
    surname        TEXT NOT NULL,
    presentation   TEXT
);

CREATE TABLE employer
(
    id            BIGSERIAL PRIMARY KEY,
    employer_email         TEXT NOT NULL UNIQUE,
    employer_name TEXT NOT NULL,
    website       TEXT NOT NULL UNIQUE

);

CREATE TABLE vacancy
(
    id           BIGSERIAL PRIMARY KEY,
    vacancy_name TEXT    NOT NULL,
    description  TEXT    NOT NULL,
    is_active    BOOLEAN NOT NULL,
    employer_id BIGINT REFERENCES employer (id) ON DELETE CASCADE
);

CREATE TABLE response
(
    id            BIGSERIAL PRIMARY KEY,
    cover_letter TEXT,
    candidate_id  BIGSERIAL NOT NULL REFERENCES candidate (id) ON DELETE CASCADE,
    employer_id   BIGSERIAL NOT NULL REFERENCES employer (id) ON DELETE CASCADE
)