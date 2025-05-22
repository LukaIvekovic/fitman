INSERT INTO trener (id, ime, prezime, specijalizacija, godine_iskustva)
VALUES (1, 'Petar', 'Petrović', 'Funkcionalni trening', 5),
       (2, 'Ana', 'Horvat', 'Pilates', 8),
       (3, 'Marko', 'Kovač', 'HIIT', 4),
       (4, 'Ivana', 'Novak', 'Yoga', 10),
       (5, 'Damir', 'Perić', 'Bodybuilding', 7),
       (6, 'Maja', 'Jurić', 'CrossFit', 6);

INSERT INTO prostorija (oznaka, naziv, kapacitet, opis)
VALUES ('A1', 'Aerobna dvorana', 20, 'Sekundarna aerobna dvorana'),
       ('B2', 'Teretana', 10, 'Glavna teretana'),
       ('C3', 'Yoga studio', 15, 'Studio s drvenim podom i ogledalima'),
       ('D4', 'Funkcionalna dvorana', 12, 'Dvorana za funkcionalne treninge'),
       ('E5', 'Kardio zona', 25, 'Prostor s kardio opremom'),
       ('F6', 'Mala dvorana', 8, 'Privatni prostor za manje grupe');

INSERT INTO trening (id, prostorija_id, naziv, opis, tezina, trajanje, max_broj_polaznika, datum)
VALUES (1, 1, 'Aerobik', 'Intenzivni aerobni trening', 'Srednja', 60, 15, CURRENT_TIMESTAMP()),
       (2, 2, 'Snaga', 'Trening snage s utezima', 'Napredna', 45, 8, CURRENT_TIMESTAMP()),
       (3, 3, 'Jutarnja Yoga', 'Opuštajuća jutarnja yoga', 'Početna', 50, 12, DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 1 DAY)),
       (4, 4, 'HIIT Izazov', 'Visoko intenzivni intervalni trening', 'Napredna', 40, 10, DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 2 DAY)),
       (5, 5, 'Kardio Miks', 'Kombinacija različitih kardio vježbi', 'Srednja', 55, 20, DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 3 DAY)),
       (6, 6, 'Pilates za početnike', 'Osnove pilatesa za sve uzraste', 'Početna', 45, 8, DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 4 DAY)),
       (7, 1, 'Body Pump', 'Trening s utezima uz glazbu', 'Srednja', 50, 15, DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 5 DAY)),
       (8, 2, 'Funkcionalni trening', 'Vježbe za cijelo tijelo', 'Srednja', 60, 10, DATE_ADD(CURRENT_TIMESTAMP(), INTERVAL 6 DAY));

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