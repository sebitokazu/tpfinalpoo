package game.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {

	private static final String SCORE = "SCORE: ", MOVES = "MOVES LEFT: ";

	private Label scoreLabel, movesLabel, infoLabel;
	private String infoText;

	public ScorePanel() {
		setStyle("-fx-background-color: #5490ff");
		scoreLabel = new Label(SCORE + "0");
		scoreLabel.setAlignment(Pos.CENTER_LEFT);
		scoreLabel.setStyle("-fx-font-size: 18");
		setCenter(scoreLabel);

		movesLabel  = new Label(MOVES);
		movesLabel.setAlignment(Pos.CENTER_RIGHT);
		movesLabel.setStyle("-fx-font-size: 18");
		setRight(movesLabel);

		infoLabel = new Label("");
		infoLabel.setAlignment(Pos.BOTTOM_LEFT);
		infoLabel.setStyle("-fx-font-size: 18");
		setLeft(infoLabel);

	}

	public void setMaxMoves(int maxMoves){
	    movesLabel.setText(MOVES+maxMoves);
    }
    //setea el texto del label de informacion adicional, si el nivel lo requeria
	public void setInfoText(String level, int initialState){
        switch (level){
            case "Level 2":
                infoText = "UNGOLDED: ";
                break;
            case "Level 3":
                infoText = "TO COLLECT: ";
                break;
            default:
                throw new IllegalArgumentException("No such level");
        }
	    infoLabel.setText(infoText + initialState);
    }

    //refresca la informacion adicional del nivel
    public void updateInfo(int info){
            infoLabel.setText(infoText + info);
    }

	public void updateScore(long score) {
		scoreLabel.setText(SCORE + score);
	}

	public void updateMoves(int moves){
		movesLabel.setText(MOVES+ moves);
	}

}