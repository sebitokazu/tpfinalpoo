package game.frontend;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Candy Crush - Itokazu/Rosenblatt");

        Image image = new Image("images/Logo-ITBA-60.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(500*0.40);
        imageView.setFitWidth(1200*0.40);
        imageView.setPreserveRatio(true);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add("Level 1");
        choiceBox.getItems().add("Level 2");
        choiceBox.getItems().add("Level 3");

        Button playButton = new Button("Play!");
        playButton.setOnAction(actionEvent->{
            GameApp gameApp = new GameApp();
            gameApp.start(new Stage());
            primaryStage.close();
        });

        VBox vBox = new VBox(imageView,choiceBox, playButton);
        vBox.setSpacing(60);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 720,750);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
