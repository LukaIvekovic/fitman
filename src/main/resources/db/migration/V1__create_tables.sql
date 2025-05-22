CREATE TABLE prostorija
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    oznaka    VARCHAR(20) NOT NULL,
    kapacitet INTEGER     NOT NULL DEFAULT 0,
    opis      VARCHAR(255),
    aktivna   BOOLEAN     NOT NULL DEFAULT TRUE
);

CREATE TABLE trener
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    ime             VARCHAR(50)  NOT NULL,
    prezime         VARCHAR(50)  NOT NULL,
    specijalizacija VARCHAR(100) NOT NULL,
    godine_iskustva INTEGER,
    certifikati     VARCHAR(100),
    biografija      VARCHAR(500)
);

CREATE TABLE trening
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    naziv              VARCHAR(100) NOT NULL,
    opis               VARCHAR(500),
    tezina             VARCHAR(20)  NOT NULL,
    trajanje           INTEGER      NOT NULL, -- u minutama
    max_broj_polaznika INTEGER,
    datum              TIMESTAMP    NOT NULL
);

CREATE TABLE trening_trener
(
    trening_id BIGINT NOT NULL,
    trener_id  BIGINT NOT NULL,
    PRIMARY KEY (trening_id, trener_id),
    FOREIGN KEY (trening_id) REFERENCES trening (id) ON DELETE CASCADE,
    FOREIGN KEY (trener_id) REFERENCES trener (id) ON DELETE CASCADE
);