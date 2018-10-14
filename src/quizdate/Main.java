package quizdate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import quizdate.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("QuizDater");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
//        LocalDate inputDate = LocalDate.of(2000,1,31);
//        LocalDate inputDat = LocalDate.of(2010,3,4);
//        LocalDate inputDa = LocalDate.of(2001,5,23);
//        System.out.println("Create three users:");
//        User u1 = new User("Vonk", "Robin", inputDate, "Male", "robin", "69","Hoorn","password");
//        User u2 = new User("Rummens", "Michel", inputDat, "Male", "robin", "42","Hoorn2","password");
//        User u3 = new User("Bootje", "Mootje", inputDa, "Male", "robin", "1337","Hoorn3","password");
//        System.out.println(u1);
//        System.out.println(u2);
//        System.out.println(u3);
//        System.out.println("They like eachother:");
//        u1.acceptMatch(u2);
//        u3.acceptMatch(u1);
//        System.out.println("u1likes: " + u1.getLikes());
//        System.out.println("u1matches: " + u1.getMatches());
//        u2.acceptMatch(u1);
//        System.out.println("u2likes: " + u2.getLikes());
//        System.out.println("u2matches: " + u2.getMatches());
//        System.out.println("u1matches: " + u1.getMatches());
//        System.out.println("u3likes: " + u3.getLikes());
//        System.out.println("u3matches: " + u3.getMatches());
//        System.out.println("They got a match");


//        System.out.println(UserRepository.getDatabase().getRandomId());
//        System.out.println(UserRepository.getDatabase().getRandomId());
//        System.out.println(UserRepository.getDatabase().getRandomId());
//        System.out.println(UserRepository.getDatabase().getRandomId());
        //System.out.println(UserRepository.getInstance().get(2));
        //System.out.println(ChatRepoSQL.getInstance().getById(2));
//        System.out.println(ChatRepoSQL.getInstance().getUserChatrooms(2));
//        ChatroomImpl chat = new ChatroomImpl();
//        chat.setMessages("BOOHOOOOOOOOOOOOOOOOOOOOOO");
//        chat.setRoomid(1);
////        ChatRepoSQL.getInstance().add(chat);
//            //System.out.println(ChatRepoSQL.getInstance().getRoomid());
//        ChatRepoSQL.getInstance().update(chat);
        System.out.println((ChatRepoSQL.getInstance().getById(1)));
        launch(args);
    }

}
