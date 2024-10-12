--levels
INSERT INTO levels(level) VALUES ('Basico');
INSERT INTO levels(level) VALUES ('Intermedio');
INSERT INTO levels(level) VALUES ('Avanzado');

---suscriptions
INSERT INTO suscriptions (duration_months, name, price) VALUES (1, 'Mensual', 50);
INSERT INTO suscriptions (duration_months, name, price) VALUES (6, 'Semestral', 290);
INSERT INTO suscriptions (duration_months, name, price) VALUES (12, 'Anual', 500);

--Payment Type
INSERT INTO payment_type (name) VALUES ('Visa');
INSERT INTO payment_type (name) VALUES ('Mastercard');
INSERT INTO payment_type (name) VALUES ('PayPal');
INSERT INTO payment_type (name) VALUES ('Yape');
INSERT INTO payment_type (name) VALUES ('Plin');

--ROLES
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');


