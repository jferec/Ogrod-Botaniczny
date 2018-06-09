package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OgrodnikData {

    private final StringProperty id;
    private final StringProperty imie;
    private final StringProperty nazwisko;
    private final StringProperty pesel;
    private final StringProperty telefon;
    private final StringProperty dataZatrudnienia;
    private final StringProperty zastepca;
    private final StringProperty przelozony;

    public OgrodnikData(String id, String imie, String nazwisko, String pesel, String telefon, String dataZatrudnienia, String zastepca, String przelozony){
        this.id = new SimpleStringProperty(id);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.telefon = new SimpleStringProperty(telefon);
        this.pesel = new SimpleStringProperty(pesel);
        this.dataZatrudnienia = new SimpleStringProperty(dataZatrudnienia);
        this.zastepca = new SimpleStringProperty(zastepca);
        this.przelozony = new SimpleStringProperty(przelozony);
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

    public String getImie() {
        return imie.get();
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public String getTelefon() {
        return telefon.get();
    }

    public StringProperty telefonProperty() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    public String getDataZatrudnienia() {
        return dataZatrudnienia.get();
    }

    public StringProperty dataZatrudnieniaProperty() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(String dataZatrudnienia) {
        this.dataZatrudnienia.set(dataZatrudnienia);
    }

    public String getZastepca() {
        return zastepca.get();
    }

    public StringProperty zastepcaProperty() {
        return zastepca;
    }

    public void setZastepca(String zastepca) {
        this.zastepca.set(zastepca);
    }

    public String getPrzelozony() {
        return przelozony.get();
    }

    public StringProperty przelozonyProperty() {
        return przelozony;
    }

    public void setPrzelozony(String przelozony) {
        this.przelozony.set(przelozony);
    }




}
