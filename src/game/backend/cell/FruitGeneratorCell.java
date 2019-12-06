package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;

public class FruitGeneratorCell extends CandyGeneratorCell {
    private final int PROBABILITY = 1;
    private int fruits;
    private int totalFruits;

    public FruitGeneratorCell(Grid grid, int fruits) {
        super(grid);
        this.fruits = totalFruits = fruits;
    }

    @Override
    public Element getContent() {
        if(fruits > 0){
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

    //Emparcha la posibilidad de que se colecte una fruta por un combo en la generacion del tablero
    public void resetFruits(int toReAdd){
        fruits += toReAdd;
    }

    private int getProbability(){
        return (int)(Math.random()*100);
    }

}
