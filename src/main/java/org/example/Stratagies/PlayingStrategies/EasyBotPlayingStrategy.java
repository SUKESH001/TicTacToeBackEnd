package org.example.Stratagies.PlayingStrategies;

import org.example.Models.Board;
import org.example.Models.Cell;
import org.example.Models.CellState;
import org.example.Models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
        public Move makeMove(Board board) {
            //In easy bot playing strategy, bot will make a move at the first
            //available cell.
            for (List<Cell> row : board.getBoard()) {
                for (Cell cell : row) {
                    if (cell.getCellState().equals(CellState.EMPTY)) {
                        //Bot can make a move at this cell.
                        return new Move(cell, null);
                    }
                }
            }
        return null;
    }
}
