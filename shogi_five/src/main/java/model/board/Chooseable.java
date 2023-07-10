package model.selection;

import model.board.Piece;

public abstract class Player {
    private Piece pieceType;
    private int position;

    public Chooseable(Piece piece, int position) {
        this.piece = piece;
        this.position = position;
    }

    
}
