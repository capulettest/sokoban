package com.alex.soko;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class SokoMenuLabel extends StackPane {
    private Rectangle bg;
    private Text text;
    private boolean first = true;

    public SokoMenuLabel(String defaultText) {
        text = new Text(defaultText);
        text.setFont(new Font(20));
        text.setFill(Color.WHITE);


        bg = new Rectangle(200, 30);
        bg.setOpacity(0.6);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.5));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);

    }

    public void setText(String newText) {
        Text tmpTExt = new Text(newText);
        tmpTExt.setFont(text.getFont().font(20));
        tmpTExt.setFill(Color.WHITE);
        getChildren().remove(this.text);
        this.text = tmpTExt;
        getChildren().add(tmpTExt);
    }

    public String getText(){
        if(first) {
            first = false;
            return "";
        }

        return text.getText();
    }
}