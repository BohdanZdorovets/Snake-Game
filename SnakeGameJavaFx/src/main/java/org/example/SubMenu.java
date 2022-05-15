package org.example;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class SubMenu extends VBox {
    public SubMenu(MenuButton...items){
        setSpacing(15);

        setTranslateY(300);
        setTranslateX(200);
        for(MenuButton item : items){
            getChildren().addAll(item);
        }
    }

    public SubMenu(MenuLabel mainLabel, MenuLabel scoreLabel, MenuField nameField, MenuButton submitButton){

        setSpacing(15);

        setTranslateY(200);
        setTranslateX(200);

        getChildren().addAll(mainLabel, scoreLabel, nameField, submitButton);
    }

    public SubMenu(MenuLabel mainLabel, MenuTable menuTable, MenuButton backButton){


        setSpacing(25);

        setTranslateY(50);
        setTranslateX(200);

        getChildren().addAll(mainLabel);

        setSpacing(25);
        for (int i = 0; i < 10; i++) {
            getChildren().add(menuTable.getLeaderboard()[i]);
        }

        setSpacing(25);

        getChildren().add(backButton);

    }


}
