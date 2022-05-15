package org.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *JavaFX App
 */
public class App extends Application {

    String name;
    int score;

    @Override
    public void start(Stage stage) throws IOException {

        Pane pane = new Pane();


        //Main menu objects
        MenuButton newGame = new MenuButton("NEW GAME");
        MenuButton leaderboard = new MenuButton("LEADERBOARD");
        MenuButton exitGame = new MenuButton("EXIT");

        //Game end menu objects
        MenuField nameField = new MenuField();
        MenuLabel scoreText = new MenuLabel( "Score : " );
        MenuButton submitName = new MenuButton("Submit");
        MenuLabel mainText = new MenuLabel( "Good Game");

        //Leaderboards menu objects
        MenuTable leaderboardsTable = new MenuTable();
        MenuLabel mainLabel = new MenuLabel("LEADERBOARDS");
        MenuButton backButton = new MenuButton("Back");

        SubMenu gameEndMenu = new SubMenu(

           mainText, scoreText, nameField, submitName
        );

        SubMenu mainMenu = new SubMenu(
                newGame,leaderboard,exitGame
        );

        SubMenu leaderboardsMenu = new SubMenu(
                mainLabel, leaderboardsTable, backButton
        );

        //Setting the start menu
        MenuBox menuBox = new MenuBox(mainMenu);

        newGame.setOnMouseClicked(event->{
            mainMenu.setVisible(false);

            //Creating new game
            SnakeGame snakeGame = new SnakeGame(menuBox, gameEndMenu, mainMenu, scoreText);
            pane.getChildren().add(snakeGame);

            AnimationTimer timer = new AnimationTimer() {
                double time = 0.35;

                @Override
                public void handle(long l) {
                    time -= 0.01;

                    if (time < 0) {
                        if (snakeGame.endGame()) {
                            pane.getChildren().remove(snakeGame);
                            stop();
                    }
                        time = 0.5;
                        start();
                    }
                }
            };
            timer.start();
        });


        leaderboard.setOnMouseClicked(event->{
            leaderboardsTable.showBoard();
            menuBox.setSubMenu(leaderboardsMenu);
        });

        exitGame.setOnMouseClicked(event->  System.exit(0));

        submitName.setOnMouseClicked(event ->{ menuBox.setSubMenu(mainMenu);
        //Getting name from the textField and score from the label
          name = nameField.getText();
          score = scoreText.getScore();

          if(score == 1210)
              mainText.changeText("YOU WON!!!");

        //Changes arrayList and file if need
            try {
                leaderboardsTable.updateScore(score, name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));

        pane.getChildren().addAll(menuBox);

        var scene = new Scene(pane, 600, 600);

        menuBox.setVisible(true);

        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }

}