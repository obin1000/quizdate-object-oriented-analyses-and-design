package quizdate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private static Connection connection;
    private static dbConnection singleton;

    static {
        singleton = new dbConnection();
    }

    private dbConnection() {
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://oege.ie.hva.nl:3306/zhadiyem?useUnicode=true&useJDBCCompliantTimezoneShift" +
                        "=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "hadiyem", "F+OYAvrrsj26nQ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static dbConnection getInstance() {
        return singleton;
    }

    public void close() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
