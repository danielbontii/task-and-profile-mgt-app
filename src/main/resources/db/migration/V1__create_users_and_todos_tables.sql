CREATE SEQUENCE IF NOT EXISTS todos_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE todos
(
    id          BIGINT       NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    completed   BOOLEAN      NOT NULL,
    user_id     BIGINT       NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_todos PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGINT       NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE todos
    ADD CONSTRAINT uc_todos_title UNIQUE (title);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE todos
    ADD CONSTRAINT FK_TODOS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);