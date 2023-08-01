package shogi_five.model;

import shogi_five.model.Status;
import shogi_five.model.Node;
import shogi_five.model.Operator;
import shogi_five.model.Board;
import shogi_five.model.Player;
import shogi_five.model.Chooseable;
import shogi_five.model.piece.Piece;

import java.util.ArrayList;

public class Tree{
    /*
     * フィールド
     */
    /*
     * コンストラクタ
     */

    /*
     * minimax法で探索を行う．決定したNodeを返す．
     */
    public static Node miniMax(Node node,int depth){
        Status status = node.getStatus();
        Board board = status.getBoard();
        ArrayList<Node> nodes = new ArrayList<>();
        Node ans;//結果のノード

        //Pieceのリストを取得
        ArrayList<Piece> haveAIPiece = status.getAI().getHavePiece();
        int lenHavePiece = haveAIPiece.size();

        //駒を一つずつ処理していく
        for(int i ; i < lenHavePiece ; i++){
            //動く場所のリストを取得
            ArrayList<Integer> availableMoveList = new ArrayList<>();
            availableMoveList.add(Operator.availableMove(board, haveAIPiece.get(i)).getPosition());
            
            //それぞれの評価値を計算する
            int size = availableMoveList.size();
            for(int j ; j < size ; j++){
                //駒を動かした新しいノードを用意する
                Node nextNode = node;
                Operator.operator(node.getStatus(),haveAIPiece.get(i).getPosition(),availableMoveList.get(j));

                //盤面を評価する
                int eval = Operator.calEval(nextNode.getStatus());
                //評価値をノードにセットする
                nextNode.setEvaluation(eval);
            }

        }

        return ans;
    }
}