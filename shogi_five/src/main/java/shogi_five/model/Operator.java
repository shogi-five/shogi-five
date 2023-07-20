package shogi_five.model;

import shogi_five.model.Board;
import shogi_five.model.piece.Piece;
import shogi_five.model.Chooseable;
import shogi_five.model.Status;

import java.util.ArrayList;

public class Operator {
    /*
     * 駒の移動、所有を遷移させ次の場面を返す
     * @param board 現在の盤面
     * @param human 人の駒の所有リスト
     * @param ai aiの駒の所有リスト
     * @param now 動かす駒の位置
     * @param next 駒の行先
     * @return Status 駒を移動させたStatus
     */
    public static Status operator(Board board, Player human, Player ai, int now, int next){
        Piece piece = board.getPiece(now);//駒を取得

        Status next_Status = new Status(board, human, ai);

        if (board.getPiece(next) == null){ //移動先に駒がない場合
            //駒を移動
            piece.setPosition(next);
            board.setPiece(null, now);
            board.setPiece(piece, next);

            next_Status.setAI(ai);
            next_Status.setBoard(board);
            next_Status.setHuman(human);
        }else{//移動先に駒がある場合
            //***********************todo*********************** */
        }
        return next_Status;
    }

    /*
     * 駒が移動可能なリストを返す
     * @param board 現在の盤面
     * @param position 駒の位置
     * @return ArrayList<Integer> 駒が移動可能な位置のリスト
     */
    public static ArrayList<Integer> availableMove(Board board, int position){
        //駒を取得
        Piece piece = board.getPiece(position);        
        //駒が移動できるリストを取得
        ArrayList<Integer> positionList = piece.move(board);

        return positionList;
    }
}