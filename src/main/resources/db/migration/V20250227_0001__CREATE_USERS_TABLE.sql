
CREATE TABLE IF NOT EXISTS users
(
    id                 uuid primary key,
    username           VARCHAR UNIQUE,
    password           VARCHAR,
    state              varchar,
    role               varchar,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP

);
