package com.alex.soko;

public class Maze {
    private boolean complete;
    private Field[][] matrix;
    private int mapNumber, mapWidth, mapHeight, baseCount;

    public Maze(int mapNumber, int y, int x, int bc) {
        complete = false;
        this.mapNumber = mapNumber;
        matrix = new Field[y][x];
        mapHeight = y;
        mapWidth = x;
        baseCount = bc;
    }

    /**
     * Visszaadja a pálya blokkokban mért magasságát.
    */
    public int getHeight(){
        return mapHeight;
    }

    /**
     * Visszaadja a pálya blokkokban mért szélességét.
    */
    public int getWidth(){
        return mapWidth;
    }

    /**
     * Visszaadja a pálya sorszámát.
    */
    public int getMapNumber() {
        return mapNumber;
    }

    /**
     * Visszaadja hogy a pálya teljesítve van-e, azaz hogy
     * minden bázis pozíción áll-e doboz.
    */
    public boolean isComplete() {
        return complete;
    }

    /**
     ** Módosítja a paraméterként átvett értékkel
     ** a bázis pozíciókon található dobozok számát.
     ** Amikor nullára csökken, akkor beállítja a pálya
     ** teljesítését jelző "complete: boolean" flaget igazra.
    */
    public void setEmptyBaseCount(int delta){
        if((baseCount += delta) == 0)
            complete = true;

    }

    /**
     ** Szomszédos Fieldekre való referenciák szolgáltatásával
     ** beállítja a pályán az összes Field, Field szomszédját.
    */
    public void setNeighbors() {
        for (int h = 0; h < mapHeight; h++)
            for (int w = 0; w < mapWidth; w++) {
                Field up = (h == 0) ? null : matrix[h-1][w];
                Field left = (w == 0) ? null : matrix[h][w-1];
                Field right = (w == (mapWidth-1)) ? null : matrix[h][w+1];
                Field down = (h == (mapHeight -1)) ? null : matrix[h+1][w];
                matrix[h][w].setNeighbors(up, left, right, down);
            }
    }

    /**
     ** A Fieldreferencia mátrixban, az adott indexű referenciára
     ** Field objektumot állít.
     */
    public void setFieldAt(int h, int w, Field f) throws NullPointerException{
            matrix[h][w] = f;
    }

}