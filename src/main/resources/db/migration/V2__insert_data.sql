-- Treneri
INSERT INTO trener (id, ime, prezime, specijalizacija, godine_iskustva)
VALUES (1, 'Petar', 'Petrović', 'Funkcionalni trening', 5);

-- Prostorije
INSERT INTO prostorija (oznaka, naziv, kapacitet, opis)
VALUES ('A1', 'Aerobna dvorana', 20, 'Sekundarna aerobna dvorana'),
       ('B2', 'Teretana', 10, 'Glavna teretana');

-- Treninzi
INSERT INTO trening (id, prostorija_id, naziv, opis, tezina, trajanje, max_broj_polaznika, datum)
VALUES (1, 1, 'Aerobik', 'Intenzivni aerobni trening', 'Srednja', 60, 15, CURRENT_TIMESTAMP()),
       (2, 2, 'Snaga', 'Trening snage s utezima', 'Napredna', 45, 8, CURRENT_TIMESTAMP());

-- Povezivanje treninga i trenera kroz veznu tablicu
INSERT INTO trening_trener (trening_id, trener_id)
VALUES (1, 1);