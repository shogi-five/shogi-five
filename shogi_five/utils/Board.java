package shogi_five.utils;

import java.util.Optional;

import shogi_five.*;


public class Board {
    private Piece[] board = new Piece[45];

    
    public Board() {
        this
    }


    /**
     * 駒を動かす
     * @param selection 手
     * @return 動かした盤面
     */
    public Board move(Selection selection) {
        Board next = new Board();
        // 
        // 実装

        return next;

    }

    /**
     * 移動できるすべての手を計算しリストで返す
     * @return
     */
    public Selection[] validSelection() {
        
        return 計算させるクラス.計算(this);
    }
}


abstract class Piece {
    boolean isFirst; // 先手が持っているかどうか
    // ☖

    Selection[] validSelection();
}

class 金 extends Piece {
    Selection[] validSelection();
}

class 銀 extends Piece {

}

class 成銀 extends Piece {

}



class Selection {
    int now_position;
    int next_position;
    
    boolean nari;
}

abstract class Player {}

class Human extends Player {}

class FujiiAI extends Player {

}
