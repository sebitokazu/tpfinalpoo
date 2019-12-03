package game.backend;

public abstract class GameState {
	
	private long score = 0;
	private int moves = 0;
	protected final int maxMoves;

	protected GameState(int maxMoves){
		this.maxMoves=maxMoves;
	}
	
	public void addScore(long value) {
		this.score = this.score + value;
	}
	
	public long getScore(){
		return score;
	}
	
	public void addMove() {
		moves++;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public abstract boolean gameOver();
	
	public abstract boolean playerWon();

	public int getMovesLeft(){
		return maxMoves - moves;
	}

	public boolean hasFunctionality(){return false;}

	public int getInfo(){
		if(!hasFunctionality()){
			throw new IllegalArgumentException(); //crear excepcion
		}
		return -1;
	}

}
