package model.piece;

import model.Board;

/**
 * 将棋の駒を表す抽象クラス
 */

public abstract class Piece {
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか

    /*
    * コンストラクタ
    */
    public Piece(int pieceClass, int position, Selection[] availableMoves) {
        this.pieceClass = pieceClass;
        this.position = position;
        this.promote = false;
    }

    //動きを定義する
    public abstract int[] move();
}
