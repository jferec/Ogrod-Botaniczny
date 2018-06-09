package loginApp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import viewer.ViewerController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Option> combobox;
    @FXML
    private Button loginbutton;
    @FXML
    private Label loginstatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String text = (this.loginModel.isDatabaseConnected()) ? "Connected" : "Disconnected";

        this.dbstatus.setText(text);

        this.combobox.setItems(FXCollections.observableArrayList(Option.values()));

    }

    @FXML
    public void login(ActionEvent event){
        try{

            if(this.loginModel.isLogin(this.username.getText(), this.password.getText(), this.combobox.getValue().toString())){
                Stage stage = (Stage)this.loginbutton.getScene().getWindow();
                stage.close();
                switch (this.combobox.getValue().toString()){
                    case "Admin" : adminLogin();
                    break;
                    case "Viewer" : viewerLogin();
                    break;
                    default :
                    System.out.println("Invalid combobox value");
                    break;

                }
            }
            else {
                    this.loginstatus.setText("Wrong username or password");
            }

        }
        catch (Exception localException){

        }
    }

    public void adminLogin() throws Exception{
        try{
            Stage adminStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            Pane root = (Pane)loader.load(getClass().getResource("/admin/admin.fxml").openStream());
            AdminController adminController = (AdminController)loader.getController();

            Scene scene = new Scene(root);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void viewerLogin(){
        try{
            Stage viewerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();

            Pane root = (Pane)loader.load(getClass().getResource("/viewer/viewer.fxml").openStream());
            ViewerController viewerController = (ViewerController)loader.getController();

            Scene scene = new Scene(root);
            viewerStage.setScene(scene);
            viewerStage.setTitle("Viewer Dashboard");
            viewerStage.setResizable(false);
            viewerStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
