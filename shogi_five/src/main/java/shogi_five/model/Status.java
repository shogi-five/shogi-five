package shogi_five.model;

import shogi_five.model.Board;
import shogi_five.model.Player;

import java.util.ArrayList;

public class Status {
    Board board;
    Player human;
    Player ai;

    /*
     * コンストラクタ
     */
    public Status(Board board, Player human, Player ai){
        this.board = board;
        this.human = human;
        this.ai = ai;
    }

    public Board getBoard(){
        return this.board;
    }
    public Player getHuman(){
        return this.human;
    }
    public Player getAI(){
        return this.ai;
    }

    public void setBoard(Board board){
        this.board = board;
    }
    public void setHuman(Player human){
        this.human = human;
    }
    public void setAI(Player ai){
        this.ai = ai;
    }
}
