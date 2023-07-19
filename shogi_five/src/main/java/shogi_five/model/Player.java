package shogi_five.model;

import shogi_five.model.piece.Piece;
import shogi_five.model.Chooseable;

public abstract class Player {
    private Piece[] ownedPieces;

    /*
     * @param ownedPieces 所有している駒のリスト
     */
    public Player() {}
}
