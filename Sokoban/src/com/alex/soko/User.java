package com.alex.soko;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1457123L;
    private String userName;
    private int acquiredLevelNumber;

    User(String name, int level){
        userName = name;
        acquiredLevelNumber = level;
    }

    /**
     * Visszaadja a User objektumban álltal
     * reprezentál felhasználó a felhasználó álltal
     * beállított nevét.
     */
    public String getUserName() {
        return userName;
    }

    /**
     ** Visszaadja a User objektumban álltal
     ** reprezentál felhasználó a felhasználó álltal
     ** legutoljára feloldott pálya sorszámát.
     */
    public int getAcquiredLevelNumber() {
        return acquiredLevelNumber;
    }

    /**
     ** Beállítja a User objektumban álltal
     ** reprezentál felhasználó a felhasználó álltal
     ** legutoljára feloldott pálya sorszámát.
     */
    public void setAcquiredLevelNumber(int n){
        acquiredLevelNumber = n;
    }

    /**
     ** User objektumok egyenlőségét vizsgálja a
     ** User osztályban megtalálható userName: String
     ** attribútum összehasonlításával.
     */
    @Override
    public boolean equals(Object obj){
        return  this.userName.equals(((User)obj).userName);
    }

}
