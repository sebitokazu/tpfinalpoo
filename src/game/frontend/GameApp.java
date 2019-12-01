package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level;
import game.backend.level.Level1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application {
	private Level levelSelected;

	public static void main(String[] args) {
		launch(args);
	}
	public GameApp(Level levelSelected){
		this.levelSelected = levelSelected;
	}
	@Override
	public void start(Stage primaryStage) {
		CandyGame game = new CandyGame(levelSelected);
		CandyFrame frame = new CandyFrame(game);
		Scene scene = new Scene(frame);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
