package sample;

import java.sql.*;

public class SQL {

    private Connection connection;
    private Statement statement;

    public SQL(String conLink, String username, String password) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(conLink, username, password);
            this.statement = connection.createStatement();

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void execute(String text) {

        try {
            statement.executeQuery(text);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void update(String text) {
        try {
            statement.executeUpdate(text);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}