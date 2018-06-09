package loginApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dbUtil.DbConnection;

import static java.lang.System.exit;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        try {
            this.connection = DbConnection.getConnection();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        if(this.connection == null) {
            exit(1);
        }
    }


    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    public boolean isLogin(String username, String password, String option) throws Exception{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            String sql = "SELECT * FROM login WHERE username = ? AND password = ?";

            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next() == true;
        }
        catch (SQLException ex){
            return false;
        }

        finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}
