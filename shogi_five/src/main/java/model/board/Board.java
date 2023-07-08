package model.board;

import model.piece.Piece;

public class Board {
    private Piece[] board;

    /*
     * コンストラクタ：初期化
     */
    public Board() {}

    /*
     * 駒を位置にセット
     */
    public void setPiece(Piece piece, int position){}

    /*
     * 位置にセットされている駒をゲット
     */
    public Piece getPiece(int position){}
}
