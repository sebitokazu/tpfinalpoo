package game.backend.level;

import game.backend.GameState;

public class Level2 extends Level {

    private static int MAX_MOVES = 40;
    private int TO_GOLD_CELLS = SIZE*SIZE;
    private Level2State internalState = new Level2State(TO_GOLD_CELLS,MAX_MOVES);

    @Override
    protected GameState newState() {
        return internalState;
    }

    //si se realizo un movimiento valido, se fija si es horizontal o vertical y llama a goldRow o goldCol respectivamente
    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            if(i1-i2 == 0){
                internalState.goldCells(goldRow(i1));
            }else{
                internalState.goldCells(goldCol(j1));
            }
        }
        return ret;
    }

    //para una fila, cambia el estado de las celdas que no estaban doradas y retorna cuantas se pintaron
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

    //para una columna, cambia el estado de las celdas que no estaban doradas y retorna cuantas se pintaron
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

    private static class Level2State extends GameState {
        private int toGoldCells;

        public Level2State(int toGoldCells, int maxMoves) {
            super(maxMoves);
            this.toGoldCells = toGoldCells;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return toGoldCells == 0;
        }

        //indica que hay informacion adicional en este nivel
        @Override
        public boolean hasFunctionality() {
            return true;
        }

        //devuelve la cantidad de casilleros que faltan pintar de dorado
        @Override
        public int getInfo() {
            return toGoldCells;
        }

        //decrementa la canntidad de celdas que se pintaron
        public void goldCells(int qty){
            toGoldCells-=qty;
        }

    }
}
