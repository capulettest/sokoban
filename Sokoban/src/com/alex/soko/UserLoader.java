package com.alex.soko;

import java.io.*;

public class UserLoader {

    /**
     * Betölti a ez eddig elmentett felhasználókat fájlból.
     */
    public static UserList loadUsersFromFile(){
        UserList userList = null;
        try{
            FileInputStream fis = new FileInputStream("users.txt");
            ObjectInputStream in = new ObjectInputStream(fis);
            userList = (UserList)in.readObject();
            in.close();

        } catch(IOException e) {}
        catch(ClassNotFoundException e) {
            System.out.println("...ClassNotFoundException");
            e.printStackTrace();
        }
        if(userList == null)
            userList = new UserList();
        return userList;
    }

    /**
     ** Fájlba menti a programban az aktuális sessionben eddig aktivált felhasználókat,
     ** valamint azokat, akiket már beolvasott az applikáció elindításakor.
     */
    public static void saveUsersToFile(UserList userList){
        try{
            FileOutputStream fos = new FileOutputStream("users.txt");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(userList);
            out.close();
        } catch(IOException e) {}
    }
}
