use undergrounds;

insert into depots (address)
values ("Moskovskaya str, 5"),
("Aranskaya str, 15");

insert into `lines` (depot_id, name)
values (1, "Moskovskaya"),
(2, "Avtozavodskaya");

insert into trains (depot_id, `number`)
values (1, 3245),
(1, 2567),
(1, 7459),
(2, 4753);

insert into carriages(train_id, seat_capacity, manufacturer, carriage_number)
values(1, 100, "Stadler", 4968),
(1, 100, "Stadler", 4023),
(1, 100, "Stadler", 8593),
(1, 100, "Stadler", 2893),
(1, 100, "Stadler", 7591),
(2, 100, "Stadler", 5279),
(2, 100, "Stadler", 8563),
(2, 100, "Stadler", 9731),
(2, 100, "Stadler", 1469),
(2, 100, "Stadler", 7814);

insert into stations (name)
values ("Malinovka"),
("Petrovshina"),
("Mikhalovo"),
("Hrushauka"),
("Institut kultury"),
("Lenina Square"),
("Oktyabrskaya"),
("Victory Square"),
("Kamennaya Horka"),
("Praletarskaya"),
("Sportivnaya");

insert into line_stations (line_id, station_id)
values (1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(2, 9),
(2, 10),
(2, 11);

insert into platforms (station_id)
values (1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11);