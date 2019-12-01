package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.Cell;

public abstract class Level extends Grid {
    protected Cell wallCell;
    protected Cell candyGenCell;

    @Override
    protected abstract  void fillCells();
    @Override
    protected abstract GameState newState();

    @Override
    public abstract String toString();
}
