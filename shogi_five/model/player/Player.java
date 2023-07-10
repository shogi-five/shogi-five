package model.player;

import model.piece.Piece;
import model.selection.Selection;

public abstract class Player {
    private Piece[] ownedPieces;

    /*
     * @param ownedPieces 所有している駒のリスト
     */
    public Player() {}
}
