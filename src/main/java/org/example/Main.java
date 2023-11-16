package org.example;

import org.example.Controller.GameController;
import org.example.Models.*;
import org.example.Stratagies.PlayingStrategies.EasyBotPlayingStrategy;
import org.example.Stratagies.WinningStrategies.ColumnWinningStrategy;
import org.example.Stratagies.WinningStrategies.DiagnolWinningStrategy;
import org.example.Stratagies.WinningStrategies.RowWinningStrategy;
import org.example.Stratagies.WinningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
;import static org.example.Models.PlayerType.BOT;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        //create  object of gamecontroller

        GameController gameController = new GameController();

        // game contoller object has game builder object of dimension, plyers , winning startegies
        //1. give dimension

        int dimension = 3;

        //2. list of players (2 for dimesnion=3)
        int numberofplayers= dimension-1;
        List<Player> players = new ArrayList<>();

        players.add( new Player(1L ,"Hamsika", PlayerType.HUMAN,   new Symbol('H')  ));
        players.add( new Bot(2L, "botplayer", BOT, new Symbol('S'), BotDifficultyLevel.EASY , new EasyBotPlayingStrategy()));

        //3.add all winning Strategies

        List<WinningStrategy> winningStrategies = new ArrayList<>();

        winningStrategies.add( new RowWinningStrategy());
        winningStrategies.add( new ColumnWinningStrategy());
        winningStrategies.add( new DiagnolWinningStrategy());

        //start the game after adding  dimension, players, winningstrategies

        //craete game object to play the game

        Game game = new Game(dimension , players , winningStrategies);

        while( gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){

            System.out.println(" This is current state of the board");

            gameController.displayBoard(game);
            gameController.makeMove(game);

        }
        if( gameController.getGameStatus(game).equals(GameStatus.ENDED)){

            gameController.displayBoard(game);
            System.out.println("winner is "+ gameController.getWinner(game).getName());

        }
        else{
            System.out.println("match is drawn");

        }
    }
}