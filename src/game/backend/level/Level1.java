package game.backend.level;

import game.backend.GameState;

public class Level1 extends Level {
	
	private static int REQUIRED_SCORE = 5000; 
	private static int MAX_MOVES = 20;

	@Override
	public String toString() {
		return "Level 1";
	}

	@Override
	protected GameState newState() {
		return new Level1State(REQUIRED_SCORE, MAX_MOVES);
	}
	
	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret;
		if (ret = super.tryMove(i1, j1, i2, j2)) {
			state().addMove();
		}
		return ret;
	}
	
	private class Level1State extends GameState {
		private long requiredScore;
		
		public Level1State(long requiredScore, int maxMoves) {
		    super(maxMoves);
			this.requiredScore = requiredScore;
		}
		
		public boolean gameOver() {
			return playerWon() || getMoves() >= maxMoves;
		}
		
		public boolean playerWon() {
			return getScore() > requiredScore;
		}
	}

}
