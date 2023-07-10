package model.piece;

import model.Board;

/**
 * 将棋の駒を表す抽象クラス
 */

public abstract class Piece {
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか
    private Selection[] availableMoves;//移動可能なリスト

    /*
    * コンストラクタ
    */
    public Piece(int pieceClass, int position, Selection[] availableMoves) {
        this.pieceClass = pieceClass;
        this.position = position;
        this.promote = false;
        this.availableMoves = availableMoves;
    }

    //動きを定義する
    //public abstract int[] move();
}
