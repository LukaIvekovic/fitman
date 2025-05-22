INSERT INTO trener (ime, prezime, specijalizacija, godine_iskustva)
VALUES ('Petar', 'Petrović', 'Funkcionalni trening', 5),
       ('Ana', 'Horvat', 'Pilates', 8),
       ('Marko', 'Kovač', 'HIIT', 4),
       ('Ivana', 'Novak', 'Yoga', 10),
       ('Damir', 'Perić', 'Bodybuilding', 7),
       ('Maja', 'Jurić', 'CrossFit', 6);

INSERT INTO prostorija (oznaka, naziv, kapacitet, opis)
VALUES ('A1', 'Aerobna dvorana', 20, 'Sekundarna aerobna dvorana'),
       ('B2', 'Teretana', 10, 'Glavna teretana'),
       ('C3', 'Yoga studio', 15, 'Studio s drvenim podom i ogledalima'),
       ('D4', 'Funkcionalna dvorana', 12, 'Dvorana za funkcionalne treninge'),
       ('E5', 'Kardio zona', 25, 'Prostor s kardio opremom'),
       ('F6', 'Mala dvorana', 8, 'Privatni prostor za manje grupe');

INSERT INTO trening (prostorija_id, naziv, opis, tezina, trajanje, max_broj_polaznika, datum)
VALUES (1, 'Aerobik', 'Intenzivni aerobni trening', 'Srednja', 60, 15, CURRENT_TIMESTAMP()),
       (2, 'Snaga', 'Trening snage s utezima', 'Napredna', 45, 8, CURRENT_TIMESTAMP()),
       (3, 'Jutarnja Yoga', 'Opuštajuća jutarnja yoga', 'Početna', 50, 12, DATEADD('DAY', 1, CURRENT_TIMESTAMP())),
       (4, 'HIIT Izazov', 'Visoko intenzivni intervalni trening', 'Napredna', 40, 10, DATEADD('DAY', 2, CURRENT_TIMESTAMP())),
       (5, 'Kardio Miks', 'Kombinacija različitih kardio vježbi', 'Srednja', 55, 20, DATEADD('DAY', 3, CURRENT_TIMESTAMP())),
       (6, 'Pilates za početnike', 'Osnove pilatesa za sve uzraste', 'Početna', 45, 8, DATEADD('DAY', 4, CURRENT_TIMESTAMP())),
       (1, 'Body Pump', 'Trening s utezima uz glazbu', 'Srednja', 50, 15, DATEADD('DAY', 5, CURRENT_TIMESTAMP())),
       (2, 'Funkcionalni trening', 'Vježbe za cijelo tijelo', 'Srednja', 60, 10, DATEADD('DAY', 6, CURRENT_TIMESTAMP()));

INSERT INTO trening_trener (trening_id, trener_id)
VALUES (1, 1),
       (2, 5),
       (3, 4),
       (4, 3),
       (5, 1),
       (5, 3),
       (6, 2),
       (7, 5),
       (8, 1),
       (8, 6);