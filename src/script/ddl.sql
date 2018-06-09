CREATE TABLE gatunek (
  id      INTEGER NOT NULL,
  nazwa   VARCHAR2(100) NOT NULL,
  opis    CLOB
);

ALTER TABLE gatunek ADD CONSTRAINT gatunek_pk PRIMARY KEY ( id );

CREATE TABLE gatunek_srodek_ochrony (
  gatunek_id          INTEGER NOT NULL,
  srodek_ochrony_id   INTEGER NOT NULL
);

ALTER TABLE gatunek_srodek_ochrony ADD CONSTRAINT relation_11_pk PRIMARY KEY ( gatunek_id,
                                                                               srodek_ochrony_id );

CREATE TABLE nieobecnosc (
  id            INTEGER NOT NULL,
  data_od       DATE NOT NULL,
  data_do       DATE,
  ogrodnik_id   INTEGER NOT NULL,
  typ           CHAR(1) NOT NULL
);

ALTER TABLE nieobecnosc ADD CONSTRAINT nieobecnosc_pk PRIMARY KEY ( id );

CREATE TABLE ogrodnik (
  id                  INTEGER NOT NULL,
  pesel               VARCHAR2(11) NOT NULL,
  imie                VARCHAR2(50) NOT NULL,
  nazwisko            VARCHAR2(60) NOT NULL,
  data_zatrudnienia   DATE NOT NULL,
  zastepca_id         INTEGER,
  przelozony_id       INTEGER,
  telefon             VARCHAR2(15)
);

CREATE INDEX ogrodnik__idx ON
  ogrodnik (
    zastepca_id
    ASC );

CREATE INDEX ogrodnik__idxv1 ON
  ogrodnik (
    przelozony_id
    ASC );

ALTER TABLE ogrodnik ADD CONSTRAINT ogrodnik_pk PRIMARY KEY ( id );

CREATE TABLE poletko (
  id             INTEGER NOT NULL,
  nazwa          VARCHAR2(60) NOT NULL,
  powierzchnia   NUMBER,
  sekcja_id      INTEGER NOT NULL
);

ALTER TABLE poletko ADD CONSTRAINT poletko_pk PRIMARY KEY ( id );

CREATE TABLE poletko_ogrodnik (
  poletko_id    INTEGER NOT NULL,
  ogrodnik_id   INTEGER NOT NULL
);

ALTER TABLE poletko_ogrodnik ADD CONSTRAINT "Poletko/Ogrodnik_PK" PRIMARY KEY ( poletko_id,
                                                                                ogrodnik_id );

CREATE TABLE punkt_graniczny (
  id                   INTEGER NOT NULL,
  wspolrzedna_x        NUMBER(9,6) NOT NULL,
  wspolrzedna_y        NUMBER(8,6) NOT NULL,
  sekcja_id            INTEGER NOT NULL,
  punkt_graniczny_id   INTEGER
);

CREATE UNIQUE INDEX punkt_graniczny__idxv1 ON
  punkt_graniczny (
    punkt_graniczny_id
    ASC );

ALTER TABLE punkt_graniczny ADD CONSTRAINT punkt_graniczny_pk PRIMARY KEY ( id );

CREATE TABLE roslina (
  id           INTEGER NOT NULL,
  uwagi        VARCHAR2(100),
  gatunek_id   INTEGER NOT NULL,
  poletko_id   INTEGER
);

ALTER TABLE roslina ADD CONSTRAINT roslina_pk PRIMARY KEY ( id );

CREATE TABLE sekcja (
  id      INTEGER NOT NULL,
  nazwa   VARCHAR2(60) NOT NULL
);

ALTER TABLE sekcja ADD CONSTRAINT sekcja_pk PRIMARY KEY ( id );

CREATE TABLE srodek_ochrony (
  id      INTEGER NOT NULL,
  nazwa   VARCHAR2(100) NOT NULL,
  opis    CLOB
);

ALTER TABLE srodek_ochrony ADD CONSTRAINT srodek_ochrony_pk PRIMARY KEY ( id );

CREATE TABLE typ_urlopu (
  id      INTEGER NOT NULL,
  nazwa   VARCHAR2(40) NOT NULL
);

ALTER TABLE typ_urlopu ADD CONSTRAINT typ_urlopu_pk PRIMARY KEY ( id );

CREATE TABLE urlop (
  platny          CHAR(1),
  typ_urlopu_id   INTEGER NOT NULL,
  id              INTEGER NOT NULL
);

ALTER TABLE urlop ADD CONSTRAINT urlop_pk PRIMARY KEY ( id );

CREATE TABLE zuzycie_srodka (
  srodek_ochrony_id   INTEGER NOT NULL,
  data_uzycia                DATE,
  id                  INTEGER NOT NULL,
  objetosc            INTEGER,
  waga                INTEGER,
  poletko_id          INTEGER NOT NULL
);

ALTER TABLE zuzycie_srodka ADD CONSTRAINT zuzycie_srodka_pk PRIMARY KEY ( id );

CREATE TABLE zwolnienie (
  powod   CLOB,
  id      INTEGER NOT NULL
);

ALTER TABLE zwolnienie ADD CONSTRAINT zwolnienie_pk PRIMARY KEY ( id );

ALTER TABLE nieobecnosc
  ADD CONSTRAINT nieobecnosc_ogrodnik_fk FOREIGN KEY ( ogrodnik_id )
REFERENCES ogrodnik ( id )
ON DELETE CASCADE;

ALTER TABLE ogrodnik
  ADD CONSTRAINT ogrodnik_ogrodnik_fk FOREIGN KEY ( przelozony_id )
REFERENCES ogrodnik ( id );

ALTER TABLE ogrodnik
  ADD CONSTRAINT ogrodnik_ogrodnik_fkv1 FOREIGN KEY ( zastepca_id )
REFERENCES ogrodnik ( id );

ALTER TABLE poletko
  ADD CONSTRAINT poletko_sekcja_fk FOREIGN KEY ( sekcja_id )
REFERENCES sekcja ( id )
ON DELETE CASCADE;

ALTER TABLE poletko_ogrodnik
  ADD CONSTRAINT "Poletko/Ogrodnik_Ogrodnik_FK" FOREIGN KEY ( ogrodnik_id )
REFERENCES ogrodnik ( id );

ALTER TABLE poletko_ogrodnik
  ADD CONSTRAINT "Poletko/Ogrodnik_Poletko_FK" FOREIGN KEY ( poletko_id )
REFERENCES poletko ( id )
ON DELETE CASCADE;

ALTER TABLE punkt_graniczny
  ADD CONSTRAINT punkt_gra_punkt_gra_fk FOREIGN KEY ( punkt_graniczny_id )
REFERENCES punkt_graniczny ( id )
ON DELETE CASCADE;

ALTER TABLE punkt_graniczny
  ADD CONSTRAINT punkt_graniczny_sekcja_fk FOREIGN KEY ( sekcja_id )
REFERENCES sekcja ( id )
ON DELETE CASCADE;

ALTER TABLE gatunek_srodek_ochrony
  ADD CONSTRAINT relation_11_gatunek_fk FOREIGN KEY ( gatunek_id )
REFERENCES gatunek ( id )
ON DELETE CASCADE;

ALTER TABLE gatunek_srodek_ochrony
  ADD CONSTRAINT relation_11_srodek_ochrony_fk FOREIGN KEY ( srodek_ochrony_id )
REFERENCES srodek_ochrony ( id )
ON DELETE CASCADE;

ALTER TABLE roslina
  ADD CONSTRAINT roslina_gatunek_fk FOREIGN KEY ( gatunek_id )
REFERENCES gatunek ( id )
ON DELETE CASCADE;

ALTER TABLE roslina
  ADD CONSTRAINT roslina_poletko_fk FOREIGN KEY ( poletko_id )
REFERENCES poletko ( id )
ON DELETE SET NULL;

ALTER TABLE urlop
  ADD CONSTRAINT urlop_nieobecnosc_fk FOREIGN KEY ( id )
REFERENCES nieobecnosc ( id )
ON DELETE CASCADE;

ALTER TABLE urlop
  ADD CONSTRAINT urlop_typ_urlopu_fk FOREIGN KEY ( typ_urlopu_id )
REFERENCES typ_urlopu ( id )
ON DELETE CASCADE;

ALTER TABLE zuzycie_srodka
  ADD CONSTRAINT zuzy_srod_srod_och_fk FOREIGN KEY ( srodek_ochrony_id )
REFERENCES srodek_ochrony ( id )
ON DELETE CASCADE;

ALTER TABLE zuzycie_srodka
  ADD CONSTRAINT zuzycie_srodka_poletko_fk FOREIGN KEY ( poletko_id )
REFERENCES poletko ( id )
ON DELETE CASCADE;

ALTER TABLE zwolnienie
  ADD CONSTRAINT zwolnienie_nieobecnosc_fk FOREIGN KEY ( id )
REFERENCES nieobecnosc ( id )
ON DELETE CASCADE;

CREATE SEQUENCE gatunek_id_seq;
CREATE SEQUENCE roslina_id_seq;
CREATE SEQUENCE sekcja_id_seq;
CREATE SEQUENCE poletko_id_seq;
CREATE SEQUENCE ogrodnik_id_seq;
CREATE SEQUENCE punkt_graniczny_id_seq;
CREATE SEQUENCE srodek_ochrony_id_seq;
CREATE SEQUENCE nieobecnosc_id_seq;
CREATE SEQUENCE typ_urlopu_id_seq;
CREATE SEQUENCE zuzycie_srodka_id_seq;