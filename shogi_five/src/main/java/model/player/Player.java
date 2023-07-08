package model.player;

import model.piece.Piece;
import model.selection.Selection;

public abstract class Player {
    private Piece[] ownedPieces;
    private Selection[] availableMoves;

    /*
     * @param ownedPieces 所有している駒のリスト
     */
    public Player() {
        // 所有する駒や移動可能な選択肢の初期化などの処理を実装する
    }
}
