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

-- update cashiers' names instead of duplicates
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

-- delete duplicates of the certain names
delete from cashiers
where first_name in ("mimi", "veronica") and (id > 5);

-- get all the names of the employees and order by last names
select first_name, last_name from drivers
union
select first_name, last_name from cashiers
order by last_name;

-- add new column to the table depots
alter table depots
add address varchar(100);

insert into depots (address)
values ("Moskovskaya str, 5"),
("Aranskaya str, 15");

-- update depot address
update depots
set address= "Fabritsiusa str, 24"
where id = 1;

-- delete duplicate
delete from depots where id = 3;

insert into `lines` (depot_id, name)
values (1, "Moskovskaya"),
(2, "Avtozavodskaya");

-- table containing lines of the underground und their depot address
select d.address as depot_address, l.name as line_name
from `lines` l
left join depots d on l.depot_id = d.id
order by l.name;

insert into trains (depot_id)
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

select * from trains;

-- combine a new train from existing carriages
update carriages
set train_id = 15
where id in (21, 23, 25, 27, 29);

-- delete old trains
delete from trains
where id in (13, 14);

-- delete carriages that are not in the new train sequence
delete from carriages
where train_id <> 15;

insert into carriages (train_id, seat_capacity, manufacturer, carriage_number)
values (16, 100, "Stadler", 7589),
(16, 100, "Stadler", 3469),
(16, 100, "Stadler", 7593),
(16, 100, "Stadler", 8432),
(16, 100, "Stadler", 1589);

select * from carriages;

-- count all the carriages from a particular manufacturer in a rolling stock
select c.manufacturer as manufacturer, count(c.train_id) as carriages_count
from carriages c
group by c.manufacturer;

-- count all the carriages from a particular manufacturer in each depot
select c.manufacturer as manufacturer, count(c.train_id) as carriages_count, t.depot_id as depot_id
from carriages c
inner join trains t on c.train_id = t.id
group by t.depot_id;

insert into train_drivers (train_id, driver_id)
values (15, 1), 
(15, 3);

-- relocate driver to another train
update train_drivers
set train_id = 16
where driver_id = 3;

insert into train_drivers (train_id, driver_id)
values (16, 5);

-- selecting drivers, their train's id and its depo name
select dep.address as depot_address, t.id as train_id, d.first_name as driver_name, d.last_name as driver_surname
from trains t
inner join train_drivers td on t.id = td.train_id
inner join drivers d on td.driver_id = d.id
inner join depots dep on t.depot_id = dep.id
order by d.last_name;

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

-- disable mode in order to get nonaggregated columns in a table
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

-- select stations with more than one platform and their line name
select l.name as line_name, s.name as station_name, count(p.station_id) as platform_count
from platforms p
left join line_stations ls on p.station_id = ls.station_id
left join `lines` l on ls.line_id = l.id
left join stations s on ls.station_id = s.id
group by p.station_id having platform_count > 1;

-- show number of stations for each line
select l.name as line_name, count(s.name) as number_of_stations
from `lines` l
left join line_stations ls on l.id = ls.line_id
left join stations s on ls.station_id = s.id
group by l.name;

-- closing down the first station of the line
delete from line_stations
where station_id=1;

delete from platforms
where station_id=1;

delete from stations
where id = 1;