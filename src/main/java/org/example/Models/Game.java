package org.example.Models;

import java.util.ArrayList;
import java.util.List;

import org.example.Exception.GameInvalidationException;
import org.example.Exception.ValidateMoveException;
import org.example.Stratagies.WinningStrategies.WinningStrategy;

public class Game {

    private  Board board;
    private List<Player> players;
    private GameStatus gameStatus;
    private Player winner;
    private  int nextMovePlayerIndex;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;

    public Game(int dimension, List<Player> players
               ,List<WinningStrategy> winningStrategies) {
        this.board = new Board( dimension);
        this.players = players;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.nextMovePlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextMovePplayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePplayerIndex(int nextMovePplayerIndex) {
        this.nextMovePlayerIndex = nextMovePplayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public  void printBoard(){
        board.displayBoard();
    }

    public  static Builder getBuilder(){
        return  new Builder();
    }
    public static class Builder{

        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
        private   Boolean validate(){
            return true;
        }
        public Game build() throws  Exception {

            if(!validate()){
                throw new GameInvalidationException("Invalid game");
            }

            return new Game( dimension, players , winningStrategies);
        }
    }
    private  Boolean validateMove( Move move)  throws  Exception{
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(!validateExceptionMove(row , col)){
            throw new ValidateMoveException(" Invalid Move by " + move.getPlayer().getName());
        }

        return row >= 0 && row < board.getDimension() && col >= 0 && col < board.getDimension()
                && board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }
    private Boolean validateExceptionMove( int row , int col){


        if( row <0 || row>= board.getDimension() || col <0 || col>= board.getDimension() ||
                !board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return  false;
        }
        return true;

    }

    private Boolean checkWinner( Board board , Move move){
        for (WinningStrategy winningStrategy : winningStrategies){
            if( winningStrategy.checkWinner(board , move)){
                return  true;
            }
        }
        return false;
    }

    public  void makeMove() throws Exception {
        Player currentPlayer= players.get(nextMovePlayerIndex);// current player to make the move

        System.out.println("It is " + currentPlayer.getName() + "'s move");

        Move move = currentPlayer.makeMove(this.board);//makemove function called on this board

        System.out.println(currentPlayer.getName() + " has made the move at Row;" +
                move.getCell().getRow() + " Col:" + move.getCell().getCol());//move has made

        //validate the move
        if( !validateMove(move)){
            System.out.println(" This is invalid move by player" + currentPlayer.getName());
        }
        // update the cell state
        int row =move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell finalMoveToMakeMove= board.getBoard().get(row).get(col);//get the cell

        finalMoveToMakeMove.setCellState(CellState.FILLED);//update
        finalMoveToMakeMove.setPlayer(currentPlayer);//update

        //add move to the moves

        Move finalMove= new Move( finalMoveToMakeMove , currentPlayer);
        moves.add(finalMove);

        //update nextplayer index

        nextMovePlayerIndex+=1;
        nextMovePlayerIndex %= players.size();

        //after move check for winner

        if( checkWinner(board , finalMove)){
            gameStatus= GameStatus.ENDED;
            winner= currentPlayer;
        }
        //check for draw
        else if(moves.size()==(board.getDimension()*board.getDimension())){
            gameStatus=GameStatus.DRAW;

        }
    }
}
