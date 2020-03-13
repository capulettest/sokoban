package com.alex.soko;

public class MapNames {
    String menuLabelText;
    String fileName;

    public MapNames(String menuLabelText, String fileName) {
        this.menuLabelText = menuLabelText;
        this.fileName = fileName;
    }

    /**
    * Visszaadja a pálya menüben való megjelenítésére szolgáló nevét.
    */
    public String getMenuLabelText() {
        return menuLabelText;
    }

    /**
     * Visszaadja a pálya fájlból való betöltéséhez szükséges nevét.
     */
    public String getFileName() {
        return fileName;
    }
}
