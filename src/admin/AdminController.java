package admin;

import data.OgrodnikData;
import data.SekcjaPoletkoData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import dbUtil.DbConnection;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import script.Insert;
import script.SQLExe;

public class AdminController implements Initializable {
    @FXML
    private Label time;
    @FXML
    private TextField ogrodnikN;
    @FXML
    private TextField sekcjaN;
    @FXML
    private TextField poletkoN;

    private boolean sekcja = false;
    @FXML
    private TextField deleteId;
    @FXML
    private TextField Id;
    @FXML
    private TextField nazwa;
    @FXML
    private TextField powierzchnia;
    @FXML
    private TextField sekcjaID;
    @FXML
    private DatePicker dataOD;
    @FXML
    private DatePicker dataDO;

    @FXML
    private TableView<SekcjaPoletkoData> sekcjaPoletkoTable;
    @FXML
    private TableColumn<SekcjaPoletkoData, String> sekcjaPoletkoIdColumn;
    @FXML
    private TableColumn<SekcjaPoletkoData, String> sekcjaPoletkoNazwaColumn;
    @FXML
    private TableColumn<SekcjaPoletkoData, String> sekcjaPoletkoPowierzchniaColumn;
    @FXML
    private TableColumn<SekcjaPoletkoData, String> sekcjaPoletkoZuzycieSrodkaColumn;
    @FXML
    private TableColumn<SekcjaPoletkoData, String> sekcjaPoletkoZuzycieWodyColumn;
    @FXML
    private TableColumn<OgrodnikData, String> phonecolumn;
    @FXML
    private TableColumn<OgrodnikData, String> emailcolumn;
    //STATS
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> types = FXCollections.observableArrayList();
    @FXML
    private Label avgLabel;

    @FXML
    private javafx.scene.control.Button refreshButtonLodgingUnit;
    @FXML
    private javafx.scene.control.Button refreshButtonGuest;

    @FXML
    private ImageView imageView;

    //new Image(getClass().getResourceAsStream("refreshbutton.png"));

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    private DbConnection connection;
    private ObservableList<SekcjaPoletkoData> sekcjaPoletkoData;


    @FXML
    private void loadSekcjaData(javafx.event.ActionEvent actionEvent) {
        try {
            Connection connection = DbConnection.getConnection();
            this.sekcjaPoletkoData = FXCollections.observableArrayList();

            ResultSet resultSet = connection.createStatement()
                    .executeQuery("SELECT S.ID, S.NAZWA, NVL(SUM(P.POWIERZCHNIA),0) AS powierzchnia\n" +
                            "FROM SEKCJA S\n" +
                            "LEFT JOIN POLETKO P ON S.ID = P.SEKCJA_ID\n" +
                            "GROUP BY S.ID, S.NAZWA " +
                            "ORDER BY SUM(P.POWIERZCHNIA) DESC");

            while (resultSet.next()) {
                this.sekcjaPoletkoData.add(new SekcjaPoletkoData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), " ", " "));
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.sekcjaPoletkoIdColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("id"));
        this.sekcjaPoletkoNazwaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("nazwa"));
        this.sekcjaPoletkoPowierzchniaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("powierzchnia"));

        this.sekcjaPoletkoTable.setItems(null);
        this.sekcjaPoletkoTable.setItems(sekcjaPoletkoData);
        sekcja = true;
    }


    @FXML
    private void loadPoletkoData(javafx.event.ActionEvent actionEvent) {
        try {
            Connection connection = DbConnection.getConnection();
            this.sekcjaPoletkoData = FXCollections.observableArrayList();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM POLETKO P ORDER BY P.POWIERZCHNIA DESC");
            while (resultSet.next()) {
                this.sekcjaPoletkoData.add(new SekcjaPoletkoData(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), " ", " "));
            }
            resultSet.close();
        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

        this.sekcjaPoletkoIdColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("id"));
        this.sekcjaPoletkoNazwaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("nazwa"));
        this.sekcjaPoletkoPowierzchniaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("powierzchnia"));

        this.sekcjaPoletkoTable.setItems(null);
        this.sekcjaPoletkoTable.setItems(sekcjaPoletkoData);
        sekcja = false;
    }


    @FXML
    private void loadZuzycieSrodkow(javafx.event.ActionEvent actionEvent) throws Exception {
        try {
            Connection connection = DbConnection.getConnection();
            this.sekcjaPoletkoData = FXCollections.observableArrayList();

            if (sekcja) {
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT S.ID, S.NAZWA, ROUND(SUM(Z.WAGA)/SUM(P.POWIERZCHNIA), 4) AS RESULT " +
                        "FROM SEKCJA S " +
                        "JOIN POLETKO P ON S.ID = P.SEKCJA_ID " +
                        "JOIN ZUZYCIE_SRODKA Z ON P.ID = Z.POLETKO_ID " +
                        "JOIN SRODEK_OCHRONY SO ON Z.SRODEK_OCHRONY_ID = SO.ID\n" +
                        "WHERE (Z.DATA_UZYCIA >=  TO_DATE('" + formatDate(this.dataOD.getEditor().getText()) +
                        "', 'DD-MM-YYYY') AND Z.DATA_UZYCIA <= TO_DATE('" + formatDate(this.dataDO.getEditor().getText()) +
                        "', 'DD-MM-YYYY') AND SO.ID != 1) " +
                        "GROUP BY S.ID, S.NAZWA " +
                        "ORDER BY RESULT DESC");
                while (resultSet.next()) {
                    this.sekcjaPoletkoData.add(new SekcjaPoletkoData(resultSet.getString(1), resultSet.getString(2), " ", " ", resultSet.getString(3)));
            }
                resultSet.close();
            }
            else{
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT P.ID, P.NAZWA, ROUND(SUM(Z.WAGA)/P.POWIERZCHNIA, 4) AS RESULT " +
                        "FROM POLETKO P " +
                        "JOIN ZUZYCIE_SRODKA Z ON P.ID = Z.POLETKO_ID " +
                        "JOIN SRODEK_OCHRONY SO ON Z.SRODEK_OCHRONY_ID = SO.ID\n" +
                        "WHERE (Z.DATA_UZYCIA >=  TO_DATE('" + formatDate(this.dataOD.getEditor().getText()) +
                        "', 'DD-MM-YYYY') AND Z.DATA_UZYCIA <= TO_DATE('" + formatDate(this.dataDO.getEditor().getText()) +
                        "', 'DD-MM-YYYY')AND SO.ID != 1) " +
                        "GROUP BY P.ID, P.NAZWA, P.POWIERZCHNIA " +
                        "ORDER BY RESULT DESC");
                while (resultSet.next()) {
                    this.sekcjaPoletkoData.add(new SekcjaPoletkoData(resultSet.getString(1), resultSet.getString(2), " ", " ", resultSet.getString(3)));
                }

            }

            this.sekcjaPoletkoIdColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("id"));
            this.sekcjaPoletkoNazwaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("nazwa"));
            this.sekcjaPoletkoPowierzchniaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("powierzchnia"));
            this.sekcjaPoletkoZuzycieWodyColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String >("zuzycieWody"));
            this.sekcjaPoletkoZuzycieSrodkaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("zuzycieSrodka"));


            this.sekcjaPoletkoTable.setItems(null);
            this.sekcjaPoletkoTable.setItems(sekcjaPoletkoData);

        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }
    }

    @FXML
    private void loadZuzycieWody(javafx.event.ActionEvent actionEvent) throws Exception {
        try {
            Connection connection = DbConnection.getConnection();
            this.sekcjaPoletkoData = FXCollections.observableArrayList();

            if (sekcja) {
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT S.ID, S.NAZWA, ROUND(SUM(Z.WAGA)/SUM(P.POWIERZCHNIA), 4) AS RESULT\n" +
                        "FROM SEKCJA S\n" +
                        "JOIN POLETKO P ON S.ID = P.SEKCJA_ID\n" +
                        "JOIN ZUZYCIE_SRODKA Z ON P.ID = Z.POLETKO_ID\n" +
                        "JOIN SRODEK_OCHRONY SO ON Z.SRODEK_OCHRONY_ID = SO.ID\n" +
                        "WHERE (Z.DATA_UZYCIA >=  TO_DATE('" + formatDate(this.dataOD.getEditor().getText()) +
                        "', 'DD-MM-YYYY') AND Z.DATA_UZYCIA <= TO_DATE('" + formatDate(this.dataDO.getEditor().getText()) +
                        "', 'DD-MM-YYYY') AND SO.ID = 1) " +
                        "GROUP BY S.ID, S.NAZWA\n" +
                        "ORDER BY RESULT DESC");
                while (resultSet.next()) {
                    this.sekcjaPoletkoData.add(new SekcjaPoletkoData(resultSet.getString(1), resultSet.getString(2), " ", resultSet.getString(3), " "));
                }

            }
            else {
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT P.ID, P.NAZWA, ROUND(SUM(Z.WAGA)/P.POWIERZCHNIA, 4) AS RESULT\n" +
                        "FROM POLETKO P\n" +
                        "JOIN ZUZYCIE_SRODKA Z ON P.ID = Z.POLETKO_ID\n" +
                        "JOIN SRODEK_OCHRONY SO ON Z.SRODEK_OCHRONY_ID = SO.ID\n" +
                        "WHERE (Z.DATA_UZYCIA >=  TO_DATE('" + formatDate(this.dataOD.getEditor().getText()) +
                        "', 'DD-MM-YYYY') AND Z.DATA_UZYCIA <= TO_DATE('" + formatDate(this.dataDO.getEditor().getText()) +
                        "', 'DD-MM-YYYY') AND SO.ID = 1) " +
                        "GROUP BY P.ID, P.NAZWA, P.POWIERZCHNIA\n" +
                        "ORDER BY RESULT DESC");
                while (resultSet.next()) {
                    this.sekcjaPoletkoData.add(new SekcjaPoletkoData(resultSet.getString(1), resultSet.getString(2), " ", resultSet.getString(3), " "));
                }

            }
            this.sekcjaPoletkoIdColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("id"));
            this.sekcjaPoletkoNazwaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("nazwa"));
            this.sekcjaPoletkoPowierzchniaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("powierzchnia"));
            this.sekcjaPoletkoZuzycieWodyColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String >("zuzycieWody"));
            this.sekcjaPoletkoZuzycieSrodkaColumn.setCellValueFactory(new PropertyValueFactory<SekcjaPoletkoData, String>("zuzycieSrodka"));


            this.sekcjaPoletkoTable.setItems(null);
            this.sekcjaPoletkoTable.setItems(sekcjaPoletkoData);

        } catch (SQLException ex) {
            System.err.println("Error " + ex);
        }

    }
    @FXML
    private void odswiez(javafx.event.ActionEvent actionEvent) throws Exception{
        Connection connection = DbConnection.getConnection();
        String sql = "COMMIT";
        connection.createStatement().executeQuery(sql);
    }

    @FXML
    private void insertPoletkoSekcja(javafx.event.ActionEvent actionEvent) throws Exception {
        String sql;
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;
        if (sekcja) {
            sql = "INSERT INTO SEKCJA (nazwa) VALUES (?)";

            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, this.nazwa.getText());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            sql = "INSERT INTO POLETKO (nazwa, Sekcja_id, powierzchnia) VALUES (?, ? ,?)";

            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, this.nazwa.getText());
                preparedStatement.setString(2, this.sekcjaID.getText());
                preparedStatement.setString(3, this.powierzchnia.getText());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        System.out.println(sql);
        preparedStatement.execute();
        preparedStatement.close();
    }


        @FXML
        private void deleteSekcjaPoletko (javafx.event.ActionEvent actionEvent){
            String sql;
            if (sekcja)
                sql = "DELETE FROM SEKCJA WHERE id = ?";
            else
                sql = "DELETE FROM POLETKO WHERE id = ?";

            try {
                Connection connection = DbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, Integer.parseInt(this.deleteId.getText()));

                preparedStatement.execute();
                preparedStatement.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }



    @FXML
    private void generujInserty(javafx.event.ActionEvent actionEvent) throws Exception{
        int sekN = Integer.parseInt(sekcjaN.getText());
        int polN = Integer.parseInt(poletkoN.getText());
        int ogrN = Integer.parseInt(ogrodnikN.getText());

        long startTime = System.currentTimeMillis();
        Insert.generateDate(sekN, polN, ogrN, 10);
        long estimatedTime = System.currentTimeMillis() - startTime;
        this.time.setText(estimatedTime + " ms");
    }

    @FXML
    private void oproczBazeDanych(javafx.event.ActionEvent actionEvent) throws Exception{

        long startTime = System.currentTimeMillis();
        SQLExe.executeSql("src/script/drop.sql", ";");
        long estimatedTime = System.currentTimeMillis() - startTime;
        this.time.setText(estimatedTime + " ms");
    }

    @FXML
    private void wstawRekordy(javafx.event.ActionEvent actionEvent) throws Exception{
        long startTime = System.currentTimeMillis();
        SQLExe.executeSql("src/script/insert.sql", ";");
        long estimatedTime = System.currentTimeMillis() - startTime;
        this.time.setText(estimatedTime + " ms");
    }

    @FXML
    private void uruchomDDL(javafx.event.ActionEvent actionEvent) throws Exception{
        long startTime = System.currentTimeMillis();
        SQLExe.executeSql("src/script/ddl.sql", ";");
        SQLExe.executeSql("src/script/triggers.sql", "/");
        long estimatedTime = System.currentTimeMillis() - startTime;
        this.time.setText(estimatedTime + " ms");
    }



    private String formatDate(String input)throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
        Date date = parser.parse(input);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

}

