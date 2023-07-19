package shogi_five.model;

import shogi_five.model.Board;
import shogi_five.model.piece.Piece;
import shogi_five.model.Chooseable;
import shogi_five.model.Status;

public class Operator {
    /*
     * 駒の移動、所有を遷移させ次の場面を返す
     */
    public Status operator(){}

    /*
     * 駒が移動可能なリストを返す
     */
    public int[] availableMove(){}
}