use undergrounds;

insert into drivers (first_name, last_name, dob)
values ("anton", "vasilievich", "1970-01-01"),
("mikhail", "antonov", "1990-02-05"),
("zinaida", "nishuk", "1985-04-06");

-- update duplicates of data to new drivers
update drivers
set first_name="Filipp", last_name="Nits", dob = "1981-3-7"
where id = 4;
update drivers
set first_name="Nikita", last_name="Klimovich", dob = "1963-9-30"
where id = 5;
update drivers
set first_name="Mikhail", last_name="Ryba", dob = "1976-12-7"
where id = 6;

-- delete duplicates
delete from drivers where id > 6;

insert into drivers(first_name, last_name, dob)
values("Oleksandr", "Radyuk", "1977-3-4");

-- look for drivers whose last name starts with N
select * from drivers
where last_name like "N%";

-- select quantity of drivers with a particular date of birth
select count(*) from drivers
where dob between "1985-01-01" and "1991-01-01";

insert into cashiers (first_name, last_name, dob)
values ("mimi", "antonovna", "1990-12-4"),
("veronica", "lublievich", "1953-1-2");

update cashiers
set first_name="Alina", last_name="Baduk", dob="1995-8-23"
where id = 3;
update cashiers
set first_name="Mihalina", last_name="Runina", dob="1975-6-3"
where id = 4;
update cashiers
set first_name="Isa", last_name="Nistko", dob="1960-8-17"
where id = 5;
update cashiers
set first_name="Uliana", last_name="Nosik", dob="1992-4-23"
where id = 6;

-- select cashiers with the selected names
select first_name from cashiers
where first_name in ("Isa", "Alina", "Miriam");

-- get all the names of the employees and sort by last names
select first_name, last_name from drivers
union
select first_name, last_name from cashiers
order by last_name;

-- add new column to the table depots
alter table depots
add address varchar(100);

insert into depots (address)
values ("Moskovskaya str, 5");

insert into depots (address)
values ("Aranskaya str, 15");

insert into `lines` (depot_id, name)
values (1, "Moskovskaya"),
(2, "Avtozavodskaya");

insert into trains(depot_id)
values (1),
(1),
(1),
(2);

insert into carriages(train_id, seat_capacity, manufacturer, carriage_number)
values(13, 100, "Stadler", 4968),
(13, 100, "Stadler", 4023),
(13, 100, "Stadler", 8593),
(13, 100, "Stadler", 2893),
(13, 100, "Stadler", 7591),
(14, 100, "Stadler", 5279),
(14, 100, "Stadler", 8563),
(14, 100, "Stadler", 9731),
(14, 100, "Stadler", 1469),
(14, 100, "Stadler", 7814);

select * from carriages;

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

insert into stations (name)
values("Pershamaiskaia");

-- find stations by the pattern and order station names by their id
select name as station_name from stations
where name like "%ov%"
order by id asc;

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
(2, 11),
(2, 12);

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
(11),
(12),
(12);

-- joined table with lines and stations names
select l.name as line_name, s.name as station_name
from line_stations ls
left join `lines` l on ls.line_id = l.id
left join stations s on ls.station_id = s.id;

-- joined table with stations with more than one platform
select s.name as station_name, count(p.station_id) as platform_count
from platforms p
left join stations s on p.station_id = s.id
group by station_id having platform_count > 1;