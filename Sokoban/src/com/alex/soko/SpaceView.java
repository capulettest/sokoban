package com.alex.soko;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SpaceView extends ThingView {
    SpaceView(){
        Image img = null;
        try(InputStream is = Files.newInputStream(Paths.get("pavement.png"))){
            img = new Image(is);
        } catch (IOException e) {}

        imgView = new ImageView(img);
        imgView.setFitWidth(50);
        imgView.setFitHeight(50);
        int rotationAngle = 90*(int)(Math.random()*5);
        imgView.setRotate(rotationAngle);
        getChildren().add(imgView);
    }
}
