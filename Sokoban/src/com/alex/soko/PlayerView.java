package com.alex.soko;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlayerView extends ThingView {
    PlayerView(){

        Image img = null;
        try(InputStream is = Files.newInputStream(Paths.get("playerViewimg.png"))){
            img = new Image(is);
        } catch (IOException e) {}

        imgView = new ImageView(img);
        imgView.setFitWidth(50);
        imgView.setFitHeight(50);
        getChildren().add(imgView);
    }
}
