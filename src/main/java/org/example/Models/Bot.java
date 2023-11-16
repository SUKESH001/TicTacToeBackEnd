package org.example.Models;

import org.example.Stratagies.PlayingStrategies.BotPlayingStrategy;

public class Bot extends Player{

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(long id, String name, PlayerType playerType, Symbol symbol, BotDifficultyLevel botDifficultyLevel,
               BotPlayingStrategy botPlayingStrategy) {
        super(id, name, playerType, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = botPlayingStrategy;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }


    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }
    public Move makeMove(Board board){

        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;

    }
}
