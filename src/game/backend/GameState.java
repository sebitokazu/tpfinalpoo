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

	//devuelve los movimientos restantes del usuario
	public int getMovesLeft(){
		return maxMoves - moves;
	}

	/* en caso del nivel requerir informacion adicional, se debe sobreescribir este metodo.
	 * una llamada a hasFunctionality que retorna false, significa que no deberia llamarse al metdo getInfo.
	 */
	public boolean hasFunctionality(){return false;}

	/* los niveles agregados requerian mostrar en pantalla informacion adicional aparte de el score y los movimientos
	 * este metodo devuelve dicha informacion.
	 * en caso de no requerir informacion adicional, una llamada al metodo lanza una excepcion.
	 * se recomienda sobreescribir este metodo, ante una sobreescritura de hasFunctionality
	 */
	public int getInfo(){
			throw new IllegalStateException("This level ");
	}

}
