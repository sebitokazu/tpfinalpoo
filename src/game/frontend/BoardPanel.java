package game.frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class BoardPanel extends TilePane {

	protected ImageView[][] cells;

	public void initialize(final int rows, final int columns, final int cellSize){
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

	public void setImageLevel(int row, int column, Image image, boolean hasFunctionality){
	    setImage(row, column, image);
    }

    public void setImageNull(int row, int column){
        cells[row][column].setImage(null);
    }
	
	protected void setImage(int row, int column, Image image) {
		cells[row][column].setImage(image);
	}


}