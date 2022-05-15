package org.example;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SnakeGame extends Pane {

    private Circle[] snake = new Circle[3];
    private int snakeSize = 3;
    public int score = 0;


    private int snakeXDir = -1;// -1 Left , 1 - Right
    private int snakeYDir = 0;// -1 Down , 1 - Up

    private double timeToMove = 0.35;

    private Circle point = pointsSummoner();
    private Label scoreLabel = new Label("Score :  0");


    public SnakeGame(MenuBox menuBox, SubMenu gameEndMenu, SubMenu mainMenu, MenuLabel scoreText) {
        super();

        snake[0] = new Circle(25, Color.CRIMSON);
        snake[0].setCenterX(300);
        snake[0].setCenterY(300);
        getChildren().add(snake[0]);

        for (int i = 1; i < snakeSize; i++) {
            snake[i] = new Circle(25, Color.GREEN);
            snake[i].setCenterX(300 + i * 50);
            snake[i].setCenterY(300);
            getChildren().add(snake[i]);
        }

        Borders borders = new Borders(0,0,600,600 );
        getChildren().addAll(borders);

        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD,14));
        scoreLabel.setPrefSize(200, 25);
        scoreLabel.setLayoutX(15);
        scoreLabel.setLayoutY(0);
        getChildren().add(scoreLabel);

        getChildren().add(point);

        AnimationTimer timer = new AnimationTimer() {
            double time = timeToMove;

            @Override
            public void handle(long l) {
                time -= 0.01;

                if(snake[0].getCenterX() == point.getCenterX() &&
                    snake[0].getCenterY() == point.getCenterY()){
                    pointAdding();
                }

                if (endGame()) {
                    mainMenu.setVisible(true);
                    menuBox.setSubMenu(gameEndMenu);
                    scoreText.changeText("Score : " +  score);
                    stop();
                }

                if (time < 0) {
                    time = timeToMove;
                    snakeMove();
                    start();
                }
            }
        };
        timer.start();


        setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.UP) {
                snakeXDir = 0;
                snakeYDir = 1;
            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                snakeXDir = 1;
                snakeYDir = 0;
            } else if (keyEvent.getCode() == KeyCode.LEFT) {
                snakeXDir = -1;
                snakeYDir = 0;
            } else if (keyEvent.getCode() == KeyCode.DOWN) {
                snakeXDir = 0;
                snakeYDir = -1;
            }
        });

        setFocusTraversable(true);
        setFocused(true);
    }

    public void snakeMove() {
        for (int i = snake.length - 1; i > 0; i--) {
            snake[i].setCenterX(snake[i - 1].getCenterX());
            snake[i].setCenterY(snake[i - 1].getCenterY());
        }
        snake[0].setCenterX(snake[0].getCenterX() + 50 * snakeXDir);
        snake[0].setCenterY(snake[0].getCenterY() - 50 * snakeYDir);
    }

    public void pointAdding(){
        getChildren().remove(point);
        point = pointsSummoner();
        getChildren().add(point);

        score += 10;
        scoreLabel.setText("Score : " + score);

        addSnakePart();
    }

    public void addSnakePart(){
        Circle[] buff = new Circle[snake.length+1];
        for (int i = 0; i < snake.length; i++) {
            buff[i] = snake[i];
        }

        snake = buff;

        snake[snake.length-1] = new Circle(25, Color.GREEN);

        snake[snake.length-1].setCenterX(700);
        snake[snake.length-1].setCenterY(300);
        getChildren().add(snake[snake.length-1]);

        snakeSize = snakeSize+1;
    }

    public Circle pointsSummoner(){
        int[] nums = new int[11];
        for (int i = 1; i < 12; i++) {
            nums[i-1] = i*50;
        }

        try {
            int x = nums[(int)(Math.random()*11)];
            int y = nums[(int)(Math.random()*11)];
            while(!summonChecker(x, y)){
                x = nums[(int)(Math.random()*11)];
                y = nums[(int)(Math.random()*11)];
            }

            Circle point = new Circle (x, y,
                    25, Color.GOLD);


            return point;
        }catch (NullPointerException e){
            Circle point = new Circle ( nums[(int)(Math.random()*11)], nums[(int)(Math.random()*11)],
                    25, Color.GOLD);

            return point;
        }

    }

    public boolean summonChecker(int x, int y){
        for (int i = 0; i < snakeSize; i++) {
            if(snake[i].getCenterX() == x && snake[i].getCenterY() == y)
                return false;
        }
        return true;
    }

    public boolean endGame() {
        if(suicideEnd() || bordersEnd())
            return true;
        return false;
    }

    public boolean suicideEnd(){
        for (int i = 1; i < snake.length; i++) {
            if (snake[0].getCenterX() == snake[i].getCenterX() && snake[0].getCenterY() == snake[i].getCenterY()) {
                return true;
            }
        }
        return false;
    }

    public boolean bordersEnd(){
       if(snake[0].getCenterX() > 575 || snake[0].getCenterX() < 25 || snake[0].getCenterY() > 575 || snake[0].getCenterY() < 25)
           return true;
       return false;
    }

}
