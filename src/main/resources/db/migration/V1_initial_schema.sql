CREATE TABLE candidate
(
    id        BIGSERIAL PRIMARY KEY,
    email     TEXT NOT NULL UNIQUE,
    name      TEXT NOT NULL,
    last_name TEXT NOT NULL
);
CREATE UNIQUE INDEX ON candidate (lower(email));

CREATE TABLE cv
(
    candidate_id BIGINT PRIMARY KEY REFERENCES candidate,
    content      TEXT NOT NULL
);

CREATE TABLE employer
(
    id       BIGSERIAL PRIMARY KEY,
    email    TEXT NOT NULL UNIQUE,
    name     TEXT NOT NULL,
    site_url TEXT

);
CREATE UNIQUE INDEX ON employer (lower(email));

CREATE TABLE opportunity
(
    id          BIGSERIAL PRIMARY KEY,
    title       TEXT      NOT NULL,
    description TEXT      NOT NULL,
    active      BOOLEAN   NOT NULL,
    employer_id BIGINT    NOT NULL REFERENCES employer (id),
    created_at  TIMESTAMP NOT NULL
);

CREATE TABLE opportunity_apply
(
    id           BIGSERIAL PRIMARY KEY,
    opportunity_id BIGINT NOT NULL REFERENCES opportunity,
    covering_letter TEXT NOT NULL ,
    candidate_id BIGSERIAL NOT NULL REFERENCES candidate (id),
    created_at TIMESTAMP NOT NULL,
    UNIQUE (opportunity_id, candidate_id)
);