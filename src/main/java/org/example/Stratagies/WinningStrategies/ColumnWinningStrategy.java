package org.example.Stratagies.WinningStrategies;

import org.example.Models.Board;
import org.example.Models.Move;
import org.example.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {

    private Map<Integer , Map<Symbol, Integer>> colMap= new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if( !colMap.containsKey(col)){
            colMap.put(col , new HashMap<>());
        }
        Map<Symbol , Integer> currentColMap = colMap.get(col);

        if( currentColMap.containsKey(symbol)){
            currentColMap.put(symbol , currentColMap.get(symbol)+1);
        }
        else{
            currentColMap.put(symbol, 1);
        }
        return currentColMap.get(symbol) == board.getDimension();
    }
}
