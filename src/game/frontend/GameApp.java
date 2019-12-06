package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Candy Crush TPE - Itokazu/Rosenblatt");

		Level[] levels = {new Level1(), new Level2(), new Level3()};
		ChoiceBox<Level> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(levels));

		Image buttonImage = new Image("images/playButton.png");
		ImageView buttonImageView = new ImageView(buttonImage);
		buttonImageView.setFitHeight(245*0.2);
		buttonImageView.setFitWidth(603*0.20);

		Button playButton = new Button("", buttonImageView);
		playButton.setOnAction(actionEvent->{
			if(choiceBox.getValue()!= null) {
				Stage gameStage = new Stage();
				CandyGame game = new CandyGame(choiceBox.getValue());
				CandyFrame frame = new CandyFrame(game);
				Scene scene = new Scene(frame);
				gameStage.setResizable(false);
				gameStage.setScene(scene);
				gameStage.show();
				primaryStage.close();
			}
		});

		Image background = new Image("images/backgroundImage.png");
		BackgroundSize size = new BackgroundSize(720,750,false,false,false,true);
		BackgroundImage backgroundimage = new BackgroundImage(background,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				size);

		VBox vBox = new VBox(choiceBox, playButton);
		vBox.setSpacing(60);
		vBox.setAlignment(Pos.CENTER);
		vBox.setBackground(new Background(backgroundimage));

		Scene scene = new Scene(vBox, 720,750);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
