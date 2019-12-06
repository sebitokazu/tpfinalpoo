package game.backend.level;

import game.backend.GameState;
import game.backend.cell.Cell;
import game.backend.cell.FruitCell;
import game.backend.cell.FruitGeneratorCell;
import game.backend.element.Element;

public class Level3 extends Level {
    private static int REQUIRED_FRUITS = 5;
    private static int MAX_MOVES = 50;
    private FruitGeneratorCell fruitGeneratorCell = new FruitGeneratorCell(this, REQUIRED_FRUITS);
    private Level3State internalState = new Level3State(REQUIRED_FRUITS,MAX_MOVES);

    @Override
    protected Cell getNewCell() {
        return new FruitCell(this);
    }

    @Override
    protected GameState newState() {
        return internalState;
    }

    @Override
    public String toString() {
        return "Level 3";
    }

    //asigna a la celda generadora una instancia de FruitGeneratorCell
    @Override
    protected void setGeneratorCell() {
        genCell = fruitGeneratorCell;
    }

    @Override
    public void initialize() {
        super.initialize();

        //en caso de que al crear el tablero inicial una fruta se haya consumido por quedar en la ultima fila
        // o por una sucesion de combos que generen esa situacion, resetea los contadores de fruta.
        int i;
        if( (i = internalState.getInfo()) != REQUIRED_FRUITS){
            fruitGeneratorCell.resetFruits(REQUIRED_FRUITS - i);
            internalState.resetFruitCounter();
        }
    }

    /* si se realizo un movimiento valido, verifica si este dejo una fruta en la ultima fila
     * y, de ser asi, elimina dicha fruta
     */
    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if(ret = super.tryMove(i1,j1,i2,j2)){
            if(g()[SIZE -1][j1].getContent().getKey().equals("FRUIT")){
                g()[SIZE-1][j1].clearContent();
                fallElements();
            }
        }
        return ret;
    }

    //si explota una celda que tiene
    @Override
    public void cellExplosion(Element e) {
        super.cellExplosion(e);
        if(e.getKey().equals("FRUIT")){
            internalState.collectFruit();
        }
    }

    private class Level3State extends GameState {
        private int requiredFruits;
        private int fruitsToCollect;
        private int maxMoves;

        public Level3State(int requiredFruits, int maxMoves) {
            super(maxMoves);
            this.requiredFruits = fruitsToCollect = requiredFruits;
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return fruitsToCollect == 0;
        }

        //indica que hay informacion adicional en este nivel
        @Override
        public boolean hasFunctionality() {
            return true;
        }

        //devuelve la cantidad de frutas que faltan recolectar
        @Override
        public int getInfo() { return fruitsToCollect; }

        //decrementa en uno la cantidad de frutas a recolectar
        public void collectFruit(){
            fruitsToCollect--;
        }

        /* resetea la cantidad de frutas a recolectar al requerido por el nivel
         * este metodo debe llamarse unicamente si en la creacion del tablero
         * se consumio una fruta
         */
        public void resetFruitCounter(){
            fruitsToCollect = requiredFruits;
        }
    }

}
