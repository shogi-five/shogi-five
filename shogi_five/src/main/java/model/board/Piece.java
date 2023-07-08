package model.piece;

import model.board.Board;

public class Piece {
    private int pieceClass;//駒の種類
    private int position;//駒の現在地
    private boolean promote;//成りかどうか
    private Selection[] availableMoves;//移動可能なリスト

    public Piece(int pieceClass, int position, Selection[] availableMoves) {
        this.pieceClass = pieceClass;
        this.position = position;
        this.promote = false;
        this.availableMoves = availableMoves;
    }
}
