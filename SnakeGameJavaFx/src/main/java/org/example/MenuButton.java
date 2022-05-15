package org.example;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuButton extends StackPane {

    public MenuButton(String name) {
        Rectangle bg = new Rectangle(200, 20, Color.GREEN);
        bg.setOpacity(0.5);
        bg.setLayoutX(0);
        bg.setLayoutY(0);

        Text text = new Text(name);
        text.setFill(Color.DARKGREEN);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);

        //Color animation
        FillTransition st = new FillTransition(Duration.seconds(0.5), bg);
        setOnMouseEntered(event -> {
            st.setFromValue(Color.DARKGRAY);
            st.setToValue(Color.DARKGOLDENROD);
            st.setCycleCount(Animation.INDEFINITE);
            st.setAutoReverse(true);
            st.play();
        });

        setOnMouseExited(event -> {
            st.stop();
            bg.setFill(Color.GREEN);
        });
    }
}
