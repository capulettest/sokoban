package com.alex.soko;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MazeLoader {

    /**
     ** A paraméterként átvett fájl nevű fájlból tölt be pályát
     ** a visszatérési értékében lévő Maze típusú objektumba.
    */
    public static Maze loadMazeFromFile(String mapName) {
        Path filePath = Paths.get(mapName);
        Maze maze;
        try(Scanner scanner = new Scanner(filePath)){

            int MapWidth = Integer.valueOf(scanner.nextLine());
            int MapHeight = Integer.valueOf(scanner.nextLine());
            int baseCount = Integer.valueOf(scanner.nextLine());
            int mapLevel = Integer.valueOf(scanner.nextLine());

            maze = new Maze(mapLevel, MapHeight, MapWidth, baseCount);

            String line;
            int h = 0;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                for(int w = 0; w < MapWidth; w++) {
                    Field f = null;
                    switch(line.charAt(w)){
                        case('.'):
                            f = new Field(maze, new Space(), false, new SpaceView()/*new Space().getView()*/, false);
                            maze.setFieldAt(h, w, f);
                            break;
                        case('X'):
                            f = new Field(maze, Main.player, true, new SpaceView()/*new Space().getView()*/, false);
                            maze.setFieldAt(h, w, f);
                            break;
                        case('#'):
                            f = new Field(maze, new Wall(), false, new Wall().getView(), false);
                            maze.setFieldAt(h, w, f);
                            break;
                        case('$'):
                            f = new Field(maze, new Box(), true, new SpaceView()/*new Space().getView()*/, false);
                            maze.setFieldAt(h, w, f);
                            break;
                        case('0'):
                            f = new Field(maze, new Base(), false, new BaseView()/*new Base().getView()*/, true);
                            maze.setFieldAt(h, w, f);
                            break;
                    }
                    f.setTranslateX(w*50);
                    f.setTranslateY(h*50);
                    Main.thingsGroup.getChildren().add(f);
                }
                h++;
            }
            maze.setNeighbors();
            return maze;
        } catch(IOException e) {System.out.println(e);}
        return null;
    }
}
