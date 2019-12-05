package game.backend.cell;

import game.backend.Grid;
import game.backend.move.Direction;

public class FruitCell extends Cell {
    public FruitCell(Grid grid) {
        super(grid);
    }

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

    @Override
    public boolean fallUpperContent() {
        Cell up = around[Direction.UP.ordinal()];
        if (this.isEmpty() && !up.isEmpty() && up.isMovable()) {
            this.content = up.getAndClearContent();
            if(this.content.getKey().equals("FRUIT")){
                clearContent();
            }
            grid.wasUpdated();
            if (this.hasFloor()) {
                grid.tryRemove(this);
                return true;
            } else {
                Cell down = around[Direction.DOWN.ordinal()];
                return down.fallUpperContent();
            }
        }
        return false;
    }

    //Devuelve si la celda en la direccion DOWN tiene un Wall
    private boolean hasBottomCell(){
        //System.out.println(this.around[Direction.DOWN.ordinal()]);
        return this.around[Direction.DOWN.ordinal()].getContent().getKey().equals("WALL");
    }
}
