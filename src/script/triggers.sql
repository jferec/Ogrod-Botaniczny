CREATE OR REPLACE TRIGGER arc_fkarc_1_urlop BEFORE
    INSERT OR UPDATE OF id ON urlop
    FOR EACH ROW
DECLARE
    d   CHAR(1);
BEGIN
    SELECT
        a.typ
    INTO d
    FROM
        nieobecnosc a
    WHERE
        a.id =:new.id;

    IF
        ( d IS NULL OR d <> 'U' )
    THEN
        raise_application_error(-20223,'FK Urlop_Nieobecnosc_FK in Table Urlop violates Arc constraint on Table Nieobecnosc - discriminator column typ doesn''t have value ''U'''
);
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

CREATE OR REPLACE TRIGGER arc_fkarc_1_zwolnienie BEFORE
    INSERT OR UPDATE OF id ON zwolnienie
    FOR EACH ROW
DECLARE
    d   CHAR(1);
BEGIN
    SELECT
        a.typ
    INTO d
    FROM
        nieobecnosc a
    WHERE
        a.id =:new.id;

    IF
        ( d IS NULL OR d <> 'Z' )
    THEN
        raise_application_error(-20223,'FK Zwolnienie_Nieobecnosc_FK in Table Zwolnienie violates Arc constraint on Table Nieobecnosc - discriminator column typ doesn''t have value ''Z'''
);
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

create trigger trg_gatunek_id
      before insert on gatunek
      for each row
    begin
      select gatunek_id_seq.nextval
        into :new.id
        from dual;
    end;
/
create trigger trg_roslina_id
      before insert on roslina
      for each row
    begin
      select roslina_id_seq.nextval
        into :new.id
        from dual;
    end;
/
create trigger trg_sekcja_id
      before insert on sekcja
      for each row
    begin
      select sekcja_id_seq.nextval
        into :new.id
        from dual;
    end;
/
create trigger trg_poletko_id
      before insert on poletko
      for each row
    begin
      select poletko_id_seq.nextval
        into :new.id
        from dual;
    end;
/
create trigger trg_ogrodnik_id
      before insert on ogrodnik
      for each row
    begin
      select ogrodnik_id_seq.nextval
        into :new.id
        from dual;
    end;
/
create trigger trg_punkt_graniczny_id
      before insert on punkt_graniczny
      for each row
    begin
      select punkt_graniczny_id_seq.nextval
        into :new.id
        from dual;
    end;
/
create trigger trg_srodek_ochrony_id
      before insert on srodek_ochrony
      for each row
    begin
      select srodek_ochrony_id_seq.nextval
        into :new.id
        from dual;
    end;
/
create trigger trg_nieobecnosc_id
      before insert on nieobecnosc
      for each row
    begin
      select nieobecnosc_id_seq.nextval
        into :new.id
        from dual;
    end;
/
create trigger trg_typ_urlopu_id
      before insert on typ_urlopu
      for each row
    begin
      select typ_urlopu_id_seq.nextval
        into :new.id
        from dual;
    end;
/
create trigger trg_zuzucie_srodka
      before insert on zuzycie_srodka
      for each row
    begin
      select zuzycie_srodka_id_seq.nextval
        into :new.id
        from dual;
    end;
/
CREATE OR REPLACE TRIGGER nowy_ogrodnik
BEFORE INSERT ON ogrodnik
FOR EACH ROW
BEGIN
    :NEW.data_zatrudnienia := SYSDATE;
END;
/