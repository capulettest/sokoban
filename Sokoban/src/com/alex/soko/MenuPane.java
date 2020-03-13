package com.alex.soko;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class MenuPane extends Pane {
    private int menuMapNumber, acquiredMapNumber;
    private boolean isResumableByButton, startable, validUserName, userActivated, userLoaded;
    private Map<Integer, MapNames> mapNames;
    private SokoMenuLabel nameLabel, mapLabel;
    private SokoMenuButton resumeButton, incMapNumber, decMapNumber, startButton, setNameButton, exitButton;
    private VBox menuOptions;

    MenuPane(){
        userLoaded = false;
        userActivated = false;
        isResumableByButton = false;
        startable = false;
        validUserName = false;
        menuMapNumber = 1;
        acquiredMapNumber = 1;
        mapNames = Map.of(
                1, new MapNames("First map", "map1.txt"),
                2, new MapNames("Second map", "map2.txt"),
                3, new MapNames("Third map", "map3.txt")
        );

        menuOptions = new VBox();

        resumeButton = new SokoMenuButton("Resume game");
        resumeButton.setOnMouseClicked(event -> isResumableByButton = true);

        mapLabel = new SokoMenuLabel("First map");

        incMapNumber = new SokoMenuButton("Next map");
        incMapNumber.setOnMouseClicked(event -> {
            if(menuMapNumber < acquiredMapNumber)
                menuMapNumber++;
            else if(menuMapNumber == acquiredMapNumber)
                menuMapNumber = 1;
            mapLabel.setText(mapNames.get(menuMapNumber).getMenuLabelText());
        });

        decMapNumber = new SokoMenuButton("Previous map");
        decMapNumber.setOnMouseClicked(event -> {
            if(menuMapNumber > 1)
                menuMapNumber--;
            else if(menuMapNumber == 1)
                menuMapNumber = acquiredMapNumber;
            mapLabel.setText(mapNames.get(menuMapNumber).getMenuLabelText());
        });

        startButton = new SokoMenuButton("Start map");
        startButton.setOnMouseClicked(event -> {
            if(validUserName && userActivated)
                startable = true;
        });

        nameLabel = new SokoMenuLabel("Type player name");

        setNameButton = new SokoMenuButton("Set player name");
        setNameButton.setOnMouseClicked(event -> {
            if(!userLoaded)
                userLoaded = true;
        });

        exitButton = new SokoMenuButton("Exit");
        exitButton.setOnMouseClicked(vent -> System.exit(0));

        menuOptions.getChildren().addAll(nameLabel, setNameButton, mapLabel, incMapNumber, decMapNumber,
                                         startButton, resumeButton, exitButton);
        getChildren().add(menuOptions);
    }


    /**
     * Igazzal tér vissza, ha a user megnyomta a setNameButtont.
     */
    public boolean isUserLoaded() {
        return userLoaded;
    }

    /**
     ** Beállítja a setNameButtont lenyomását jelző flaget.
     ** A gomb lenyámása után a flag false értékű rezetelésére szolgál.
     */
    public void setUserLoaded(boolean userLoaded) {
        this.userLoaded = userLoaded;
    }

    /**
     ** Amennyiben a felhasználó leütött egy billentyűt
     ** ez a metódus írja be a nameLabelbe a karaktert,
     ** amennyiben a név még nem érte el a 6 karakter hosszúságot.
     */
    public void characterInput(String character){
        String tempText = nameLabel.getText() + character;
        if(tempText.length() < 7) {
            nameLabel.setText(tempText);
            validUserName = true;
        }
    }

    /**
     ** Amennyiben a felhasználó leütötte a backspace billentyűt
     ** ez a metódus törli ki a nameLabelből a karaktert,
     ** amennyiben a név tartalmaz karaktert.
     */
    public void deleteLastcharacterInput(){
        if(nameLabel.getText().length() != 0)
        nameLabel.setText(nameLabel.getText().substring(0, nameLabel.getText().length() - 1));
        else
            validUserName = false;
    }

    /**
     ** A felhasználó által legutoljára feloldott pálya,
     ** sorszámát állítja be a paraméterként átvett értékre.
     */
    public void setAcquiredMapNumber(int n){
        acquiredMapNumber = n;
        menuMapNumber = acquiredMapNumber;
        mapLabel.setText(mapNames.get(acquiredMapNumber).getMenuLabelText());
    }

    /**
     ** Visszaadja a felhasználó által legutoljára feloldott
     ** pálya fájl nevét, annak fájlból való betöltésének szolgálatához.
     */
    public String getFileNameOfAcquiredMap(){
        return mapNames.get(acquiredMapNumber).getFileName();
    }

    /**
     ** Visszaadja a felhasználó által a menüben kiválasztott
     ** pálya fájl nevét, annak fájlból való betöltésének szolgálatához.
     */
    public String getFileNameOfMenuMapNumber(){
        return mapNames.get(menuMapNumber).getFileName();
    }

    /**
     ** A felhasználó a nameLabelbe beírt nevet adja vissza.
     */
    public String getNameText(){
        return nameLabel.getText();
    }

    /**
     * Visszaadja, hogy el lehet-e indítani az épp kiválasztott pályát.
    */
    public boolean isStartable() {
        return startable;
    }

    /**
     * Beállítja, hogy el lehet-e indítani az épp kiválasztott pályát.
     */
    public void setStartable(boolean startable) {
        this.startable = startable;
    }

    /**
     ** Visszaadja, hogy a menüben található resumeButton hatására
     ** visszatérhetünk-e a félbeszakított pályára.
     */
    public boolean isResumableByButton() {
        return isResumableByButton;
    }

    /**
     ** Beállítja, hogy a menüben található resumeButton hatására
     ** visszatérhetünk-e a félbeszakított pályára.
     */
    public void setResumableByButton(boolean resumable) {
        this.isResumableByButton = resumable;
    }

    /**
     ** Visszaadja, hogy a felhasználó által beírt felhasználó név
     ** megfelelő-e a játék indításához.
     */
    public boolean isValidUserName() {
        return validUserName;
    }

    /**
     ** Betölti a a felhasználó által beírt felhasználó névhez
     ** tartozó eddig elért eredményeket.
     */
    public void setUserActivated(boolean userActivated) {
        this.userActivated = userActivated;
    }
}
