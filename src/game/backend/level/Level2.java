package game.backend.level;

import game.backend.GameState;

public class Level2 extends Level {

    private static int MAX_MOVES = 20;
    private int toGoldCells = SIZE*SIZE;

    @Override
    protected GameState newState() {
        return null;
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            if(i1-i2 == 0){
                System.out.println("HORIZONTAL");
                toGoldCells-=goldRow(i1);
            }else{
                System.out.println("VERTICAL");
                toGoldCells-=goldCol(j1);
            }
            System.out.println(toGoldCells);
            System.out.println(String.format("Point1 = [%d,%d] - Point2 = [%d,%d]",i1,j1,i2,j2));
        }
        return ret;
    }

    private int goldRow(int row){
        int painted = 0;
        for(int i=0; i<SIZE;i++){
            if(!g()[row][i].hasFunctionality()){
                g()[row][i].changeFunctionality();
                painted++;
            }
        }
        return painted;
    }
    private int goldCol(int col){
        int painted = 0;
        for(int i=0; i<SIZE;i++){
            if(!g()[i][col].hasFunctionality()){
                g()[i][col].changeFunctionality();
                painted++;
            }
        }
        return painted;
    }

    @Override
    public String toString() {
        return "Level 2";
    }

    private class Level2State extends GameState {
        private int ungoldedCells;
        private int maxMoves;

        public Level2State(int ungoldedCells, int maxMoves) {
            this.ungoldedCells = ungoldedCells;
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return ungoldedCells == 0;
        }
    }
}
