package model.piece;

import model.Board;

/**
 * 将棋の駒を表す抽象クラス
 */

public abstract class Piece {
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか
    private boolean owner;//所有者

    /*
    * コンストラクタ
    */
    public abstract Piece(int position) {}

    /*
     * 駒を成らせる
     */
    public abstract void promotedPice(){}

    /*
     * 駒の成りを解除する
     */
    public abstract void demotedPice(){}

    //動きを定義する
    public abstract ArrayList<Integer> move();
}
