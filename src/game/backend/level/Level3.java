package game.backend.level;

import game.backend.GameState;
import game.backend.cell.FruitGeneratorCell;

public class Level3 extends Level {
    private static int REQUIRED_FRUITS = 5;
    private static int MAX_MOVES = 20;
    private static int MAX_FRUITS_IN_GAME = 2;
    private FruitGeneratorCell fruitGeneratorCell = new FruitGeneratorCell(this);

    private Level3State internalState = new Level3State(REQUIRED_FRUITS,MAX_MOVES,MAX_FRUITS_IN_GAME);

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
        fruitGeneratorCell.updateState(internalState.getState());
        super.fallElements();

    }

    private class Level3State extends GameState {
        private int requiredFruits;
        private int fruitsInGame;
        private int wonFruits;
        private int maxFruitsInGame;

        public Level3State(int requiredFruits, int maxMoves, int maxFruitsInGame) {
            super(maxMoves);
            this.requiredFruits = requiredFruits;
            this.fruitsInGame = 0;
            this.maxFruitsInGame = maxFruitsInGame;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return wonFruits >= requiredFruits;
        }

        @Override
        public int getInfo() { return requiredFruits - wonFruits; }

        public int getState(){
            int i = maxFruitsInGame - fruitsInGame;
            if(maxFruitsInGame == i){
                return 1;
            } else{
                if(i == 0){
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
        public int getWonFruits(){ return wonFruits; }
        public void addFruit(){ wonFruits++; }
        public void incrementFruitsInGame(){ fruitsInGame++; }
        public void decrementFruitsInGame(){ fruitsInGame--; }
    }

}
