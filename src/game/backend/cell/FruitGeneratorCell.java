package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;
import game.backend.move.Direction;

public class FruitGeneratorCell extends CandyGeneratorCell {
    public FruitGeneratorCell(Grid grid) {
        super(grid);
    }

    @Override
    public Element getAndClearContent(int state) {
        return getContent(state);
    }

    @Override
    public boolean fallUpperContent(int state) {
        Cell up = around[Direction.UP.ordinal()];
        if (this.isEmpty() && !up.isEmpty() && up.isMovable()) {
            this.content = up.getAndClearContent(state);
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

    @Override
    public Element getContent(int state) {
        if(state == 0){
            return super.getContent();
        }else if(state > 0){
            int i = (int)(Math.random() * FruitType.values().length);
            return new Fruit(FruitType.values()[i]);
        }else{
            int i = (int)(Math.random()*100);
            if(i<=5){
                int j = (int)(Math.random() * FruitType.values().length);
                return new Fruit(FruitType.values()[j]);
            }else{
                return super.getContent();
            }
        }
    }
}
