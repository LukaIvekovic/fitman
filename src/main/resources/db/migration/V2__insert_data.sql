-- Treneri
INSERT INTO trener (id, ime, prezime, specijalizacija, godine_iskustva)
VALUES (1, 'Petar', 'Petrović', 'Funkcionalni trening', 5);

-- Prostorije
INSERT INTO prostorija (oznaka, kapacitet, opis)
VALUES ('A1', 20, 'Aerobna dvorana'),
       ('B2', 10, 'Teretana');

-- Treninzi
INSERT INTO trening (id, naziv, opis, tezina, trajanje, max_broj_polaznika, datum)
VALUES (1, 'Aerobik', 'Intenzivni aerobni trening', 'Srednja', 60, 15, CURRENT_TIMESTAMP()),
       (2, 'Snaga', 'Trening snage s utezima', 'Napredna', 45, 8, CURRENT_TIMESTAMP());

-- Povezivanje treninga i trenera kroz veznu tablicu
INSERT INTO trening_trener (trening_id, trener_id)
VALUES (1, 1);