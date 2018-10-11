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
        if (connection == null) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://oege.ie.hva.nl:3306/zhadiyem?useUnicode=true&useJDBCCompliantTimezoneShift" +
                        "=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "hadiyem", "F+OYAvrrsj26nQ");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static dbConnection getInstance() {
        return singleton;
    }

    public void close() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
