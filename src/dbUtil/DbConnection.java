package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;

public class DbConnection {
    final private static String USERNAME = "SYSTEM";
    final private static String PASSWORD = "kamikadze";
    final private static String CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
    final private static String DRIVER = "oracle.jdbc.driver.OracleDriver";

    public static Connection getConnection() throws SQLException {
        System.out.println("Connecting to database...");

        try {
            System.out.println("Database connected!");
            return DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getCONNECTION() {
        return CONNECTION;
    }

    public static String getDRIVER() {
        return DRIVER;
    }


}
