package game.frontend;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class BoardPanel extends TilePane {

	private ImageView[][] cells;

	public BoardPanel(final int rows, final int columns, final int cellSize) {
		setPrefRows(rows);
		setPrefColumns(columns);
		setPrefTileHeight(cellSize);
		setPrefTileWidth(cellSize);
		this.cells = new ImageView[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new ImageView();
				getChildren().add(cells[i][j]);
			}
		}
	}

	public void setImageLevel(int row, int column, Image image, boolean hasFunctionality, String level){
	    switch (level){
            case "Level 1":
                setImage(row, column, image);
                break;
            case "Level 2":
                setImageLevel2(row,column,image, hasFunctionality);
                break;
			case "Level 3":
				setImage(row,column,image);
				break;
            default:
                throw new IllegalArgumentException("No such level");
        }
    }

    public void setImageNull(int row, int column){
        cells[row][column].setImage(null);
    }
	
	private void setImage(int row, int column, Image image) {
		cells[row][column].setImage(image);

	}

	private void setImageLevel2(int row, int column, Image image, boolean isGolden){
		setImage(row,column,image);
		if(isGolden) {
			Light.Distant spotLight = new Light.Distant();
			spotLight.setColor(Color.YELLOW);
			spotLight.setElevation(100);
			Lighting lighting = new Lighting(spotLight);
			cells[row][column].setEffect(lighting);
		}
	}

}