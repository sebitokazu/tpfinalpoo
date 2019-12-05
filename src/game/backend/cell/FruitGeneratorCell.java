package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;
import game.backend.move.Direction;

public class FruitGeneratorCell extends CandyGeneratorCell {
    private final int PROBABILITY = 1;
    private int fruits;

    public FruitGeneratorCell(Grid grid, int fruits) {
        super(grid);
        this.fruits = fruits;
    }

    @Override
    public Element getContent() {
        if(fruits != 0){
            if(getProbability()<=PROBABILITY){
                int i = (int)(Math.random() * FruitType.values().length);
                fruits--;
                return new Fruit(FruitType.values()[i]);
            }else{
                return super.getContent();
            }
        }
        return super.getContent();
    }

    private int getProbability(){
        return (int)(Math.random()*100);
    }

}
