package org.example.Controller;

import org.example.Models.Game;
import org.example.Models.GameStatus;
import org.example.Models.Player;
import org.example.Stratagies.WinningStrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame (int dimension , List<Player> players,
                           List<WinningStrategy> winningstrategies) throws Exception{

        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningstrategies)
                .build();
    }

    public void displayBoard(Game game){
        game.printBoard();
    }
    public  void makeMove(Game game) throws Exception {
        game.makeMove();
    }
    public Player getWinner( Game game){
        return game.getWinner();
    }
    public GameStatus getGameStatus(Game game){
       return  game.getGameStatus();
    }

}
