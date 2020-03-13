package com.alex.soko;

public class Wall extends Thing{
    Wall(){
        view = new WallView();
    }

    public boolean hitByPlayer(Direction d){
        return false;
    }
}
