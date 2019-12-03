package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level;
import game.backend.level.Level1;
import game.backend.level.Level2;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Candy Crush - Itokazu/Rosenblatt");

		Image image = new Image("images/Logo-ITBA-60.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(500*0.40);
		imageView.setFitWidth(1200*0.40);
		imageView.setPreserveRatio(true);

		Level[] levels = {new Level1(), new Level2()};
		ChoiceBox<Level> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(levels));

		Button playButton = new Button("Play!");
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

		VBox vBox = new VBox(imageView,choiceBox, playButton);
		vBox.setSpacing(60);
		vBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vBox, 720,750);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
