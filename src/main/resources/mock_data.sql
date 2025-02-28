INSERT INTO public.users (id, username, password, state, role, created_at, updated_at) VALUES ('b4afaa20-dcef-405a-b63d-b93a62d18667', 'islam', '$2a$10$XqBDzckrHFY0ZcNO6DW.K.HsNj7ac.GvEim/wXVgJky/itqt.3Sty', 'ACTIVE', 'BASE', '2025-02-27 23:54:24.491996', '2025-02-27 23:54:24.492026');

INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 11:07:37.348700', '2025-02-28 11:07:37.348727', 1, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', 10, 'SUCCESS');
INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 10:57:41.979038', '2025-02-28 10:57:41.979059', 2, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', -3000, 'SUCCESS');
INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 00:26:45.568545', '2025-02-28 00:26:45.568555', 3, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', 3000, 'FAILED');
INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 00:26:43.643720', '2025-02-28 00:26:43.643755', 4, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', 3000, 'SUCCESS');
INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 00:26:38.411359', '2025-02-28 00:26:38.411371', 5, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', 1000, 'SUCCESS');
INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 00:26:37.727238', '2025-02-28 00:26:37.727256', 6, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', 1000, 'SUCCESS');
INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 00:26:37.032284', '2025-02-28 00:26:37.032293', 7, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', 1000, 'SUCCESS');
INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 00:26:36.372758', '2025-02-28 00:26:36.372772', 8, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', 1000, 'SUCCESS');
INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 00:26:30.465517', '2025-02-28 00:26:30.465526', 9, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', 1000, 'SUCCESS');
INSERT INTO public.transactions (created_at, updated_at, id, user_id, type, amount, status) VALUES ('2025-02-28 00:26:20.898532', '2025-02-28 00:26:20.898559', 10, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 'DEBIT', 1000, 'SUCCESS');


INSERT INTO public.user_balances (id, user_id, amount, created_at, updated_at) VALUES (1, 'b4afaa20-dcef-405a-b63d-b93a62d18667', 4040, null, '2025-02-28 11:07:37.322433');
