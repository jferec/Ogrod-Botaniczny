package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SekcjaPoletkoData {

    private final StringProperty id;
    private final StringProperty nazwa;
    private final StringProperty powierzchnia;
    private final StringProperty zuzycieWody;
    private final StringProperty zuzycieSrodka;


    public SekcjaPoletkoData(String id, String nazwa, String powierzchnia, String zuzycieWody, String zuzycieSrodka){
        this.id = new SimpleStringProperty(id);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.powierzchnia = new SimpleStringProperty(powierzchnia);
        this.zuzycieSrodka = new SimpleStringProperty(zuzycieSrodka);
        this.zuzycieWody = new SimpleStringProperty(zuzycieWody);
    }


    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public StringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public String getPowierzchnia() {
        return powierzchnia.get();
    }

    public StringProperty powierzchniaProperty() {
        return powierzchnia;
    }

    public void setPowierzchnia(String powierzchnia) {
        this.powierzchnia.set(powierzchnia);
    }


    public String getZuzycieWody() {
        return zuzycieWody.get();
    }

    public StringProperty zuzycieWodyProperty() {
        return zuzycieWody;
    }

    public void setZuzycieWody(String zuzycieWody) {
        this.zuzycieWody.set(zuzycieWody);
    }

    public String getZuzycieSrodka() {
        return zuzycieSrodka.get();
    }

    public StringProperty zuzycieSrodkaProperty() {
        return zuzycieSrodka;
    }

    public void setZuzycieSrodka(String zuzycieSrodka) {
        this.zuzycieSrodka.set(zuzycieSrodka);
    }
}
