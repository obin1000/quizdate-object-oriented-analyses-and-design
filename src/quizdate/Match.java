package quizdate;

public class Match {

    public void checkForMatch(User u1, User u2){
        if (u1.hasLike(u2)){
            if (u2.hasLike(u1)){
                createMatch(u1, u2);
            }
        }
    }

    // A user can request a Match to accept or deny it, Sends a random int from the database
    public int requestMatch() {
        SQL db = new SQL();
        return db.getRandomId();
    }

    // Create a Match between two users
    public boolean createMatch(User u1, User u2){
        u1.addToMatches(u2.getUserId()); //add user2 to user1s matches
        u2.addToMatches(u1.getUserId()); // add user1 to user2s matches
        return true;
    }
    // Remove a Match between two users
    public boolean removeMatch(User u1, User u2){
        u1.removeLike(u2);
        u1.removeMatch(u2);
        u2.removeMatch(u1);
        return true;
    }

    // Is called when the user presses the accept button
    public void acceptMatch(User u1, User u2){
        u1.addToLiked(u2.getUserId());  // add user2 to user1s likes list
        checkForMatch(u1, u2);
    }
    // Is called when user presses the deny button
    public  void denyMatch(){

    }
}
