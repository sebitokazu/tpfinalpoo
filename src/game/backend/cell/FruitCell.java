package game.backend.cell;

import game.backend.Grid;
import game.backend.move.Direction;

public class FruitCell extends Cell {
    public FruitCell(Grid grid) {
        super(grid);
    }

    //si la celda contiene una fruta, la elimina solo si es una celda de la ultima fila
    @Override
    public void clearContent() {
        if(!(content.getKey().equals("FRUIT"))) {
            super.clearContent();
        }else {
            if(hasBottomCell()) {
                super.clearContent();
            }
        }
    }

    //
    @Override
    public boolean fallUpperContent() {
        boolean ret;
        if(ret =super.fallUpperContent()){
            if(this.content.getKey().equals("FRUIT")){
                clearContent();
            }
        }
        return ret;
    }

    //Devuelve si la celda en la direccion DOWN tiene un Wall
    private boolean hasBottomCell(){
        return this.around[Direction.DOWN.ordinal()].getContent().getKey().equals("WALL");
    }
}
