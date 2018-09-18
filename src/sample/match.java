package sample;

public class match {

    public boolean checkIfLiked(User u, int ID){
        for (Integer number : u.getLikes()){
            if(ID == (int) number){
                return true;
            }
        }
        return false;
    }
    public int requestMatch() {
        return 1000;
    }
    // Is called when the user presses the accept button
    public void acceptMatch(User u1, User u2){
        u1.addToLiked(u2.getUserId()); // add user2, to user1s likeslist
        if(checkIfLiked(u2, u1.getUserId())){ //check if user1 is in user2s like list
            u1.addToMatches(u2.getUserId()); //add user2 to user1s matches
            u2.addToMatches(u1.getUserId()); // add user1 to user2s matches
        }

    }
    // Is called when user presses the deny button
    public  void denyMatch(){

    }
}
