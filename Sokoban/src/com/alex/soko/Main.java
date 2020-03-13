package com.alex.soko;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int BLOCK_HEIGHT = 50, BLOCK_WIDTH = 50, HEADER_HIGHT = 35, MAP_COUNT = 2;
    public static Player player = new Player();
    public static Group thingsGroup = new Group();
    private static Maze maze;
    private Parent gamePane = null;
    private static int currentMapWidth, currentMapHeight;
    private static Stage stageInUse;
    private static UserList userList;
    private static User actualUser;
    private static MenuPane menuPane;

    /**
     * Az applikáció belépő pontja.
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * Beállítja az ablak méretét az aktuális pálya méretére.
     */
    private void setStageSizeByGamePaneSize(){
        stageInUse.setWidth(currentMapWidth);
        stageInUse.setHeight(currentMapHeight+ HEADER_HIGHT);
    }

    /**
     * Beállítja az ablak méretét a menü méretére.
     */
    private void setStageSizeByMenuPaneSize(){
        stageInUse.setWidth(200);
        stageInUse.setHeight(240+ HEADER_HIGHT);
    }

    /**
     * A kiválasztott pálya felületét hozzá létre és adja vissza.
     */
    private Pane createGameContent(String mapName) throws NullPointerException {
        thingsGroup.getChildren().removeAll();
        maze = MazeLoader.loadMazeFromFile(mapName);
        Pane gamePane = new Pane();
        currentMapWidth = maze.getWidth()*BLOCK_WIDTH;
        currentMapHeight = maze.getHeight()*BLOCK_HEIGHT;
        gamePane.setPrefSize(currentMapHeight, currentMapWidth);
        setStageSizeByGamePaneSize();
        gamePane.getChildren().addAll(thingsGroup.getChildren());
        return gamePane;
    }

    /**
     * A játében lévő menü felületét hozzá létre és adja vissza.
     */
    private MenuPane createMenuContent() throws NullPointerException {
        return new MenuPane();
    }

    /**
     ** A applikáció kezdő megjelenítését inicializálja.
     ** A felhasználó az ebben a metódosban megtalálható
     ** setOnMouseClicked és setOnKeyPressed metódusokkal
     ** tud instrukciókat kiadni a programnak, amely instrukciók
     ** az alábbiak lehetnek.
     ** Név megadása és adott névhez elmentett játékállás betöltése.
     ** Pályák választása.
     ** Pályára való visszalépés pályáról való kilépés után.
     ** Pálya újraindítása.
     ** Játékos modelljének mozgatása.
     ** Továbbá egy pálya teljesítése után automatikusan beolvassa
     ** a MazeLoader.loadMazeFromFile(mapName) statikus metódus
     ** segítségével a soron következő pályát, az utolsó után pedig
     ** visszalép a főmenübe.
     */
    @Override
    public void start(Stage primaryStage) {
        userList = UserLoader.loadUsersFromFile();

        User defaultUser = new User("Default User", 1);
        actualUser = defaultUser;

        stageInUse = primaryStage;
        stageInUse.setTitle("Soko");
        stageInUse.setResizable(false);
        setStageSizeByMenuPaneSize();


        menuPane = createMenuContent();
        Scene sceneApp = new Scene(menuPane);

        stageInUse.setScene(sceneApp);
        stageInUse.show();


        stageInUse.getScene().setOnMouseClicked(event -> {
            if(menuPane.isStartable()){
                menuPane.setStartable(false);
                gamePane = createGameContent(menuPane.getFileNameOfMenuMapNumber());
                sceneApp.setRoot(gamePane);
            }
            if(menuPane.isResumableByButton() && gamePane != null){
                menuPane.setResumableByButton(false);
                setStageSizeByGamePaneSize();
                sceneApp.setRoot(gamePane);
            }
            if(menuPane.isUserLoaded()){
                menuPane.setUserActivated(true);
                actualUser = userList.getUser(menuPane.getNameText());
                menuPane.setAcquiredMapNumber(actualUser.getAcquiredLevelNumber());

                menuPane.setUserLoaded(false);
            }
        });

        stageInUse.getScene().setOnKeyPressed(event -> {
            if(sceneApp.getRoot() == menuPane && event.getCode() != KeyCode.ESCAPE) {
                if(event.getCode() == KeyCode.BACK_SPACE) {
                    menuPane.deleteLastcharacterInput();
                    menuPane.setUserActivated(false);
                    return;
                }
                else if(event.getCode() == KeyCode.ENTER && menuPane.isValidUserName()) {
                    menuPane.setUserActivated(true);
                    actualUser = userList.getUser(menuPane.getNameText());
                    menuPane.setAcquiredMapNumber(actualUser.getAcquiredLevelNumber());
                    return;
                }
                else {
                    menuPane.characterInput(event.getText());
                    menuPane.setUserActivated(false);
                    return;
                }
            }
            else
                switch (event.getCode()) {
                    case ESCAPE:
                        if (stageInUse.getScene().getRoot() == menuPane) {
                            sceneApp.setRoot(gamePane);
                            setStageSizeByGamePaneSize();
                        }
                        else {
                            sceneApp.setRoot(menuPane);
                            setStageSizeByMenuPaneSize();
                        }
                        break;
                    case W:
                        player.move(Direction.UP);
                        break;
                    case S:
                        player.move(Direction.DOWN);
                        break;
                    case A:
                        player.move(Direction.LEFT);
                        break;
                    case D:
                        player.move(Direction.RIGHT);
                        break;
                    case R:
                        gamePane = createGameContent(menuPane.getFileNameOfAcquiredMap());
                        sceneApp.setRoot(gamePane);
                        break;
                    default:
                        break;
                }
                if(maze.isComplete()){
                    boolean allMapsCompleted = false;
                    if(MAP_COUNT == maze.getMapNumber())
                        allMapsCompleted = true;

                    int newAcquiredLevelNumber = MAP_COUNT == maze.getMapNumber() ? MAP_COUNT : maze.getMapNumber()+1;
                    actualUser.setAcquiredLevelNumber(newAcquiredLevelNumber);

                    UserLoader.saveUsersToFile(userList);
                    menuPane.setAcquiredMapNumber(newAcquiredLevelNumber);

                    if(allMapsCompleted) {
                        sceneApp.setRoot(menuPane);
                        setStageSizeByMenuPaneSize();
                    }
                    else {
                        gamePane = createGameContent(menuPane.getFileNameOfAcquiredMap());
                        sceneApp.setRoot(gamePane);
                    }
            }
        });
    }
}
