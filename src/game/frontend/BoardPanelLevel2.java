package game.frontend;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BoardPanelLevel2 extends BoardPanel {

    //si es un casillero dorado, ademas de setear la imagen, la pinto de amarillo. sino solo seteo la imagen
    @Override
    public void setImageLevel(int row, int column, Image image, boolean isGolden) {
        if(isGolden){
            setImage(row, column, image);
        }else{
            super.setImage(row, column, image);
        }
    }

    //setea la imagen y pinta el casillero de amarillo
    @Override
    protected void setImage(int row, int column, Image image) {
        super.setImage(row, column, image);
        Light.Distant spotLight = new Light.Distant();
        spotLight.setColor(Color.YELLOW);
        spotLight.setElevation(100);
        Lighting lighting = new Lighting(spotLight);
        cells[row][column].setEffect(lighting);
    }
}
