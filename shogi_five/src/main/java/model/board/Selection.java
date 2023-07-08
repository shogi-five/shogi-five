package model.selection;

import model.board.Piece;
import model.board.Selection;

public abstract class Player {
    private Piece pieceType;
    private int position;

    public Selection(Piece piece, int position) {
        this.piece = piece;
        this.position = position;
    }

    
}
