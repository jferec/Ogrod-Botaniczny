package loginApp;

public enum Option {
Admin, Viewer;

public String value(){
    return name();
}
public static Option fromValue(String value) {
    return valueOf(value);
}
}