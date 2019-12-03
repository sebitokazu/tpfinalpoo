package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;
	private ImageManager images;
	private Point2D lastPoint;
	private CandyGame game;

	public CandyFrame(CandyGame game) {
		this.game = game;
        System.out.println(game.getLevel());
		getChildren().add(new AppMenu());
		images = new ImageManager();
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		getChildren().add(boardPanel);
		scorePanel = new ScorePanel();
		getChildren().add(scorePanel);
		game.initGame();
		GameListener listener;
		game.addGameListener(listener = new GameListener() {
							@Override
							public void gridUpdated() {
								Timeline timeLine = new Timeline();
								Duration frameGap = Duration.millis(100);
								Duration frameTime = Duration.ZERO;
								for (int i = game().getSize() - 1; i >= 0; i--) {
									for (int j = game().getSize() - 1; j >= 0; j--) {
										int finalI = i;
										int finalJ = j;
										Cell cell = CandyFrame.this.game.get(i, j);
										Element element = cell.getContent();
										Image image = images.getImage(element);
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImageNull(finalI, finalJ)));
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImageLevel(finalI, finalJ, image, cell.hasFunctionality(), game.getLevel())));
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();

        }
			@Override
			public void cellExplosion(Element e) {
				//
			}
		});

		listener.gridUpdated();

		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (lastPoint == null) {
				lastPoint = translateCoords(event.getX(), event.getY());
				System.out.println("Get first = " +  lastPoint);
			} else {
				Point2D newPoint = translateCoords(event.getX(), event.getY());
				if (newPoint != null) {
					System.out.println("Get second = " +  newPoint);
					game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY());
					long score = game().getScore();
					if (game().isFinished()) {
					    String message = ((Long)score).toString();
						if (game().playerWon()) {
							message = message + " Finished - Player Won!";
						} else {
							message = message + " Finished - Loser !";
						}
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
						alert.setTitle("End of game!");
						alert.setHeaderText(((Long)score).toString());
						alert.setContentText("Start a new game?");

                        ButtonType backToMainButton = new ButtonType("Back to main menu", ButtonBar.ButtonData.BACK_PREVIOUS);;
                        ButtonType exitButton = new ButtonType("Close game", ButtonBar.ButtonData.FINISH);
                        alert.getButtonTypes().setAll(backToMainButton,exitButton);
                        Optional<ButtonType> result = alert.showAndWait();

                        if(result.get() == backToMainButton){
                            Stage currentStage = (Stage) getScene().getWindow();
                            GameApp gameApp = new GameApp();
                            gameApp.start(new Stage());
                            currentStage.close();
                        } else {
                            Platform.exit();
                        }
					}
					scorePanel.updateScore(score);
					scorePanel.updateMoves(game().getMoves());
					lastPoint = null;
				}
			}
		});

	}

	private CandyGame game() {
		return game;
	}

	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		double j = y / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}

}
