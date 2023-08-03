package shogi_five.model;

import shogi_five.model.Board;
import shogi_five.model.Player;

import java.util.ArrayList;

public class Status implements Cloneable{
    Board board;
    Human human;
    AI ai;

    /*
     * コンストラクタ
     */
    public Status(Board board, Human human, AI ai){
        this.board = board;
        this.human = human;
        this.ai = ai;
    }

    /*
     * ディープコピー
     */
    public Status clone() throws CloneNotSupportedException{
        Status clone = (Status)super.clone();
        return clone;
    }

    public Board getBoard(){
        return this.board;
    }
    public Human getHuman(){
        return this.human;
    }
    public AI getAI(){
        return this.ai;
    }

    public void setBoard(Board board){
        this.board = board;
    }
    public void setHuman(Human human){
        this.human = human;
    }
    public void setAI(AI ai){
        this.ai = ai;
    }
}
