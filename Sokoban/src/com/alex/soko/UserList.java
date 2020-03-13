package com.alex.soko;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable {
    private static final long serialVersionUID = 867444L;
    private ArrayList<User> users;

    UserList(){
        users = new ArrayList<>();
    }

    /**
     ** Visszaad egy a paraméterként átvett névvel rendelkező
     ** User objektumot a users lista attribútumban
     ** található Userek közül.
     ** Amennyiben még nincs ilyen userName
     ** attribútummal rendelkező User objektum, úgy az adott névvel
     ** létrehoz egyet, amit be is tesz a users listába,
     ** majd visszatér vele.
     */
    public User getUser(String userName){
        for(User user : users){
            if(user.getUserName().equals(userName))
                return user;
        }
        User actualUser = new User(userName, 1);
        users.add(actualUser);
        return actualUser;
    }
}
