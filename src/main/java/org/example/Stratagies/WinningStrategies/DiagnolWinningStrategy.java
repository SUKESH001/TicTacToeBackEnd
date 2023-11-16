package org.example.Stratagies.WinningStrategies;

import org.example.Models.Board;
import org.example.Models.Move;
import org.example.Models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagnolWinningStrategy implements WinningStrategy {

    private   Map<Symbol, Integer> rightDiagnolMap= new HashMap<>();
    private  Map<Symbol , Integer>  leftDiagnolMap= new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int row= move.getCell().getRow();
        int col= move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if( col==row){
            if( leftDiagnolMap.containsKey(symbol)){
                leftDiagnolMap.put( symbol , leftDiagnolMap.get(symbol)+1);
            }
            else{
                leftDiagnolMap.put(symbol , 1);
            }
        }
        if( col+row== board.getDimension()-1){

            if(rightDiagnolMap.containsKey(symbol)){
                rightDiagnolMap.put(symbol , rightDiagnolMap.get(symbol)+1);
            }
            else{
                rightDiagnolMap.put(symbol, 1);
            }
        }
        if( row==col && leftDiagnolMap.get(symbol)==board.getDimension()){
            return true;
        }
       if( row+col == board.getDimension()-1 && rightDiagnolMap.get(symbol) == board.getDimension()){
           return true;

       }
       return false;
    }
}
