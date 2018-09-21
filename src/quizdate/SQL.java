package quizdate;

import java.sql.*;

public class SQL {

    private Connection connection;
    private Statement statement;

    public SQL() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://oege.ie.hva.nl:3306/zhadiyem?useUnicode=true&useJDBCCompliantTimezoneShift" +
                    "=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "hadiyem","F+OYAvrrsj26nQ");
            this.statement = connection.createStatement();

        } catch (Exception e) {

            System.err.println(e);
        }

    }

    public void execute(String text) {

        try {
            statement.executeQuery(text);

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public boolean update(String text) {
        boolean status = false;
        try {
            statement.executeUpdate(text);
            status = true;

        } catch (Exception e) {
            System.err.println(e);
        }
        return status;
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


}