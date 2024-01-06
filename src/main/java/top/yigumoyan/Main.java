package top.yigumoyan;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    private boolean isDrawing = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        vBox.setSpacing(Constant.SPACING);
        vBox.setPadding(new Insets(Constant.PADDING));

        Canvas canvas = new Canvas(Constant.PANE_WIDTH, Constant.PANE_HEIGHT);

        Button buttonUP = new Button(Constant.UP);
        Button buttonDown = new Button(Constant.DOWN);
        Button buttonClear = new Button(Constant.CLEAR);

        HBox hBoxButton = new HBox();
        hBoxButton.getChildren().addAll(buttonUP, buttonDown, buttonClear);
        hBoxButton.setSpacing(Constant.SPACING);

        Label label = new Label(String.format("%s%s", Constant.CURRENT_STATE, Constant.UP));

        vBox.getChildren().addAll(canvas, hBoxButton, label);

        Scene scene = new Scene(vBox, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

        canvas.setOnMouseDragged(event -> {
            if (isDrawing) {
                canvas.getGraphicsContext2D().lineTo(event.getX(), event.getY());
                canvas.getGraphicsContext2D().stroke();
            }
        });

        canvas.setOnMousePressed(event -> {
            canvas.getGraphicsContext2D().beginPath();
        });

        canvas.setOnMouseReleased(event -> {
            canvas.getGraphicsContext2D().closePath();
        });

        buttonClear.setOnAction(event -> {
            canvas.getGraphicsContext2D().clearRect(0, 0, Constant.PANE_WIDTH, Constant.PANE_HEIGHT);
        });

        buttonUP.setOnAction(event -> {
            isDrawing = false;
            label.setText(String.format("%s%s", Constant.CURRENT_STATE, Constant.UP));
        });

        buttonDown.setOnAction(event -> {
            isDrawing = true;
            label.setText(String.format("%s%s", Constant.CURRENT_STATE, Constant.DOWN));
        });
    }
}