package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Borders extends Canvas {
    private GraphicsContext gc;

    public Borders(int x, int y, int width, int height) {
        super(width, height);

        setLayoutX(x);
        setLayoutY(y);

        gc = getGraphicsContext2D();

        gc.setFill(Color.DIMGRAY);
        gc.fillRect(0, 0, 600, 25);
        gc.fillRect(575, 0, 25, 600);
        gc.fillRect(0, 575, 600, 25);
        gc.fillRect(0, 0, 25, 600);


    }
}
