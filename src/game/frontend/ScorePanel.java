package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {

	private static final String SCORE = "SCORE: ", MOVES = "MOVES: ";

	private Label scoreLabel, movesLabel;

	public ScorePanel() {
		setStyle("-fx-background-color: #5490ff");
		scoreLabel = new Label(SCORE + "0");
		scoreLabel.setAlignment(Pos.CENTER_LEFT);
		scoreLabel.setStyle("-fx-font-size: 24");
		setCenter(scoreLabel);

		movesLabel  = new Label(MOVES+"0");
		movesLabel.setAlignment(Pos.CENTER_RIGHT);
		movesLabel.setStyle("-fx-font-size: 24");
		setRight(movesLabel);
	}
	
	public void updateScore(long score) {
		scoreLabel.setText(SCORE + score);
	}

	public void updateMoves(int moves){
		movesLabel.setText(MOVES+ moves);
	}

}