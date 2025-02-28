CREATE TABLE IF NOT EXISTS user_balances
(
    id                 bigserial primary key,
    user_id            uuid references users(id),
    amount             bigint,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP

);
