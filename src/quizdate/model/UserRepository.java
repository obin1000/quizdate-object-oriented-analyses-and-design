package quizdate.model;

import javafx.scene.image.Image;

import java.sql.*;
import java.util.List;

public class UserRepository implements Repository<User> {

    private final static UserRepository singleton;

    static {
        singleton = new UserRepository();
    }

    private UserRepository() {
    }

    public int checkLoginInformation(String email, String password) {
        int userId = 0;
        try {
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT userId FROM Account WHERE email =? AND password =?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

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

    public int getRandomId() {
        int data = 1;
        try {
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT userId FROM Account ORDER BY RAND() LIMIT 1");
            ResultSet back = statement.executeQuery();
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
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT * FROM Account WHERE userId =?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            System.out.println(rs);
            if(rs.next()) {
                user = new User(rs.getString("lastName"), rs.getString("firstName"),
                        rs.getDate("dateOfBirth").toLocalDate(), rs.getString("sex"),
                        rs.getString("email"), rs.getString("phoneNumber"),
                        rs.getString("adres"), rs.getString("password"));
                user.setUserId(rs.getInt("userId"));
                if(rs.getString("picturePath") != null) {
                    Image profilePic = new Image(rs.getString("picturePath"));
                    user.setProfilePicture(profilePic);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int getUserId(User u){
        int userId = 0;


        try {
            PreparedStatement statement;
            statement = dbConnection.getInstance().prepare("SELECT userId FROM Account WHERE email = ?");
            statement.setString(1, u.getEmail());
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                userId = rs.getInt("userId");
                }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        dbConnection.getInstance().close();





        return userId;
    }

    @Override
    public boolean save(User user) {
        boolean status = false;
        PreparedStatement statement;
        try {
            statement = dbConnection.getInstance().prepare("INSERT INTO Account (email, password, lastName, " +
                    "firstName, dateOfBirth," +
                    " sex, phoneNumber, adres) VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getFirstName());
            statement.setDate(5, Date.valueOf(user.getDateOfBirth()));
            statement.setString(6, user.getSex());
            statement.setString(7, user.getPhoneNumber());
            statement.setString(8, user.getAdres());
            statement.executeUpdate();
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
        PreparedStatement statement;
        try {

            statement = dbConnection.getInstance().prepare("UPDATE Account SET lastName = ?, " +
                    "firstName = ?, dateOfBirth = ?" +
                    ", sex = ?, email = ?, phoneNumber = ?" +
                    ", adres = ?, password = ? WHERE userId = ?");

            statement.setString(1, user.getLastName());
            statement.setString(2, user.getFirstName());
            statement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            statement.setString(4, user.getSex());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getAdres());
            statement.setString(8, user.getPassword());
            statement.setInt(9, id);

            statement.executeUpdate();
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
        PreparedStatement statement;
        try {
            statement = dbConnection.getInstance().prepare("DELETE FROM Account WHERE userId = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
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