package quizdate.model;

import javafx.scene.image.Image;

import java.sql.*;
import java.util.List;

public class UserRepository implements DAO<User> {

    private final static UserRepository singleton;

    static {
        singleton = new UserRepository();
    }

    private UserRepository() {
    }

    public int checkLoginInformation(String email, String password) {
        int userId = 0;
        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT userId FROM Account WHERE email ='" + email + "' AND password ='" + password + "'");
            if(rs.next()){
                System.out.println(userId = Integer.parseInt(rs.getString("userId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return userId;

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public int getRandomId() {
        int data = 1;
        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet back = statement.executeQuery("SELECT userId FROM Account ORDER BY RAND() LIMIT 1");
            back.next();
            data = back.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return data;
    }

    @Override
    public User get(int id) {
        User user = null;
        try {

            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Account WHERE userId = " + id);

            if(rs.next()) {
                user = new User(rs.getString("lastName"), rs.getString("firstName"),
                        rs.getDate("dateOfBirth").toLocalDate(), rs.getString("sex"),
                        rs.getString("email"), rs.getString("phoneNumber"),
                        rs.getString("adres"), rs.getString("password"));
                if(rs.getString("picturePath") != null) {
                    Image profilePic = new Image(rs.getString("picturePath"));
                    user.setProfilePicture(profilePic);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return user;
    }

    @Override
    public boolean save(User user) {
        boolean status = false;

        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("INSERT INTO Account (email, password, lastName, firstName, dateOfBirth," +
                    " sex, phoneNumber, adres) VALUES ('" + user.getEmail() + "', '" + user.getPassword() + "', '" +
                    user.getLastName() + "', '" + user.getFirstName() + "', '" + user.getDateOfBirth() + "', '" +
                    user.getSex() + "', '" + user.getPhoneNumber() + "', '" + user.getAdres() + " ' )");
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return status;
    }

    @Override
    public boolean update(int id, User user) {
        boolean status = false;

        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("UPDATE Account SET lastName = '" + user.getLastName() + "', " +
                    "firstName = '" + user.getFirstName() + "' , dateOfBirth = '" + user.getDateOfBirth() + "'" +
                    ", sex = '" + user.getSex() + "', email = '" + user.getEmail() + "', phoneNumber = '" +
                    user.getPhoneNumber() + "', adres = '" + user.getAdres() + "', password = '" + user.getPassword() +
                    "' WHERE userId = " +  id);

            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return status;
    }

    @Override
    public boolean remove(int id) {
        boolean status = false;

        try {
            Statement statement = dbConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("DELETE FROM Account WHERE userId = " + id);
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();
        return status;
    }

    public static UserRepository getInstance() {
        return UserRepository.singleton;
    }
}