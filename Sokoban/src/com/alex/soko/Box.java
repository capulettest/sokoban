package com.alex.soko;

public class Box extends Thing{
    Box(){
        view = new BoxView();
    }

    public boolean hitByPlayer(Direction d){
        if(field.placeBoxToNextField(d))
            return true;
        return false;
    }

    @Override
    public boolean collideWith(Thing t) {
        return t.hitByBox();
    }
}
