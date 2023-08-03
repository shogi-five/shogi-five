package shogi_five.model;

import shogi_five.model.Status;
import shogi_five.model.Node;
import shogi_five.model.Operator;
import shogi_five.model.Board;
import shogi_five.model.Player;
import shogi_five.model.Chooseable;
import shogi_five.model.piece.*;

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
        if(depth % 2 == 0){
            depth += 1;
        }
        return evalAI(node,depth);
    }

    /*
     *AIの評価値を計算する 
     */
    public static Node evalAI(Node node,int depth){

        Status status = node.getStatus();
        Board board = status.getBoard();
        int maxEval = Integer.MIN_VALUE;//評価値の最大
        Node maxNode = new Node();//評価値が最大のノード
        //Pieceのリストを取得
        ArrayList<Piece> haveAIPiece = status.getAI().getHavePiece();
        int lenHavePiece = haveAIPiece.size();



        for(int i = 0; i < lenHavePiece ; i++){

            //System.out.println(haveAIPiece.get(i).getPosition()+":"+haveAIPiece.get(i).getClass());//log

            //動く場所のリストを取得
            ArrayList<Integer> availableMoveList = Operator.availableMove(board, haveAIPiece.get(i).getPosition());
            
            

            //それぞれの評価値を計算し、最も高い評価値を取得する
            int size = availableMoveList.size();
            for(int j = 0; j < size ; j++){
                
                //System.out.println("before:" + node.getStatus().getBoard().getPiece(2));//log

                //駒を動かした新しいノードを用意する
                Node nextNode = node.NodeClone(node);
                Operator.operator(nextNode.getStatus(),haveAIPiece.get(i).getPosition(),availableMoveList.get(j));

                //System.out.println("after:" + node.getStatus().getBoard().getPiece(2));//log

                //盤面を評価する
                int eval=-1;
                if(depth >= 3){//深さが3以上の時、下の階層から評価値を取得する

                    //System.out.println("再帰");

                    eval = evalHuman(nextNode,depth - 1).getEvaluation();
                }else if(depth == 1){//深さ１の時、評価値を計算する

                    //System.out.println("計算");

                    eval = Operator.calEval(nextNode.getStatus());
                }

                //評価値の最大値より大きければ代入
                if(maxEval < eval){
                    maxEval = eval;
                    nextNode.setEvaluation(eval);
                    maxNode = nextNode;
                }
                //System.out.println("depth = "+depth+":eval = " + eval);//log
            }
        }
        return maxNode;

    }

    /*
     * 人間の階層の評価値を計算する
     */
    public static Node evalHuman(Node node,int depth){
        Status status = node.getStatus();
        Board board = status.getBoard();
        int minEval = Integer.MAX_VALUE;//評価値の最小
        Node minNode = new Node();//評価値が最小のノード

        //Pieceのリストを取得
        ArrayList<Piece> haveHumanPiece = status.getHuman().getHavePiece();
        int lenHavePiece = haveHumanPiece.size();
        

        for(int i = 0; i < lenHavePiece ; i++){
            //動く場所のリストを取得
            ArrayList<Integer> availableMoveList = Operator.availableMove(board, haveHumanPiece.get(i).getPosition());
            
            
            for(int k = 0;k< availableMoveList.size();k++){
               // System.out.println(availableMoveList.get(k)+":"+i);
            }

            //それぞれの動かし方の評価値を計算し、最も低い評価値を取得する
            int size = availableMoveList.size();
            for(int j = 0; j < size ; j++){

                System.out.println(haveHumanPiece.get(i).getPosition()+":"+availableMoveList.get(j));

                //駒を動かした新しいノードを用意する
                Node nextNode = node.NodeClone(node);
                Operator.operator(nextNode.getStatus(),haveHumanPiece.get(i).getPosition(),availableMoveList.get(j));
                
                //盤面を評価する
                int eval = evalAI(nextNode,depth - 1).getEvaluation();

                //評価値の最大値より小さければ代入
                if(minEval > eval){
                    minEval = eval;
                    nextNode.setEvaluation(eval);
                    minNode = nextNode;
                }
                System.out.println("depth = "+depth+":eval = " + eval);//log
            }

        }
        return minNode;
    }
}