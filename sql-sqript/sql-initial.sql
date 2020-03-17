CREATE DATABASE tattoo4u;

CREATE TABLE roles
(
    id   SERIAL      NOT NULL,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO roles (name)
values ('ADMIN');
INSERT INTO roles (name)
values ('USER');
INSERT INTO roles (name)
values ('MASTER');