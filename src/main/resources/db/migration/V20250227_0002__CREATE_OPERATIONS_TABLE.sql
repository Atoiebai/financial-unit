

CREATE TABLE IF NOT EXISTS transactions (
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    id bigserial primary key ,
    user_id uuid references users(id),
    type varchar,
    amount BIGINT,
    status varchar

);

CREATE INDEX if not exists operation_id_idx ON transactions (user_id);
