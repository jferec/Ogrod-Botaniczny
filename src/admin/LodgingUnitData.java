package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LodgingUnitData {

    private final StringProperty id;
    private final StringProperty type;
    private final StringProperty name;
    private final StringProperty starRating;

    public LodgingUnitData(String id, String name, String type, String starRating){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.starRating = new SimpleStringProperty(starRating);
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getStarRating() {
        return starRating.get();
    }

    public StringProperty starRatingProperty() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating.set(starRating);
    }


}
