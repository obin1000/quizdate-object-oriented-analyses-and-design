package quizdate.model;

public class MatchService {

    private static MatchService singleton;

    static{
        singleton = new MatchService();
    }

    private MatchService(){}


    public boolean hasMatch(User currentUser, User otherUser) {
        return currentUser.getMatches().contains(otherUser);
    }

    public boolean hasLike(User currentUser, User otherUser) {
        return currentUser.getLikes().contains(otherUser);
    }

    public void addToLiked(User currentUser, User otherUser){
        currentUser.getLikes().add(otherUser);
        currentUser.notifyObservers();
    }

    public void addToMatches(User currentUser, User otherUser){
        currentUser.getMatches().add(otherUser);
        currentUser.notifyObservers();
    }


    public void removeFromMatch(User currentUser, User otherUser){
        currentUser.getMatches().remove(otherUser);
        currentUser.notifyObservers();
    }

    public void removeFromLike(User currentUser, User otherUser){
        currentUser.getLikes().remove(otherUser);
        currentUser.notifyObservers();
    }

    public boolean createMatch(User currentUser, User otherUser){
        try {
            ChatRoom chat = new ChatroomImpl();

            addToMatches(currentUser, otherUser); //add user2 to user1s matches
            chat.addUser(currentUser);
            currentUser.getChats().add(chat);

            addToMatches(otherUser, currentUser); // add user1 to user2s matches
            chat.addUser(otherUser);
            otherUser.getChats().add(chat);

            System.out.println("Chat between: " + currentUser + " " + otherUser);
        } catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    // Remove a Match between two users
    public boolean removeMatch(User currentUser, User otherUser, ChatRoom chat){
        try {
            removeFromLike(currentUser, otherUser);
            removeFromMatch(currentUser, otherUser);
            currentUser.getChats().remove(chat);

            removeFromLike(otherUser, currentUser);
            removeFromMatch(otherUser, currentUser);
            otherUser.getChats().remove(chat);

            System.out.println("Chat removed: " + currentUser + " " + otherUser);
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    // Is called when the user presses the accept button
    public boolean acceptMatch(User currentUser, User otherUser){
        boolean isMatch = false;

        addToLiked(currentUser,otherUser);  // add user2 to user1s likes list
        if(hasLike(otherUser,currentUser)){
            if(createMatch(currentUser,otherUser)){
                System.out.println("IT IS A MATCH!! :D");
                isMatch = true;
            }
        }
        return isMatch;
    }

    // Is called when user presses the deny button
    public  void denyMatch(User otherUser){
        System.out.println("that is not very nice for " + otherUser.getFirstName());
    }

    public static MatchService getInstance(){
        return singleton;
    }
}
