package org.example;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuLabel extends StackPane {

    Label label = new Label();
    public MenuLabel(String str){
        label.setText(str);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));

        getChildren().add(label);
    }

    public void changeText(String str){
       label.setText(str);
    }

    public int getScore(){
        return Integer.parseInt(label.getText().substring(label.getText().lastIndexOf(' ')+1));
    }
}
