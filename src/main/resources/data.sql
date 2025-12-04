INSERT INTO roles(id, name) VALUES (1, 'ADMIN');
INSERT INTO roles(id, name) VALUES (2, 'USER');

INSERT INTO users(username, password) VALUES
("admin", "$2a$12$KnrLQ7LNYCECupaWkpDG1.UbSBb/yz6YvDywzcNMvoIq8Hq6.o.9y"), --admin123
("user", "$2a$12$41eUZV/uUIbz7mRIv8O6BuloMNl/BTG1uYY9Pl3/45Yl.Z7n.6Kpy"), --user123
("second-user", "$2a$12$q/jjpYRabZJLQ5mBrTbl.e5ngmYy4gslGH1479d2re.8qllYSm8rC"); -- second-pass

INSERT INTO user_roles(user_id, role_id) VALUES
(1, 1),
(2, 2),
(3, 2);

INSERT INTO developers(name) VALUES
("Remedy Entertainment"),
("Hazelight Studios"),
("Team Cherry"),
("Shedworks"),
("Bytten Studio"),
("Dogubomb"),
("Consumer Softproducts"),
("FromSoftware");

INSERT INTO publishers(name) VALUES
("Rockstar Games"),
("Microsoft Game Studios"),
("505 Games"),
("Epic Games Publishing"),
("Electronic Arts"),
("Team Cherry"),
("Raw Fury"),
("Consumer Softproducts"),
("Bandai Namco Entertainment");

INSERT INTO games(title, release_date, developer_id, publisher_id) VALUES
("Max Payne", "2001-07-24", 1, 1),
("Alan Wake", "2010-05-14", 1, 2),
("Control", "2019-08-27", 1, 3),
("Alan Wake 2", "2023-10-27", 1, 4),
("It Takes Two", "2021-03-26", 2, 5),
("Split Fiction", "2025-03-06", 2, 5),
("Hollow Knight", "2017-02-24", 3, 6),
("Hollow Knight: Silksong", "2025-09-04", 3, 6),
("Sable", "2021-09-23", 4, 7),
("Cassette Beasts", "2023-04-26", 5, 7),
("Blue Prince", "2025-04-10", 6, 7),
("Cruelty Squad", "2021-06-15", 7, 8);

INSERT INTO tags(name, age_restriction) VALUES
("Action", 0), --1
("Adventure", 0), --2
("Horror", 12), --3
("Puzzle", 0), --4
("Role Playing Game", 0), --5
("Shooter", 0),
("Platformer", 0),
("Metroidvania", 0),
("Immersive Sim", 0),
("Creature Collector", 0), --10
("Rogue-like", 0),
("Rogue-lite", 0),
("Cartoon Violence", 8),
("Violence", 12),
("Graphic Violence", 18), --15
("Noir", 0),
("Mystery", 0),
("Detective", 0),
("Psychological", 0),
("Gesamtkunstwerk", 0), --20
("Singleplayer", 0),
("Multiplayer", 0),
("Co-op", 0),
("PvP", 15);


INSERT INTO game_tag(game_id, tag_id) VALUES
(1, 1), (1, 6), (1, 14), (1, 16), (1, 18), (1, 21), -- Max Payne
(2, 1), (2, 3), (2, 6), (2, 14), (2, 17), (2, 19), (2, 21),-- Alan Wake
(3, 1), (3, 3), (3, 6), (3, 8), (3, 14), (3, 17), (3, 19), (3, 21),-- Control
(4, 1), (4, 3), (4, 6), (4, 15), (4, 17), (4, 18), (4, 19), (4, 20), (4, 21), -- Alan Wake 2
(5, 1), (5, 2), (5, 4), (5, 7), (5, 13), (5, 22), (5, 23), -- It Takes Two
(6, 1), (6, 2), (6, 4), (6, 7), (6, 13), (6, 22), (6, 23); -- Split Fiction