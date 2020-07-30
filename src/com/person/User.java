package com.person;

public class User extends Person{

    public int vote = 10;

    public User(int userID, String name, String password) {
        super(userID, name, password);
    }


    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getVote() {
        return vote;
    }

    public boolean canVote(int n){
        if (n > vote)
            return false;
        else
            return true;
    }


}
