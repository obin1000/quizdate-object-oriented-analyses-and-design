package sample;

public class match {

    public boolean checkIfLiked(User u, int ID){
        for (Integer number : u.getLikes()){
            if(ID == number){
                return true;
            }
        }
        return false;
    }
    public void checkForMatches(){

    }
    public int requestMatch() {
        return 1000;
    }

    public boolean createMatch(User u1, User u2){
        u1.addToMatches(u2.getUserId()); //add user2 to user1s matches
        u2.addToMatches(u1.getUserId()); // add user1 to user2s matches
        return true;
    }

    public boolean removeMatch(User u1, User u2){
        u1.removeMatch(u2);
        u2.removeMatch(u1);
        return true;
    }

    // Is called when the user presses the accept button
    public void acceptMatch(User u1, User u2){
        u1.addToLiked(u2.getUserId()); // add user2 to user1s likes list
        if(checkIfLiked(u2, u1.getUserId())){ //check if user1 is in user2s likes list
            createMatch(u1,u2);
        }

    }
    // Is called when user presses the deny button
    public  void denyMatch(){

    }
}
