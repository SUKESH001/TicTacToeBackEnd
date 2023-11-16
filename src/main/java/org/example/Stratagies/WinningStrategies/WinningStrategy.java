package org.example.Stratagies.WinningStrategies;

import org.example.Models.Board;
import org.example.Models.Move;

public interface WinningStrategy {


    public boolean checkWinner(Board board , Move move);
}
