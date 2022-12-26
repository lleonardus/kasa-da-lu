-- French Toasts

INSERT INTO french_toasts(flavor, price) VALUES ('Tradicional', 4);
INSERT INTO french_toasts(flavor, price) VALUES ('Chocolate', 5);
INSERT INTO french_toasts(flavor, price) VALUES ('Doce de Leite', 5);
INSERT INTO french_toasts(flavor, price) VALUES ('Nutella', 7);

-- Clients

INSERT INTO clients(name, lastname, phone) VALUES ('Leonardo', 'Sousa', '4002-8922');
INSERT INTO clients(name, lastname, phone) VALUES ('Carlos', 'Ferreira', '8922-4002');
INSERT INTO clients(name, lastname, phone) VALUES ('Jorge', 'Santos', '4008-7922');
INSERT INTO clients(name, lastname, phone) VALUES ('Eduarda', 'Alves', '4052-8922');
INSERT INTO clients(name, lastname, phone) VALUES ('Bianca', 'Fernandes', '4002-8522');

-- Orders

INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (1, 1, 3, '2022-12-15');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (1, 3, 1, '2022-11-27');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (1, 2, 2, '2022-10-3');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (2, 4, 4, '2022-11-8');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (2, 1, 3, '2022-12-15');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (3, 3, 1, '2022-11-27');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (3, 2, 2, '2022-10-3');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (4, 4, 4, '2022-11-8');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (4, 1, 3, '2022-12-15');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (5, 3, 1, '2022-11-27');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (5, 2, 2, '2022-10-3');
INSERT INTO orders(client_id, french_toast_id, quantity, ordered_at) VALUES (5, 4, 4, '2022-11-8');