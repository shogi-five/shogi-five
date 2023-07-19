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
}
