package com.alex.soko;

import javafx.scene.layout.StackPane;

public class Field extends StackPane {
    private Maze maze;
    private  Thing thing;
    private boolean isBase;
    private Field[] neighbors = new Field[4];


    Field(Maze m, Thing t, boolean isThingMovable, StackPane fieldView, boolean isBase){
        maze = m;
        this.setPrefSize(50,50);
        thing = t;
        this.isBase = isBase;
        thing.setField(this);
        this.getChildren().add(fieldView);
        if(isThingMovable)
            this.getChildren().add(thing.getView());
    }

    /**
     * A Field rendre fölfele, balra, jobbra, lefele szomszédjait állítja be.
     */
    public void setNeighbors(Field up, Field left, Field right, Field down) {
        neighbors[Direction.UP.ordinal()] = up;
        neighbors[Direction.LEFT.ordinal()] = left;
        neighbors[Direction.RIGHT.ordinal()] = right;
        neighbors[Direction.DOWN.ordinal()] = down;
    }

    /**
     * A Field megpróbálja a direction irányban lévő szomszédjára helyezni a rajta lévő Thinget.
     * Amennyiben bázis pozíció, úgy sikeres Thing továbbítás esetén a Thing méretét a bázis
     * pozícióra való érkezés előtti méretre állítja.
     * Ha sikerült a továbbítás, akkor true értékkel tér vissza.
     */
    public boolean placeBoxToNextField(Direction d){
            if(neighbors[d.ordinal()].acceptBox(thing)){
                if(isBase) {
                    maze.setEmptyBaseCount(1);
                    thing.getView().setScaleY(1);
                    thing.getView().setScaleX(1);
                    thing = new Base();
                }
                else
                    thing = new Space();
                return true;
            }
        return false;
    }

    /**
     * A Field megpróbál kölcsönös hivatkozást beállítani a paraméterként átvett Thinggel.
     * A rajta lévő Thinggel ütközteti a paraméterként átvett Thingen hívott
     * collideWith(Thing t) metódus használatával.
     * Ha a collideWith(Thing t) trueval tér vissza, akkor true értékkel tér vissza a metódus,
     * de előtte még, ha a Field bázis pozíció akkor a fogadott Thing méretét
     * lecsökkenti az érkezés előtti méretének 80%-ra.
     */
    public boolean acceptBox(Thing t){
        if(!t.collideWith(thing))
            return false;

        thing = t;
        t.setField(this);
        this.getChildren().add(t.getView());
        if(isBase) {
            maze.setEmptyBaseCount(-1);
            t.getView().setScaleY(0.8);
            t.getView().setScaleX(0.8);
        }
        return true;
    }

    /**
     * A Field megpróbálja a direction irányban lévő szomszédjára helyezni a rajta lévő Thinget.
     * Amennyiben bázis pozíció, úgy sikeres Thing továbbítás esetén a Thing méretét a bázis
     * pozícióra való érkezés előtti méretre állítja.
     * Ha sikerült a továbbítás, akkor true értékkel tér vissza.
     */
    public boolean placePlayerToNextField(Direction d){
        if(neighbors[d.ordinal()].acceptPlayer(thing)){
            if(isBase) {
                thing.getView().setScaleY(1);
                thing.getView().setScaleX(1);
            }
            thing = isBase ? new Base() : new Space();
            return true;
        }
        return false;
    }

    /**
     ** A Field megpróbál kölcsönös hivatkozást beállítani a paraméterként átvett Thinggel.
     ** A rajta lévő Thinggel ütközteti a paraméterként átvett Thingen hívott
     ** collideWith(Thing t) metódus használatával.
     ** Ha a collideWith(Thing t) trueval tér vissza, akkor true értékkel tér vissza a metódus,
     ** de előtte még, ha a Field bázis pozíció akkor a fogadott Thing méretét
     ** lecsökkenti az érkezés előtti méretének 80%-ra.
     */
    public boolean acceptPlayer(Thing t){
        if(!t.collideWith(thing))
            return false;

        this.getChildren().add(t.getView());

        thing = t;
        t.setField(this);

        if(isBase) {
            t.getView().setScaleY(0.8);
            t.getView().setScaleX(0.8);
        }
        return true;
    }
}
