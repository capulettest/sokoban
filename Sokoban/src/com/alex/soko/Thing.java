package com.alex.soko;

import javafx.scene.layout.StackPane;

public abstract class Thing {
    protected ThingView view;
    protected Field field;

    /**
     ** A Thing megjelenítéséért felelős paraméterként átvett
     ** StackPane típusú kinézetét állítja be.
     */
    public void setField(Field f){
        field = f;
    }

    /**
    * A Thing megjelenítésélrt felelős StackPane típusú kinézetét adja vissza.
    */
    public StackPane getView(){
        return view;
    }

    /**
     * A Thinget direction irányban megpróbálja elmozdítani a játékos modellje.
     */
    public boolean hitByPlayer(Direction d){return true;}

    /**
     * A Thinget megpróbálja elmozdítani a doboz modellje.
     */
    public boolean hitByBox(){return false;}

    /**
     ** Az addot Fieldre tovább lépni kívánt Thingre hívott metódus.
     ** Paraméterként az adott Fielden álló Thinget kapja.
     */
    public boolean collideWith(Thing t){return false;}
}
