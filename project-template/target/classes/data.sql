INSERT INTO CATEGORY(id, name) VALUES (1, 'Eurogames');
INSERT INTO CATEGORY(id, name) VALUES (2, 'Ameritrash');
INSERT INTO CATEGORY(id, name) VALUES (3, 'Familiar');

INSERT INTO AUTHOR(id, name, nationality) VALUES (1, 'Alan R. Moon', 'US');
INSERT INTO AUTHOR(id, name, nationality) VALUES (2, 'Vital Lacerda', 'PT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (3, 'Simone Luciani', 'IT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (4, 'Perepau Llistosella', 'ES');
INSERT INTO AUTHOR(id, name, nationality) VALUES (5, 'Michael Kiesling', 'DE');
INSERT INTO AUTHOR(id, name, nationality) VALUES (6, 'Phil Walker-Harding', 'US');

INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (1, 'On Mars', '14', 1, 2);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (2, 'Aventureros al tren', '8', 3, 1);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (3, '1920: Wall Street', '12', 1, 4);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (4, 'Barrage', '14', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (5, 'Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (6, 'Azul', '8', 3, 5);

INSERT INTO CLIENT(id, name) VALUES (1,'Ana');
INSERT INTO CLIENT(id, name) VALUES (2,'Carlos');
INSERT INTO CLIENT(id, name) VALUES (3,'Pablo');
INSERT INTO CLIENT(id, name) VALUES (4,'Pedro');
INSERT INTO CLIENT(id, name) VALUES (5,'Juan');
INSERT INTO CLIENT(id, name) VALUES (6,'Jacques');
INSERT INTO CLIENT(id, name) VALUES (7,'Marta');
INSERT INTO CLIENT(id, name) VALUES (8,'Maria');

INSERT INTO PRESTAMO (id, game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (1, 1, 1, '2022-11-02','2022-11-09');
INSERT INTO PRESTAMO (id, game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (2, 5, 2, '2022-09-03','2022-09-10');
INSERT INTO PRESTAMO (id, game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (3, 6, 5, '2022-10-04','2022-10-11');
INSERT INTO PRESTAMO (id, game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (4, 4, 6, '2022-11-05','2022-11-12');
INSERT INTO PRESTAMO (id, game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (5, 3, 7, '2022-08-06','2022-08-11');
INSERT INTO PRESTAMO (id, game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (6, 2, 3, '2022-05-07','2022-05-12');
INSERT INTO PRESTAMO (id, game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (7, 4, 4, '2022-08-06','2022-08-11');
INSERT INTO PRESTAMO (id, game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (8, 6, 8, '2022-05-07','2022-05-12');

