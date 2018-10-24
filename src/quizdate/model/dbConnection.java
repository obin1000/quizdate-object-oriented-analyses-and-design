package quizdate.model;

import java.sql.*;

public class dbConnection {

    private static Connection connection;
    private static dbConnection singleton;

    static {
        singleton = new dbConnection();
    }

    private dbConnection() {
    }

    public PreparedStatement prepare(String statement) {
        PreparedStatement prepStatement = null;
        try {
            prepStatement = connection.prepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prepStatement;
    }

    public static dbConnection getInstance() {

        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://oege.ie.hva.nl:3306/zhadiyem?useUnicode=true&useJDBCCompliantTimezoneShift" +
                        "=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "hadiyem", "F+OYAvrrsj26nQ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
