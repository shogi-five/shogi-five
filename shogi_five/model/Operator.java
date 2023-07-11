package model.operator;

import model.board.Board;
import model.piece.Piece;
import model.selection.Selection;
import model.Status;

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