package com.alex.soko;

public class Player extends Thing{
    private Direction direction;

    Player(){
        view = new PlayerView();
    }

    /**
     ** A paraméterben megadott irányban lévő szomszédos Fildre
     ** próbálja mozgatni a Player objektumot, valamint az adott
     ** irányba forgatja a megjelenítésért felelős kinézetét.
     */
    public void move(Direction d){
        direction = d;

        field.placePlayerToNextField(d);

            double rotationAngle;

            switch (direction) {
                case UP:
                    rotationAngle = 0;
                    break;
                case RIGHT:
                    rotationAngle = 90;
                    break;
                case DOWN:
                    rotationAngle = 180;
                    break;
                case LEFT:
                    rotationAngle = -90;
                    break;
                default:
                    rotationAngle = 0;
                    break;
            }
            view.setRotate(rotationAngle);
    }

    @Override
    public boolean collideWith(Thing t){
        if(t.hitByPlayer(direction))
            return true;
        return false;
    }
}
