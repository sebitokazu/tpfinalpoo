package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
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
    public void initialize() {
        super.initialize();
        int i;
        if( (i = internalState.getFruitsToCollect()) != REQUIRED_FRUITS){
            fruitGeneratorCell.resetFruits(REQUIRED_FRUITS - i);
            internalState.resetFruitCounter();
        }
    }



    @Override
    protected GameState newState() {
        return internalState;
    }

    @Override
    public String toString() {
        return "Level 3";
    }

    @Override
    protected void setGeneratorCell() {
        candyGenCell = fruitGeneratorCell;
    }

    @Override
    public void fallElements() {
        super.fallElements();
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean validMove = super.tryMove(i1, j1, i2, j2);
        if(validMove){
            state().addMove();
            if(g()[SIZE -1][j1].getContent().getKey().equals("FRUIT")){
                updateBottomLine(j1);
            }
        }
        return validMove;
    }

    private void updateBottomLine(int y){
        g()[SIZE-1][y].clearContent();
        fallElements();
    }

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
            return getFruitsToCollect() == 0;
        }

        @Override
        public boolean hasFunctionality() {
            return true;
        }
        @Override
        public int getInfo() { return getFruitsToCollect(); }

        public int getFruitsToCollect() {
            return fruitsToCollect;
        }

        public void collectFruit(){
            fruitsToCollect--;
        }

        public void resetFruitCounter(){
            fruitsToCollect = requiredFruits;
        }
    }

}