package org.example.Models;

import java.util.Scanner;

public class Player {

    private long id;
    private String name ;
    private PlayerType playerType;
    private Symbol symbol;

    public Player(long id, String name, PlayerType playerType, Symbol symbol) {
        this.id = id;
        this.name = name;
        this.playerType = playerType;
        this.symbol = symbol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Move makeMove(Board board) {

        Scanner sc = new Scanner(System.in);

        System.out.println(" Enter the row number of the move");
        int row= sc.nextInt();
        System.out.println(" Enter the column number of the move");
        int column = sc.nextInt();

        return new  Move( new Cell(row , column),this);

    }
}
