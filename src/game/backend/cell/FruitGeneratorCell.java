package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;
import game.backend.move.Direction;

public class FruitGeneratorCell extends CandyGeneratorCell {
    private int currentState;
    private final int PROBABILITY = 5;
    private boolean createdFruit;

    public FruitGeneratorCell(Grid grid) {
        super(grid);
        currentState = -1;
    }

    @Override
    public Element getContent() {
        if(currentState == 0){
            return super.getContent();
        }else if(currentState > 0){
            int i = (int)(Math.random() * FruitType.values().length);
            createdFruit = true;
            return new Fruit(FruitType.values()[i]);
        }else{
            if(getProbability()<=PROBABILITY){
                int i = (int)(Math.random() * FruitType.values().length);
                createdFruit = true;
                return new Fruit(FruitType.values()[i]);
            }else{
                return super.getContent();
            }
        }
    }

    private int getProbability(){
        return (int)(Math.random()*100);
    }

    public void updateState(int newState){
        currentState = newState;
    }
}
