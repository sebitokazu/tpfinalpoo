package game.frontend;

public class Panel {
    private ScorePanel scorePanel;
    private BoardPanel boardPanel;

    public Panel(ScorePanel scorePanel, BoardPanel boardPanel){
        this.scorePanel = scorePanel;
        this.boardPanel = boardPanel;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }
}
