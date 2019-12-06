package game.backend.cell;

import game.backend.Grid;
import game.backend.element.*;

public class FruitGeneratorCell extends CandyGeneratorCell {
    private final int PROBABILITY = 1;
    private int fruits;

    public FruitGeneratorCell(Grid grid, int fruits) {
        super(grid);
        this.fruits = fruits;
    }

    /* si aun quedan frutas por agregar, con la probabilidad de PROBABILITY/100 se genera una fruta
     * de lo contario, se generan cnadys
     */
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

    /* ante la posibilidad de que se colecte una fruta por un combo en la generacion del tablero
     * creamos este metodo que resetea las frutas a crear
     * solo debe llamarse si al generar el tablero, se recolectaron frutas
     */
    public void resetFruits(int toReAdd){
        fruits += toReAdd;
    }

    //devuelve un numero entero
    private int getProbability(){
        return (int)(Math.random()*100);
    }

}
