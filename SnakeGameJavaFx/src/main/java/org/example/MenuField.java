package org.example;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class MenuField extends StackPane {
    TextField textField = new TextField();
    public MenuField(){
        textField.setPrefSize(100,50);
        textField.setLayoutX(0);
        textField.setLayoutY(0);

        getChildren().add(textField);
    }

    public String getText(){
        return textField.getText();
    }
}
